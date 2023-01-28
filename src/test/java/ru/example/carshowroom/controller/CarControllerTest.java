package ru.example.carshowroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.mapper.CarMapper;
import ru.example.carshowroom.service.ICarService;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    private MockMvc mockMvc;

    @Spy
    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    @Mock
    private ICarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    public void whenCreateCar_thenCarSaved() throws Exception {
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, 2);
        mockMvc.perform(post("/api/car")
                        .content(asJsonString(carDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteCar_thenCarDeleted() throws Exception {
        mockMvc.perform(delete("/api/car/{carId}", 1337))
                        .andExpect(status().isOk());
        verify(carService).remove(1337);
    }

    @Test
    public void whenUpdateCar_thenCarUpdated() throws Exception {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, new Producer(1, "name", "address", "phone"));
        CarDto carDto = carMapper.toDto(car);
        carDto.setFeatures("some features123");
        mockMvc.perform(put("/api/car")
                        .content(asJsonString(carDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        verify(carService).update(any(Car.class));
    }

    @Test
    public void whenFindCarById_thenCarReturned() throws Exception {
        Producer producer = new Producer(1, "Some name", "Some address", "Some phone");
        Car car = new Car(1, "BMW", "M5 F10", "some features", 2015, 7700000, 3, producer);
        CarDto carDto = carMapper.toDto(car);
        when(carService.findCarById(car.getId())).thenReturn(car);
        mockMvc.perform(get("/api/car/{carId}", car.getId()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().json(asJsonString(carDto)))
                        .andReturn();
        verify(carService).findCarById(car.getId());
    }

    @Test
    public void whenGetAllCars_thenAllCarsReturned() throws Exception {
        Car car1 = new Car(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, new Producer(1, "name", "address", "phone"));
        Car car2 = new Car(2, "Mercedes", "A45 AMG", "some features", 2019, 5700000, 2, new Producer(2, "name", "address", "phone"));
        List<Car> carList = List.of(car1, car2);
        List<CarDto> carDtoList = carList.stream().map(carMapper::toDto).collect(Collectors.toList());
        when(carService.getAllCars()).thenReturn(carList);
        mockMvc.perform(get("/api/car/all").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().json(asJsonString(carDtoList)))
                        .andReturn();
        verifyNoMoreInteractions(carService);
    }

    @Test
    public void whenGetAvailableCars_thenAvailableCarsReturned() throws Exception {
        Car car1 = new Car(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, new Producer(1, "name", "address", "phone"));
        Car car2 = new Car(2, "Mercedes", "A45 AMG", "some features", 2019, 5700000, 3, new Producer(2, "name", "address", "phone"));
        List<Car> carList = List.of(car1, car2);
        List<CarDto> carDtoList = carList.stream().map(carMapper::toDto).collect(Collectors.toList());
        when(carService.getAvailableCars()).thenReturn(carList);
        mockMvc.perform(get("/api/car/all/available").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(carDtoList)))
                .andExpect(status().isOk())
                .andReturn();
        verifyNoMoreInteractions(carService);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
