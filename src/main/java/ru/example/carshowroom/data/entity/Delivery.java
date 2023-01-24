package ru.example.carshowroom.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery", schema = "public")
public class Delivery {

    @Id
    @Column(name = "id_delivery", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "delivery_method", nullable = false)
    private String deliveryMethod;

    @Column(name = "date_of_delivery", nullable = false)
    private LocalDateTime dateOfDelivery;

    @ManyToOne(targetEntity = Request.class)
    @JoinColumn(name = "id_request")
    private Request request;

    public Delivery() {
    }

    public Delivery(Integer id, String deliveryMethod, LocalDateTime dateOfDelivery, Request request) {
        this.id = id;
        this.deliveryMethod = deliveryMethod;
        this.dateOfDelivery = dateOfDelivery;
        this.request = request;
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

}
