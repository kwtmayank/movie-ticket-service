package com.mtb.repository;

import com.mtb.entity.BookingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<BookingDetails, String> {


}
