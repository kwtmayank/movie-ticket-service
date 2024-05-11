package com.mtb.repository;

import com.mtb.entity.Theatres;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends MongoRepository<Theatres, String> {

}
