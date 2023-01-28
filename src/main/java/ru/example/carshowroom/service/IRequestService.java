package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Request;

import java.util.List;

public interface IRequestService {

    Request save(Request request);

    void remove(Integer requestId);

    List<Request> getRequests();

    Request getRequestById(Integer requestId);

}
