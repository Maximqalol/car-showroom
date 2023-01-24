package ru.example.carshowroom.data.dto;

import java.time.LocalDateTime;

public class DeliveryDto {
    private Integer id;
    private String deliveryMethod;
    private LocalDateTime dateOfDelivery;
    private Integer requestId;

    public DeliveryDto() {
    }

    public DeliveryDto(Integer id, String deliveryMethod, LocalDateTime dateOfDelivery, Integer requestId) {
        this.id = id;
        this.deliveryMethod = deliveryMethod;
        this.dateOfDelivery = dateOfDelivery;
        this.requestId = requestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public LocalDateTime getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDateTime dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "DeliveryDto {" +
                "id=" + id +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", dateOfDelivery=" + dateOfDelivery +
                ", requestId=" + requestId +
                '}';
    }
}
