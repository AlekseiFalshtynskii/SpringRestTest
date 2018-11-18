package ru.test.hotel.service;

import ru.test.hotel.entity.Hotel;

public interface HotelService {
    Hotel add(Hotel hotel);
    Hotel get(String id);
    void delete(String id);
    void update(Hotel hotel);
}
