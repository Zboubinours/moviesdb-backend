package fr.technocrats.greenitbackend.movies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieDao extends CrudRepository<Movie, Integer> {

    @Transactional
    default void addMovieList(List<Movie> movieList) {
        movieList.forEach(movie -> {
            movie.setId(null); // force id to null, so that Hibernate inserts a new element
            this.save(movie);
        });
    }

    @Transactional
    List<Movie> findAll();

    @Transactional
    Optional<Movie> findById(Integer id);
}
