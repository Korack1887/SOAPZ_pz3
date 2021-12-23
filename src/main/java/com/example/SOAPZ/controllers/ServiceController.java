package com.example.SOAPZ.controllers;

import com.example.SOAPZ.entity.Service;
import com.example.SOAPZ.repository.ServiceRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
    class ServiceController {

        private final ServiceRepo repository;

        ServiceController(ServiceRepo repository) {
            this.repository = repository;
        }


        // Aggregate root
        // tag::get-aggregate-root[]
        @GetMapping("/api")
        CollectionModel<EntityModel<Service>> all() {

            List<EntityModel<Service>> services = repository.findAll().stream()
                    .map(service -> EntityModel.of(service,
                            linkTo(methodOn(ServiceController.class).one(service.getId())).withSelfRel(),
                            linkTo(methodOn(ServiceController.class).all()).withRel("services")))
                    .collect(Collectors.toList());

            return CollectionModel.of(services, linkTo(methodOn(ServiceController.class).all()).withSelfRel());
        }
        // end::get-aggregate-root[]


        // Single item

        @GetMapping("/api/{id}")
        EntityModel<Service> one(@PathVariable Long id) {
            Service service = repository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException());

            return EntityModel.of(service,
                    linkTo(methodOn(ServiceController.class).one(id)).withSelfRel(),
                    linkTo(methodOn(ServiceController.class).all()).withRel("services"));
        }

}
