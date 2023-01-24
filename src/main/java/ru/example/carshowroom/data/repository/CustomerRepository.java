package ru.example.carshowroom.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.carshowroom.data.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}