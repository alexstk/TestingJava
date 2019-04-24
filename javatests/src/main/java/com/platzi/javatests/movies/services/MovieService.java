package com.platzi.javatests.movies.services;

import com.platzi.javatests.movies.data.MovieRepository;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {
        Collection<Movie> allMovies = movieRepository.findAll().stream().filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
        return allMovies;
    }

    public Collection<Movie> findMoviesByLength(int length) {
        Collection<Movie> allMovies = movieRepository.findAll().stream().filter(movie -> movie.getMinutes() <= length).collect(Collectors.toList());
        return allMovies;
    }
}
