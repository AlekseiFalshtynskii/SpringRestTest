package ru.test.hotel.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ru.test.hotel.entity.Point;

import java.io.IOException;

public class PointDeserializer extends JsonDeserializer<Point> {

    @Override
    public Point deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Point point = new Point();
        ArrayNode arrayNode = jsonParser.readValueAsTree();
        point.setLat(arrayNode.get(0).isNull() ? null : arrayNode.get(0).doubleValue());
        point.setLng(arrayNode.get(1).isNull() ? null : arrayNode.get(1).doubleValue());
        return point;
    }
}
