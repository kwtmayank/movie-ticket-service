package com.mtb.repository;

import com.mtb.entity.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends MongoRepository<com.mtb.entity.Genre, String> {

    public Optional<Genre> findByGenreName(String name);
}
