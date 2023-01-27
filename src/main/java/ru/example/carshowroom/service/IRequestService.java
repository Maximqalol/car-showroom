package ru.example.carshowroom.service;

import ru.example.carshowroom.data.dto.RequestDto;

import java.util.List;

public interface IRequestService {

    void create(RequestDto requestDto);

    void remove(Integer requestId);

    void update(RequestDto requestDto);

    List<RequestDto> getRequests();

    RequestDto getRequestById(Integer requestId);

}
