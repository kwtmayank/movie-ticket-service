package com.mtb.repository;

import com.mtb.entity.TheatreDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends CrudRepository<TheatreDetails, String> {

}
