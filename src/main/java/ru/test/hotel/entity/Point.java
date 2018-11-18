package ru.test.hotel.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ru.test.hotel.json.PointDeserializer;
import ru.test.hotel.json.PointSerializer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@JsonSerialize(using = PointSerializer.class)
@JsonDeserialize(using = PointDeserializer.class)
public class Point {
    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LNG")
    private Double lng;
}
