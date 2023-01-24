package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.dto.RequestDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Request;
import ru.example.carshowroom.data.mapper.RequestMapper;
import ru.example.carshowroom.data.repository.CarRepository;
import ru.example.carshowroom.data.repository.RequestRepository;
import ru.example.carshowroom.service.IRequestService;

import java.util.List;

@Service
public class RequestServiceImpl implements IRequestService {
    private final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);
    private RequestMapper requestMapper;
    private RequestRepository requestRepository;
    private CarRepository carRepository;

    @Autowired
    public RequestServiceImpl(RequestMapper requestMapper, RequestRepository requestRepository, CarRepository carRepository) {
        this.requestMapper = requestMapper;
        this.requestRepository = requestRepository;
        this.carRepository = carRepository;
    }

    @Override
    public RequestDto create(RequestDto requestDto) {
        log.debug("Finding car with id = {}.", requestDto.getCarId());
        Car car = carRepository.findById(requestDto.getCarId()).orElse(null);
        if (car != null && car.getQuantity() > 0) {
            log.debug("Setting car's quantity");
            car.setQuantity(car.getQuantity() - 1);
            log.debug("Saving car with quantity = {}.", car.getQuantity());
            carRepository.save(car);
        }
        log.debug("Mapping RequestDto to Request");
        Request request = requestMapper.fromDto(requestDto);
        log.debug("Saving and returning request.");
        return requestMapper.toDto(requestRepository.save(request));
    }

    @Override
    public void remove(Integer requestId) {
        requestRepository.deleteById(requestId);
    }

    @Override
    public List<RequestDto> getRequests() {
        return requestMapper.toListDto(requestRepository.findAll());
    }

    @Override
    public RequestDto getRequestById(Integer requestId) {
        return requestMapper.toDto(requestRepository.findRequestById(requestId));
    }
}
