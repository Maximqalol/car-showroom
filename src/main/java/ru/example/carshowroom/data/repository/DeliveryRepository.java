package ru.example.carshowroom.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.carshowroom.data.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Delivery findDeliveryByRequestId(Integer requestId);

}
