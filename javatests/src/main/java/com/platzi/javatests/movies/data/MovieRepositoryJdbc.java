package com.platzi.javatests.movies.data;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;

public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(long id) {
        Object[] args = { id };
        return jdbcTemplate.queryForObject("select * from movies where id = ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("select * from movies", movieMapper);
    }

    @Override
    public void addOrUpdate(Movie movie) {
        jdbcTemplate.update("insert into movies (name, minutes, genre) values (?, ?, ?)",
                movie.getName(), movie.getMinutes(), movie.getGenre().toString());
    }

    @Override
    public Collection<Movie> findByName(String name) {
        return jdbcTemplate.query("select * from movies where lower(name) like ?", movieMapper, "%" + name.toLowerCase() + "%");
    }

    // Creating a class from an Interface using lambda because RowMapper is a functional interface
    private static RowMapper<Movie> movieMapper = (resultSet, rowNum) -> new Movie(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("minutes"),
            Genre.valueOf(resultSet.getString("genre")),
            resultSet.getString("director")
    );

    /*// Creating a class from an Interface
    private static RowMapper<Movie> movieMapperr = new RowMapper<Movie>() {
        @Override
        public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("minutes"),
                    Genre.valueOf(resultSet.getString("genre"))
            );
        }
    };
    */
}
