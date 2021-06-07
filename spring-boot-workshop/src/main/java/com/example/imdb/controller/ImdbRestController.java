package com.example.imdb.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Movie;
import com.example.imdb.service.MovieService;

@RestController
@RequestMapping("/movies")
@RequestScope 
@CrossOrigin
public class ImdbRestController {
	private MovieService movieSrv;
	
	public ImdbRestController(MovieService movieSrv) { // constructor injection
		this.movieSrv = movieSrv;
	}

	// http://localhost:2021/imdb/api/v1/movies?genre=Drama
	@GetMapping(params = "genre")
	public List<Movie> getMoviesByGenre(@RequestParam String genre){
		return movieSrv.findAllMoviesByGenre(genre)
				       .stream()
				       .sorted(Comparator.comparing(Movie::getYear))
				       .collect(Collectors.toList());
	}
}
