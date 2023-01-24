package ru.example.carshowroom.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "request", schema = "public")
public class Request {

    @Id
    @Column(name = "id_request", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "id_car")
    private Car car;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    public Request() {
    }

    public Request(Integer id, LocalDateTime date, Car car, Customer customer) {
        this.id = id;
        this.date = date;
        this.car = car;
        this.customer = customer;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
