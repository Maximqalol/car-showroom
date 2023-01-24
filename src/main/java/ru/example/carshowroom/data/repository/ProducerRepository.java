package ru.example.carshowroom.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.carshowroom.data.entity.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {
}