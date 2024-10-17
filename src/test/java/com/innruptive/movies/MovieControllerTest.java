package com.innruptive.movies;

import com.innruptive.movies.adapter.controller.MovieController;
import com.innruptive.movies.core.domain.Movie;
import com.innruptive.movies.service.MovieService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// integration test
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService movieService;

	private List<Movie> movieList;

	/**
	 *

	@BeforeEach
	public void setup() {
		// Setup some dummy movies with ObjectId and all fields from Movie class
		movieList = Arrays.asList(
				new Movie(new ObjectId(), "tt0111161", "The Shawshank Redemption", "1994-09-22",
						"https://youtube.com/trailer1", "https://poster1.com",
						Arrays.asList("backdrop1", "backdrop2"), Arrays.asList("Drama"), null),
				new Movie(new ObjectId(), "tt0068646", "The Godfather", "1972-03-24",
						"https://youtube.com/trailer2", "https://poster2.com",
						Arrays.asList("backdrop3", "backdrop4"), Arrays.asList("Crime", "Drama"), null)
		);
	}

	@Test
	public void testGetMovies() throws Exception {
		// Mock the MovieService response
		when(movieService.findAllMovies()).thenReturn(movieList);

		// Perform the GET request and expect an OK status and JSON content type
		mockMvc.perform(get("/api/v1/movies")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())  // Expect HTTP 200
				.andExpect(content().json("[{'imdbId':'tt0111161','title':'The Shawshank Redemption','releaseDate':'1994-09-22',"
						+ "'trailerLink':'https://youtube.com/trailer1','poster':'https://poster1.com','backdrops':['backdrop1','backdrop2'],"
						+ "'genres':['Drama'],'reviews':null},"
						+ "{'imdbId':'tt0068646','title':'The Godfather','releaseDate':'1972-03-24',"
						+ "'trailerLink':'https://youtube.com/trailer2','poster':'https://poster2.com','backdrops':['backdrop3','backdrop4'],"
						+ "'genres':['Crime','Drama'],'reviews':null}]"));  // Check the response body
	}

	@Test
	public void testGetSingleMovie() throws Exception {
		// Mock the MovieService response for a single movie
		Optional<Movie> movie = Optional.of(new Movie(new ObjectId(), "tt0111161", "The Shawshank Redemption", "1994-09-22",
				"https://youtube.com/trailer1", "https://poster1.com",
				Arrays.asList("backdrop1", "backdrop2"), Arrays.asList("Drama"), null));
		when(movieService.findMovieByImdbId("tt0111161")).thenReturn(movie);

		// Perform the GET request and expect an OK status and JSON content type
		mockMvc.perform(get("/api/v1/movies/tt0111161")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())  // Expect HTTP 200
				.andExpect(content().json("{'imdbId':'tt0111161','title':'The Shawshank Redemption','releaseDate':'1994-09-22',"
						+ "'trailerLink':'https://youtube.com/trailer1','poster':'https://poster1.com','backdrops':['backdrop1','backdrop2'],"
						+ "'genres':['Drama'],'reviews':null}"));  // Check the response
	}

	@Test
	public void testGetSingleMovieNotFound() throws Exception {
		// Mock MovieService to return an empty Optional for a nonexistent movie
		when(movieService.findMovieByImdbId("tt9999999")).thenReturn(Optional.empty());

		// Perform the GET request for a nonexistent movie and expect a 200 with an empty body
		mockMvc.perform(get("/api/v1/movies/tt9999999")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())  // Expect HTTP 200
				.andExpect(content().string("null"));  // Check the response body is empty
	}
	 */
}