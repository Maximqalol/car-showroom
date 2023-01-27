package ru.example.carshowroom.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.example.carshowroom.data.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    @Query(value = "select * from delivery where id_request = :requestId", nativeQuery = true)
    Delivery findDeliveryByRequestId(@Param("requestId") Integer requestId);

}
