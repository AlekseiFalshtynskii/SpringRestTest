package ru.test.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.test.hotel.entity.Hotel;
import ru.test.hotel.service.HotelService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/rest/hotels")
@ResponseBody
@ResponseStatus(HttpStatus.OK)
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(value = "/{id}")
    public Hotel get(@PathVariable String id) {
        return hotelService.get(id);
    }

    @PostMapping
    public String add(@Valid @RequestBody Hotel hotel) {
        return hotelService.add(hotel).getId();
    }

    @PutMapping
    public void update(@Valid @RequestBody Hotel hotel) {
        hotelService.update(hotel);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        hotelService.delete(id);
    }
}
