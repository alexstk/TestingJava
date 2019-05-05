package com.platzi.javatests.movies.services;

import com.platzi.javatests.movies.data.MovieRepository;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {
        Collection<Movie> allMovies = movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre)
                .collect(Collectors.toList());
        return allMovies;
    }

    public Collection<Movie> findMoviesByLength(int length) {
        Collection<Movie> allMovies = movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= length)
                .collect(Collectors.toList());
        return allMovies;
    }

    public Collection<Movie> findMoviesByTemplate(Movie template) {
        Stream<Movie> stream = movieRepository.findAll().stream();

        if (template.getId() != null) {
            return stream
                    .filter(movie -> movie.getId() == template.getId())
                    .collect(Collectors.toList());
        }

        if (StringUtils.isNoneEmpty(template.getName())
                && template.getMinutes() != null
                && template.getGenre() != null
                && StringUtils.isNoneEmpty(template.getDirector())) {
            return stream
                    .filter(movie ->
                            movie.getName().equalsIgnoreCase(template.getName()) ||
                                    movie.getMinutes() == template.getMinutes() ||
                                    movie.getGenre().equals(template.getGenre()) ||
                                    movie.getDirector().equalsIgnoreCase(template.getDirector()))
                    .collect(Collectors.toList());
        }

        if (template.getMinutes() != null
                && template.getGenre() != null
                && StringUtils.isNoneEmpty(template.getDirector())) {
            return stream
                    .filter(movie ->
                            movie.getMinutes() == template.getMinutes() ||
                                    movie.getGenre().equals(template.getGenre()) ||
                                    movie.getDirector().equalsIgnoreCase(template.getDirector()))
                    .collect(Collectors.toList());
        }

        if (StringUtils.isNoneEmpty(template.getName())
                && template.getMinutes() != null) {
            return stream
                    .filter(movie ->
                            movie.getName().equalsIgnoreCase(template.getName()) ||
                                    movie.getMinutes() == template.getMinutes())
                    .collect(Collectors.toList());
        }

        if (template.getGenre() != null
                && StringUtils.isNoneEmpty(template.getDirector())) {
            return stream
                    .filter(movie ->
                            movie.getGenre().equals(template.getGenre()) ||
                                    movie.getDirector().equalsIgnoreCase(template.getDirector()))
                    .collect(Collectors.toList());
        }

        if (template.getMinutes() != null
                && template.getGenre() != null) {
            return stream
                    .filter(movie ->
                            movie.getMinutes() == template.getMinutes() ||
                                    movie.getGenre().equals(template.getGenre()))
                    .collect(Collectors.toList());
        }

        if (template.getGenre() != null
                && StringUtils.isNoneEmpty(template.getDirector())) {
            return stream
                    .filter(movie ->
                            movie.getGenre().equals(template.getGenre()) ||
                                    movie.getDirector().equalsIgnoreCase(template.getDirector()))
                    .collect(Collectors.toList());
        }

        if (StringUtils.isNoneEmpty(template.getDirector())) {
            return stream
                    .filter(movie -> movie.getDirector().equalsIgnoreCase(template.getDirector()))
                    .collect(Collectors.toList());
        }

        if (template.getGenre() != null) {
            return stream
                    .filter(movie -> movie.getGenre().equals(template.getGenre()))
                    .collect(Collectors.toList());
        }

        if (template.getMinutes() != null && template.getMinutes() > 0) {
            return stream
                    .filter(movie -> movie.getMinutes().equals(template.getMinutes()))
                    .collect(Collectors.toList());
        }

        if (StringUtils.isNoneEmpty(template.getName())) {
            return stream
                    .filter(movie -> movie.getName().equalsIgnoreCase(template.getName()))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
