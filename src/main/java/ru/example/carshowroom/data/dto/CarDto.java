package ru.example.carshowroom.data.dto;

import java.util.Objects;

public class CarDto {
    private Integer id;
    private String brand;
    private String model;
    private String features;
    private Integer year;
    private Integer price;
    private Integer quantity;
    private Integer producerId;

    public CarDto() {
    }

    public CarDto(Integer id, String brand, String model, String features, Integer year, Integer price, Integer quantity, Integer producerId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.features = features;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.producerId = producerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    @Override
    public String toString() {
        return "CarDto {" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", features='" + features + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", quantity=" + quantity +
                ", producerId=" + producerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return id.equals(carDto.id) &&
                brand.equals(carDto.brand) &&
                model.equals(carDto.model) &&
                features.equals(carDto.features) &&
                year.equals(carDto.year) &&
                price.equals(carDto.price) &&
                quantity.equals(carDto.quantity) &&
                producerId.equals(carDto.producerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, features, year, price, quantity, producerId);
    }
}
