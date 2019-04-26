package com.platzi.javatests.movies.data;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRepository;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepository = new MovieRepositoryJdbc(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {
        Collection<Movie> movies = movieRepository.findAll();

        assertThat(movies, is(Arrays.asList(
                new  Movie(1, "Dark Knight", 152, Genre.ACTION, "Robert"),
                new  Movie(2, "Memento", 113, Genre.THRILLER, "Ruso"),
                new  Movie(3, "Matrix", 136, Genre.ACTION, "Neo"),
                new  Movie(4, "Super 8", 137, Genre.ACTION, "Carl"),
                new  Movie(5, "Super Man", 139, Genre.ACTION, "Louis")
        )));
    }

    @Test
    public void load_movie_by_id() {
        Movie movie = movieRepository.findById(2);
        assertThat(movie, is(new  Movie(2, "Memento", 113, Genre.THRILLER, "Ruso")));
    }

    @Test
    public void insert_a_movie() {
        Movie movie = new Movie("Batman", 112, Genre.ACTION, "Ruppert");
        movieRepository.addOrUpdate(movie);

        Movie movieFromDb = movieRepository.findById(6);
        assertThat(movieFromDb, is(new Movie(6,"Batman", 112, Genre.ACTION, "Ruppert")));
    }

    @Test
    public void find_by_name() {
        String name = "pEr";
        Collection<Movie> actualMovies = movieRepository.findByName(name);

        Collection<Movie> expectedMovies = Arrays.asList(
                new  Movie(4, "Super 8", 137, Genre.ACTION, "Carl"),
                new  Movie(5, "Super Man", 139, Genre.ACTION, "Louis")
        );

        assertThat(actualMovies, is(expectedMovies));
    }

    @After  // Another way to avoid this is use the @BeforeClass instead of @Before an @After
    public void tearDown() throws Exception {
        Statement stmt = dataSource.getConnection().createStatement();
        stmt.execute("shutdown"); // Both work correct: "shutdown"   "drop all objects delete files"
    }
}