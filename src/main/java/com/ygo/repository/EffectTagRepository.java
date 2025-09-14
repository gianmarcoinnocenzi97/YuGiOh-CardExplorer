package com.ygo.repository;

import com.ygo.model.EffectTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EffectTagRepository extends JpaRepository<EffectTag, Long> {


}
