package ru.example.carshowroom.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.carshowroom.data.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}