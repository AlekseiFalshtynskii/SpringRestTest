package ru.test.hotel.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.test.hotel.entity.Point;

import java.io.IOException;

public class PointSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(point.getLat());
        jsonGenerator.writeNumber(point.getLng());
        jsonGenerator.writeEndArray();
    }
}
