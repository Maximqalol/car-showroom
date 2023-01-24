package ru.example.carshowroom.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.example.carshowroom.data.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "select * from car where quantity > 0", nativeQuery = true)
    List<Car> findAvailableCars();
    Car findCarById(Integer carId);
}