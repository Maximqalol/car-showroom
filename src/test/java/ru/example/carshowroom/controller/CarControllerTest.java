package ru.example.carshowroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Spy
    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    @Mock
    private ICarService carService;

    @InjectMocks
    private CarController carController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void whenSaveCar_thenCarSaved() throws Exception {
        Car car = createCarWithoutId();
        CarDto carDto = carMapper.toDto(car);
        when(carService.save(any(Car.class))).thenAnswer(I -> {
           car.setId(1);
           return car;
        });
        mockMvc.perform(post("/api/car")
                        .content(objectMapper.writeValueAsString(carDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteCar_thenCarDeleted() throws Exception {
        mockMvc.perform(delete("/api/car/{carId}", 1337)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(carService).remove(1337);
    }

    @Test
    public void whenEditCar_thenCarFound() throws Exception {
        Car car = createCarWithoutId();
        car.setId(1);
        CarDto carDto = carMapper.toDto(car);
        when(carService.save(any(Car.class))).thenReturn(car);
        mockMvc.perform(put("/api/car/{carId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDto))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

    }

    @Test
    public void whenGetOneCar_thenOneCarReturned() throws Exception {
        Car car = createCarWithoutId();
        car.setId(1);
        CarDto carDto = carMapper.toDto(car);
        when(carService.findCarById(car.getId())).thenReturn(car);
        mockMvc.perform(get("/api/car/{carId}", car.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(objectMapper.writeValueAsString(carDto)))
                        .andReturn();
    }

    @Test
    public void whenGetAllCars_thenAllCarsReturned() throws Exception {
        Car car1 = createCarWithoutId();
        car1.setId(1);
        Car car2 = createCarWithoutId();
        car2.setId(2);
        List<Car> carList = List.of(car1, car2);
        List<CarDto> carDtoList = carList.stream().map(carMapper::toDto).collect(Collectors.toList());
        when(carService.getAllCars()).thenReturn(carList);
        mockMvc.perform(get("/api/car/all")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(carDtoList)))
                        .andReturn();

        verifyNoMoreInteractions(carService);
    }

    @Test
    public void whenGetAvailableCars_thenAvailableCarsReturned() throws Exception {
        Car car1 = createCarWithoutId();
        car1.setId(1);
        Car car2 = createCarWithoutId();
        car2.setId(2);
        List<Car> carList = List.of(car1, car2);
        List<CarDto> carDtoList = carList.stream().map(carMapper::toDto).collect(Collectors.toList());
        when(carService.getAvailableCars()).thenReturn(carList);
        mockMvc.perform(get("/api/car/all/available")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().json(objectMapper.writeValueAsString(carDtoList)))
                        .andExpect(status().isOk())
                        .andReturn();

        verifyNoMoreInteractions(carService);
    }

    private Car createCarWithoutId() {
        Producer producer = new Producer(1, "Some name", "Some address", "Some phone");
        Car car = new Car();
        car.setBrand("BMW");
        car.setModel("535");
        car.setYear(2012);
        car.setPrice(2450000);
        car.setProducer(producer);
        car.setQuantity(2);
        car.setFeatures("features");
        return car;
    }

}
