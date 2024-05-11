package com.mtb.repository;

import com.mtb.entity.Screens;
import com.mtb.entity.Theatres;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreenRepository extends MongoRepository<Screens, String> {
    public Optional<Screens> findByTheatre(Theatres theatreDetails);
}
