package com.mtb.repository;

import com.mtb.entity.ScreenDetails;
import com.mtb.entity.TheatreDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreenRepository extends CrudRepository<ScreenDetails, String> {
    public Optional<ScreenDetails> findByTheatre(TheatreDetails theatreDetails);
}
