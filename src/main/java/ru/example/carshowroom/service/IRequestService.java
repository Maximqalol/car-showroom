package ru.example.carshowroom.service;

import ru.example.carshowroom.data.dto.RequestDto;
import ru.example.carshowroom.data.entity.Request;

import java.util.List;

public interface IRequestService {

    RequestDto create(RequestDto requestDto);

    void remove(Integer requestId);

    List<RequestDto> getRequests();

    RequestDto getRequestById(Integer requestId);
}
