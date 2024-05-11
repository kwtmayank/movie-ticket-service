package com.mtb.repository;

import com.mtb.entity.Shows;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<Shows, String> {


}
