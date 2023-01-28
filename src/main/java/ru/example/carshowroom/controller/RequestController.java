package ru.example.carshowroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.RequestDto;
import ru.example.carshowroom.data.mapper.RequestMapper;
import ru.example.carshowroom.service.IRequestService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/request",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class RequestController {

    private final Logger log = LoggerFactory.getLogger(RequestController.class);
    private final RequestMapper requestMapper;
    private final IRequestService requestService;

    @Autowired
    public RequestController(RequestMapper requestMapper, IRequestService requestService) {
        this.requestMapper = requestMapper;
        this.requestService = requestService;
    }

    @PostMapping
    public void createRequest(@RequestBody RequestDto requestDto) {
        log.info("Request to create: {}.", requestDto.toString());
        requestService.save(requestMapper.fromDto(requestDto));

    }

    @DeleteMapping("/{requestId}")
    public void deleteRequest(@PathVariable Integer requestId) {
        log.info("Request with id = {} was deleted.", requestId);
        requestService.remove(requestId);
    }

    @GetMapping("/{requestId}")
    public RequestDto getRequestById(@PathVariable Integer requestId) {
        log.info("Return request with id = {}.", requestId);
        return requestMapper.toDto(requestService.getRequestById(requestId));
    }

    @GetMapping("/all")
    public List<RequestDto> getAllRequests() {
        log.info("Return list of requests.");
        return requestService.getRequests().stream().map(requestMapper::toDto).collect(Collectors.toList());
    }
}
