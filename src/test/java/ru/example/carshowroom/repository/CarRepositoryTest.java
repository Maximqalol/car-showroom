package ru.example.carshowroom.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CarRepositoryTest {
/*
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void savingCar_then_findingCarById_isNotNull_and_returnsSameCar() {
        Producer producer = new Producer(1, "Producer", "Address", "Phone");
        entityManager.merge(producer);
        entityManager.flush();
        //producerRepository.save(producer);
        Car car = new Car(1, "BMW", "M3", "some features", 2016, 3700000, 2, producer);
        entityManager.merge(car);
        entityManager.flush();
        //carRepository.save(car);
        Car foundedCar = carRepository.findById(1).orElse(null);
        assertThat(foundedCar).isNotNull();
        assertEquals(asJsonString(foundedCar), asJsonString(car));
    }

    @Test
    public void savingAllCars_then_availableCarsSize_returnsSameNumber() {
        Producer producer = new Producer(2, "Producer", "Address", "Phone");
        entityManager.merge(producer);
        entityManager.flush();
        Car car = new Car(1, "BMW", "M3", "some features", 2017, 3700000, 2, producer);
        Producer producer2 = new Producer(3, "Producer", "Address", "Phone");
        entityManager.merge(producer2);
        entityManager.flush();
        Car car2 = new Car(3, "BMW", "M5", "some features", 2015, 5800000, 1, producer2);
        Producer producer3 = new Producer(4, "Producer", "Address", "Phone");
        entityManager.merge(producer3);
        entityManager.flush();
        Car car3 = new Car(4, "BMW", "X6", "some features", 2016, 4600000, 0, producer3);

        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car2);
        cars.add(car3);

        entityManager.merge(cars);
        entityManager.flush();
        List<Car> availableCars = carRepository.findAvailableCars();
        assertEquals(2, availableCars.size());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
*/
}