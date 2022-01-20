package br.com.bycoderstec.desafiodev.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtilsTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T convertToObject(String json, Class<T> tClass) {

        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
