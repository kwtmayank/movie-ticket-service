package com.mtb.repository;

import com.mtb.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, String> {

    public Optional<Genre> findByGenreName(String name);
}
