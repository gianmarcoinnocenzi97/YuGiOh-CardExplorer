package com.ygo.repository;

import com.ygo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Optional<Type> findFirstByName(String name);

    default Type findOrCreateByName(String name) {
        if (name == null || name.isBlank()) return null;

        synchronized (this) {
            return findFirstByName(name)
                    .orElseGet(() -> save(new Type(null, name, new ArrayList<>())));
        }
    }
}