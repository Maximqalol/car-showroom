package ru.example.carshowroom.data.dto;

import java.time.LocalDateTime;

public class RequestDto {
    private Integer id;
    private LocalDateTime date;
    private Integer carId;
    private Integer customerId;

    public RequestDto() {
    }

    public RequestDto(Integer id, LocalDateTime date, Integer carId, Integer customerId) {
        this.id = id;
        this.date = date;
        this.carId = carId;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "RequestDto {" +
                "id=" + id +
                ", date=" + date +
                ", carId=" + carId +
                ", customerId=" + customerId +
                '}';
    }
}
