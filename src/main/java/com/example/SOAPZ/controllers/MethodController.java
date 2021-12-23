package com.example.SOAPZ.controllers;

import com.example.SOAPZ.entity.Method;
import com.example.SOAPZ.repository.MethodRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
    class MethodController {

        private final MethodRepo repository;

        MethodController(MethodRepo repository) {
            this.repository = repository;
        }


        // Aggregate root
        // tag::get-aggregate-root[]
        @GetMapping("/methods")
        CollectionModel<EntityModel<Method>> all() {

            List<EntityModel<Method>> methods = repository.findAll().stream()
                    .map(method -> EntityModel.of(method,
                            linkTo(methodOn(MethodController.class).one(method.getId())).withSelfRel(),
                            linkTo(methodOn(MethodController.class).all()).withRel("methods")))
                    .collect(Collectors.toList());

            return CollectionModel.of(methods, linkTo(methodOn(MethodController.class).all()).withSelfRel());
        }
        // end::get-aggregate-root[]

        @PostMapping("/methods")
        Method newMethod(@RequestBody Method newMethod) {
            return repository.save(newMethod);
        }

        // Single item

        @GetMapping("/methods/{id}")
        EntityModel<Method> one(@PathVariable Long id) {
            Method method = repository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException());

            return EntityModel.of(method,
                    linkTo(methodOn(MethodController.class).one(id)).withSelfRel(),
                    linkTo(methodOn(MethodController.class).all()).withRel("methods"));
        }


}
