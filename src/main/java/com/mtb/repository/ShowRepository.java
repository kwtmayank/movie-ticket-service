package com.mtb.repository;

import com.mtb.entity.ShowDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends CrudRepository<ShowDetails, String> {


}
