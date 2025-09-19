package com.ygo.repository;

import com.ygo.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

    Optional<Set> findFirstByName(String name);

    default Set findOrCreateByName(String name, String code) {
        if (name == null || name.isBlank()) return null;

        synchronized (this) {
            return findFirstByName(name)
                    .orElseGet(() -> save(new Set(null, name, code, new ArrayList<>())));
        }
    }
}
