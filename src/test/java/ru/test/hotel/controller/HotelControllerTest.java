package ru.test.hotel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.test.hotel.TestConfig;
import ru.test.hotel.WebAppConfig;
import ru.test.hotel.entity.Hotel;
import ru.test.hotel.entity.Point;
import ru.test.hotel.entity.Site;
import ru.test.hotel.service.HotelService;
import ru.test.hotel.validation.ValidationError;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class, TestConfig.class})
@WebAppConfiguration
public class HotelControllerTest {
    private static final String TEST_HOTEL_ID = "HO160015LO";

    @Autowired
    private HotelService hotelService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        Mockito.reset(hotelService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddHotel() throws Exception {
        Hotel hotel = buildHotel();
        when(hotelService.add(any(Hotel.class))).thenReturn(hotel);

        mockMvc.perform(post("/rest/hotels")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(hotel))
        ).andExpect(status().isOk())
                .andExpect(content().string(hotel.getId()));
    }

    @Test
    public void testAddHotelNotValid() throws Exception {
        mockMvc.perform(post("/rest/hotels")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(buildHotelNotValid()))
        ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(buildListValidationError())));
    }

    @Test
    public void testGetHotel() throws Exception {
        Hotel hotel = buildHotel();
        when(hotelService.get(hotel.getId())).thenReturn(hotel);

        mockMvc.perform(get("/rest/hotels/{id}", hotel.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(toJson(hotel)));
    }

    @Test
    public void testUpdateHotel() throws Exception {
        Hotel hotel = buildHotel();

        mockMvc.perform(put("/rest/hotels")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(hotel))
        ).andExpect(status().isOk());
    }

    @Test
    public void testDeleteHotel() throws Exception {
        mockMvc.perform(delete("/rest/hotels/{id}", TEST_HOTEL_ID))
                .andExpect(status().isOk());
    }

    private Hotel buildHotel() {
        Hotel hotel = new Hotel();
        hotel.setId(TEST_HOTEL_ID);
        hotel.setName("King Solomon Hotel");
        hotel.setCatid("HC154963ZZ");
        Point point = new Point();
        point.setLat(51.575276);
        point.setLng(-0.204098);
        hotel.setPoint(point);
        hotel.setAddr("155-159 Golders Green Rd, London NW11 9BX");
        hotel.setImg("/b3247259d5dd4989a55b326edebef7e78db5626b79cc19daf15baceabedc8552.jpg");
        Site site = new Site();
        site.setLabel("www.kingsolomonhotel.com");
        site.setUrl("http://www.kingsolomonhotel.com/");
        hotel.setSite(site);
        Set<Hotel.HotelService> services = new HashSet<>(2);
        services.add(Hotel.HotelService.fitness);
        services.add(Hotel.HotelService.restaurant);
        hotel.setServices(services);
        return hotel;
    }

    private Hotel buildHotelNotValid() {
        return new Hotel();
    }

    private List<ValidationError> buildListValidationError() {
        List<ValidationError> errors = new ArrayList<>(3);
        errors.add(new ValidationError("id", "'ID' field is required"));
        errors.add(new ValidationError("name", "'NAME' field is required"));
        errors.add(new ValidationError("addr", "'ADDRESS' field is required"));
        return errors;
    }

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
