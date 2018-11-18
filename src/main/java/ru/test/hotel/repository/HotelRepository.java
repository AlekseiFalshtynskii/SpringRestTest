package ru.test.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
