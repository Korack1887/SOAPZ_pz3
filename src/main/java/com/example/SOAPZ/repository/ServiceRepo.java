package com.example.SOAPZ.repository;

import com.example.SOAPZ.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service, Long> {
}
