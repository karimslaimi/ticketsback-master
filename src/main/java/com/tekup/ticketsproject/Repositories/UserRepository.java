package com.tekup.ticketsproject.Repositories;

import java.util.Optional;


import com.tekup.ticketsproject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);

    Boolean existsByName(String username);

    Boolean existsByMail(String email);
}