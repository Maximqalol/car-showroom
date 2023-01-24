package ru.example.carshowroom;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.carshowroom.data.entity.Car;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class CarShowroomApplicationTests {
    private static final Car car = Mockito.mock(Car.class);
    private static final String brand = "Mercedes";

    @Test
    public void whenGetCarBrand_thenReturnsBrand() {
        Mockito.when(car.getBrand()).thenReturn(brand);
        assertEquals(brand, car.getBrand());
    }

}
