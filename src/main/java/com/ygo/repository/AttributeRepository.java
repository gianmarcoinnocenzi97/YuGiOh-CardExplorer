package com.ygo.repository;

import com.ygo.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    Optional<Attribute> findFirstByName(String name);

    default Attribute findOrCreateByName(String name) {
        if (name == null || name.isBlank()) return null;

        synchronized (this) {
            return findFirstByName(name)
                    .orElseGet(() -> save(new Attribute(null, name, new ArrayList<>())));
        }
    }
}
