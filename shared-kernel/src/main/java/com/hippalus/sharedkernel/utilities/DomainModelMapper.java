package com.hippalus.sharedkernel.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.io.InputStream;

public final class DomainModelMapper {
    private DomainModelMapper() {
        //DO NOTING
    }

    private static ObjectMapper mapper;

    private static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.registerModule(new JavaTimeModule());
            mapper.registerModule(new ParameterNamesModule());
            mapper.registerModule(new Jdk8Module());
        }
        return mapper;
    }

    public static String writeToJsonString(Object object) {
        String result = null;
        try {
            result = getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T readValue(String json, Class<T> valueType) {
        T result = null;
        try {
            result = getMapper().readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T readValue(InputStream inputStream, Class<T> valueType) {
        T result = null;
        try {
            result = getMapper().readValue(inputStream, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}