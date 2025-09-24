package com.ygo.repository;

import com.ygo.model.EffectTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EffectTagRepository extends JpaRepository<EffectTag, Long> {

    @Query("SELECT e FROM EffectTag e WHERE e.name IN :names")
    Set<EffectTag> findByNames(@Param("names") List<String> names);}
