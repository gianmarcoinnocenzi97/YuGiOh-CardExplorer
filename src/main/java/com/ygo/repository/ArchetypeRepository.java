package com.ygo.repository;

import com.ygo.model.Archetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchetypeRepository extends JpaRepository<Archetype, Long> {


}
