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
    public RequestDto createRequest(@RequestBody RequestDto requestDto) {
        log.info("Request to create {}.", requestDto.toString());
        return requestService.create(requestDto);

    }

    @DeleteMapping("/{requestId}")
    public void deleteRequest(@PathVariable Integer requestId) {
        if (requestService.getRequestById(requestId) == null) {
            log.error("Can't delete request with id = {}", requestId);
        }
        else {
            requestService.remove(requestId);
            log.info("Request with id = {} was deleted.", requestId);
        }
    }

    @GetMapping("/all")
    public List<RequestDto> getAllRequests() {
        List<RequestDto> requestList = requestService.getRequests();
        if (requestList.isEmpty()) {
            log.warn("List of requests is empty.");
        } else {
            log.info("Returned list of requests with size = {}.", requestList.size());
        }
        return requestList;
    }

    @GetMapping("/{requestId}")
    public RequestDto getRequestById(@PathVariable Integer requestId) {
        RequestDto requestDto = requestService.getRequestById(requestId);
        if (requestDto != null) {
            log.info("Founded request with id = {}.", requestDto.getId());
            return requestDto;
        } else {
            log.error("Can't find request with id = {}", requestId);
        }
        return null;
    }
}
