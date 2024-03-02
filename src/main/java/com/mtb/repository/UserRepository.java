package com.mtb.repository;

import com.mtb.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email the user email.
     */
    public Optional<Users> findByEmail(String email);
}
