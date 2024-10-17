package com.innruptive.movies.core.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
// postgres dockerben
// java app dockerben
// dockercompose
class MovieTest {

    @Test
    void canEqual() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Movie movie = objectMapper.readValue("{\"imdbId\": true, \"id\": 123}", Movie.class);
        System.out.println(movie);
    }
}