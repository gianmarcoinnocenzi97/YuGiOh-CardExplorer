package com.ygo.repository;

import com.ygo.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {

    Optional<Race> findFirstByName(String name);

    default Race findOrCreateByName(String name) {
        if (name == null || name.isBlank()) return null;

        synchronized (this) {
            return findFirstByName(name)
                    .orElseGet(() -> save(new Race(null, name, new ArrayList<>())));
        }
    }
}

