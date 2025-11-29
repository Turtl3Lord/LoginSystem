package com.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BackEnd.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findById(String id);

    User save(User user);

    User saveAndFlush(User user);

    void deleteById(String id);

}
