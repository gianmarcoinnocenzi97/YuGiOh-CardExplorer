package com.ygo.repository;

import com.ygo.model.Archetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ArchetypeRepository extends JpaRepository<Archetype, Long> {


    Optional<Archetype> findFirstByName(String name);

    default Archetype findOrCreateByName(String name) {
        if (name == null || name.isBlank()) return null;

        synchronized (this) {
            return findFirstByName(name)
                    .orElseGet(() -> save(new Archetype(null, name, new ArrayList<>())));
        }
    }
}
