package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Request;
import ru.example.carshowroom.data.repository.CarRepository;
import ru.example.carshowroom.data.repository.RequestRepository;
import ru.example.carshowroom.service.IRequestService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RequestServiceImpl implements IRequestService {
    private final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);
    private final RequestRepository requestRepository;
    private final CarRepository carRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, CarRepository carRepository) {
        this.requestRepository = requestRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Request save(Request request) {
        log.debug("Find car with id = {}.", request.getCar().getId());
        Car car = carRepository.findById(request.getCar().getId()).orElseThrow(() -> new EntityNotFoundException("Can't find car with id = " + request.getCar().getId()));
        if (car != null && car.getQuantity() > 0) {
            car.setQuantity(car.getQuantity() - 1);
            carRepository.save(car);
        }
        log.debug("Save request.");
        return requestRepository.save(request);
    }

    @Override
    public void remove(Integer requestId) {
        log.debug("Delete request with id = {}.", requestId);
        requestRepository.deleteById(requestId);
    }

    @Override
    public Request getRequestById(Integer requestId) {
        log.debug("Find request by id = {}.", requestId);
        return requestRepository.findById(requestId).orElseThrow(() -> new EntityNotFoundException("Can't find request with id = " + requestId));
    }

    @Override
    public List<Request> getRequests() {
        log.debug("Find all requests.");
        return requestRepository.findAll();
    }

}
