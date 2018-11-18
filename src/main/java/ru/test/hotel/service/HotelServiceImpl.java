package ru.test.hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.hotel.entity.Hotel;
import ru.test.hotel.repository.HotelRepository;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel add(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public void update(Hotel hotel) {
        hotelRepository.save(hotel);
    }
}
