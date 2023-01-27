package ru.example.carshowroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.mapper.CarMapper;
import ru.example.carshowroom.service.ICarService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICarService carService;

    @MockBean
    private CarMapper carMapper;

    @Test
    public void when_carService_create_carDto_then_return_status_200() throws Exception {
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, 2);
        mockMvc.perform(post("/api/car")
                        .content(asJsonString(carDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void when_carService_removeCar_then_return_status_200() throws Exception {
        CarDto carDto = new CarDto(1, "BMW", "M5 F10", "some features", 2015, 7700000, 3, 2);
        mockMvc.perform(delete("/api/car/{carId}", carDto.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void when_carService_updateCar_then_return_status_200() throws Exception {
        CarDto carDto = new CarDto(1, "BMW", "M5 F10", "some features", 2015, 7700000, 3, 2);
        mockMvc.perform(put("/api/car")
                .content(asJsonString(carDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void when_carService_findCarById_then_return_founded_carDto() throws Exception {
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, 2);
        when(carService.findCarById(carDto.getId())).thenReturn(carDto);
        mockMvc.perform(get("/api/car/{carId}", carDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(carDto)))
                .andReturn();
    }

    @Test
    public void when_carService_getAllCars_then_return_carDtoList() throws Exception {
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, 2);
        CarDto carDto2 = new CarDto(2, "Mercedes", "A45 AMG", "some features", 2019, 5700000, 2, 1);
        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(carDto);
        carDtoList.add(carDto2);
        when(carService.getAllCars()).thenReturn(carDtoList);
        mockMvc.perform(get("/api/car/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(carDtoList)))
                .andReturn();
    }

    @Test
    public void when_carService_getAvailableCars_then_return_carDtoList() throws Exception {
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, 2);
        CarDto carDto2 = new CarDto(2, "Mercedes", "A45 AMG", "some features", 2019, 5700000, 2, 1);
        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(carDto);
        carDtoList.add(carDto2);
        when(carService.getAvailableCars()).thenReturn(carDtoList);
        mockMvc.perform(get("/api/car/all/available").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(carDtoList)))
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
