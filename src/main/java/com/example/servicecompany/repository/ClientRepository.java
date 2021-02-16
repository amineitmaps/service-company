package com.example.servicecompany.repository;

import com.example.servicecompany.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
