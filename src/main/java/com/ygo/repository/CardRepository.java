package com.ygo.repository;

import com.ygo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CardRepository extends JpaRepository<Card, String >, JpaSpecificationExecutor<Card> {

    List<Card> findByNameInIgnoreCase(List<String> names);
    @Query("SELECT c.id FROM Card c " +
            "LEFT JOIN c.effects e " +
            "LEFT JOIN c.tags t " +
            "WHERE e IS NULL " +
            "AND t IS NULL " +
            "AND (c.frameType IS NULL OR c.frameType.name NOT IN ('Normal', 'Token', 'Skill'))")
    Set<String> findCardsWithoutEffectsAndTagsExcludingNormalTokenSkill();

}
