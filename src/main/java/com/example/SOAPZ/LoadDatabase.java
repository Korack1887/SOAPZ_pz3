package com.example.SOAPZ;


import com.example.SOAPZ.entity.Service;
import com.example.SOAPZ.repository.ServiceRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ServiceRepo repo) {

        return args -> {
           List<Service> serviceList = repo.findAll() ;
            System.out.println(serviceList);
        };
    }
}
