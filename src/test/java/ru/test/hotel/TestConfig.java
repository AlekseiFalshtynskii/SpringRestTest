package ru.test.hotel;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.test.hotel.service.HotelService;

@Configuration
public class TestConfig {

    @Bean
    public HotelService hotelService() {
        return Mockito.mock(HotelService.class);
    }
}
