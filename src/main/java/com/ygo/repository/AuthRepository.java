package com.ygo.repository;

import com.ygo.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {

    Optional<Auth> findByUserEmail(String email);

}
