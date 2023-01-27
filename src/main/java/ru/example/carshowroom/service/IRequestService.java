package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Request;

import java.util.List;

public interface IRequestService {

    Request create(Request request);

    void remove(Integer requestId);

    Request update(Request request);

    List<Request> getRequests();

    Request getRequestById(Integer requestId);

}
