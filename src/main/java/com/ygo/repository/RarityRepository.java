package com.ygo.repository;

import com.ygo.model.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RarityRepository extends JpaRepository<Rarity, Long> {

    Optional<Rarity> findFirstByName(String name);

    default Rarity findOrCreateByName(String name, String code) {
        if (name == null || name.isBlank()) return null;

        synchronized (this) {
            return findFirstByName(name)
                    .orElseGet(() -> save(new Rarity(null, name, code, new ArrayList<>())));
        }
    }
}
