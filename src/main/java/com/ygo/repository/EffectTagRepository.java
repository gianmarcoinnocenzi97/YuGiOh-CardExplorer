package com.ygo.repository;

import com.ygo.model.EffectTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EffectTagRepository extends JpaRepository<EffectTag, Long> {

    Optional<EffectTag> findByName(String name);
}
