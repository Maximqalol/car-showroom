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
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.mapper.CarMapper;
import ru.example.carshowroom.service.ICarService;

import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void returnCarTest() throws Exception {
        CarDto carDto = new CarDto();
        int carId = 1337;
        carDto.setId(carId);
        given(carService.findCarById(carId)).willReturn(carDto);

        mockMvc.perform(get("/api/car/{carId}", carId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carId))
                .andReturn();
    }

    @Test
    public void returnCarListTest() throws Exception {
        when(carMapper.toListDto(List.of(new Car(), new Car()))).thenReturn(List.of(new CarDto(), new CarDto()));
        when(carService.getAllCars()).thenReturn(List.of(new CarDto(), new CarDto()));

        mockMvc.perform(get("/api/car/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"))
                .andReturn();
    }

    @Test
    public void createCarTest() throws Exception {
        mockMvc.perform(post("/api/car")
                        .content(new ObjectMapper().writeValueAsString(new Car()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
