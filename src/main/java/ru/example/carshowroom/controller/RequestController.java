package ru.example.carshowroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.RequestDto;
import ru.example.carshowroom.service.IRequestService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/request",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class RequestController {

    private final Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private IRequestService requestService;

    @Autowired
    public RequestController(IRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public void createRequest(@RequestBody RequestDto requestDto) {
        log.info("Request to create: {}.", requestDto.toString());
        requestService.create(requestDto);

    }

    @DeleteMapping("/{requestId}")
    public void deleteRequest(@PathVariable Integer requestId) {
        log.info("Request with id = {} was deleted.", requestId);
        requestService.remove(requestId);
    }

    @PutMapping
    public void updateRequest(@RequestBody RequestDto requestDto) {
        log.info("Request to update: {}", requestDto.toString());
        requestService.update(requestDto);
    }

    @GetMapping("/{requestId}")
    public RequestDto getRequestById(@PathVariable Integer requestId) {
        log.info("Return request with id = {}.", requestId);
        return requestService.getRequestById(requestId);
    }

    @GetMapping("/all")
    public List<RequestDto> getAllRequests() {
        log.info("Return list of requests.");
        return requestService.getRequests();
    }
}
