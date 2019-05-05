package com.platzi.javatests.movies.services;

import com.platzi.javatests.movies.data.MovieRepository;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;

public class MovieServiceShould {

    private MovieService movieService;

    @Before
    public void setUp() throws Exception {
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION, "Robert"),
                        new Movie(2, "Memento", 113, Genre.THRILLER, "Ruso"),
                        new Movie(3, "There's Something About Marry", 119, Genre.COMEDY, "Marry"),
                        new Movie(4, "Super 8", 112, Genre.THRILLER, "Carl"),
                        new Movie(5, "Scream", 111, Genre.HORROR, "Dennis"),
                        new Movie(6, "Home Along", 103, Genre.COMEDY, "McKauli"),
                        new Movie(7, "Matrix", 136, Genre.ACTION, "Neo")
                )
        );

        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_genre() {
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);
        assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(3, 6)));
    }

    @Test
    public void return_movies_by_length() {
        Collection<Movie> movies = movieService.findMoviesByLength(119);
        assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(2, 3, 4, 5, 6)));
    }

    private List<Integer> getMovieIds(Collection<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }

    @Test
    public void return_movies_by_template_id() {
        Movie movie = new Movie(7, null, null, null, null);
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(7, "Matrix", 136, Genre.ACTION, "Neo"))));
    }


    @Test
    public void return_movies_by_template_name() {
        Movie movie = new Movie(null, "Memento", null, null, null);
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(2, "Memento", 113, Genre.THRILLER, "Ruso"))));

    }

    @Test
    public void return_movies_by_template_minutes() {
        Movie movie = new Movie(null, null, 152, null, null);
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(1, "Dark Knight", 152, Genre.ACTION, "Robert"))));

    }

    @Test
    public void return_movies_by_template_genre() {
        Movie movie = new Movie(null, null, null, Genre.COMEDY, null);
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(3, "There's Something About Marry", 119, Genre.COMEDY, "Marry"),
                new Movie(6, "Home Along", 103, Genre.COMEDY, "McKauli")
        )));
    }

    @Test
    public void return_movies_by_template_director() {
        Movie movie = new Movie(null, null, null, null, "carl");
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(4, "Super 8", 112, Genre.THRILLER, "Carl"))));
    }

    @Test
    public void return_movies_by_template_name_minutes_() {
        Movie movie = new Movie(null, "Scream", 110, null, null);
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(5, "Scream", 111, Genre.HORROR, "Dennis"))));

    }

    @Test
    public void return_movies_by_template_id_minutes_genre() {
        Movie movie = new Movie(null, "", 110, Genre.HORROR, null);
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(5, "Scream", 111, Genre.HORROR, "Dennis"))));
    }

    @Test
    public void return_movies_by_template_genre_director() {
        Movie movie = new Movie(null, "", null, Genre.COMEDY, "McKauli");
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(3, "There's Something About Marry", 119, Genre.COMEDY, "Marry"),
                new Movie(6, "Home Along", 103, Genre.COMEDY, "McKauli"))));
    }

    @Test
    public void return_movies_by_template_name_minutes_genre_director() {
        Movie movie = new Movie(null, "Scream", 11, Genre.HORROR, "Dennis");
        Collection<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertThat(movies, CoreMatchers.is(Arrays.asList(new Movie(5, "Scream", 111, Genre.HORROR, "Dennis"))));
    }
}