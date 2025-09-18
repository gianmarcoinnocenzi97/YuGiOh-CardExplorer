package com.ygo.integration.specification.filter;
import com.ygo.integration.specification.CardSpecification;
import com.ygo.model.Card;
import com.ygo.model.critiria.CardCriteria;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CardSpecificationFilter implements CardSpecification {

    public Specification<Card> getSpecification(CardCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getIds() != null && !criteria.getIds().isEmpty()) {
                predicates.add(root.get("id").in(criteria.getIds()));
            }

            if (Objects.nonNull(criteria.getName())) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
            }

            if (Objects.nonNull(criteria.getType())) {
                predicates.add(cb.equal(root.get("type"), criteria.getType()));
            }

            if (Objects.nonNull(criteria.getRace())) {
                predicates.add(cb.equal(root.get("race"), criteria.getRace()));
            }

            if (criteria.getArchetypes() != null && !criteria.getArchetypes().isEmpty()) {
                predicates.add(root.get("archetype").in(criteria.getArchetypes()));
            }

            if (criteria.getAttributes() != null && !criteria.getAttributes().isEmpty()) {
                predicates.add(root.get("attribute").in(criteria.getAttributes()));
            }

            if (criteria.getFrameTypes() != null && !criteria.getFrameTypes().isEmpty()) {
                predicates.add(root.get("frameType").in(criteria.getFrameTypes()));
            }

            if (criteria.getAtkMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("atk"), criteria.getAtkMin()));
            }

            if (criteria.getDefMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("def"), criteria.getDefMin()));
            }

            // Level: exact or range
            if (criteria.getLevel() != null) {
                predicates.add(cb.equal(root.get("level"), criteria.getLevel()));
            } else {
                if (criteria.getLevelMin() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("level"), criteria.getLevelMin()));
                }
                if (criteria.getLevelMax() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("level"), criteria.getLevelMax()));
                }
            }

            // Rank
            if (criteria.getRank() != null) {
                predicates.add(cb.equal(root.get("rank"), criteria.getRank()));
            } else {
                if (criteria.getRankMin() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("rank"), criteria.getRankMin()));
                }
                if (criteria.getRankMax() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("rank"), criteria.getRankMax()));
                }
            }

            // LinkVal
            if (criteria.getLinkval() != null) {
                predicates.add(cb.equal(root.get("linkVal"), criteria.getLinkval()));
            } else {
                if (criteria.getLinkvalMin() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("linkVal"), criteria.getLinkvalMin()));
                }
                if (criteria.getLinkvalMax() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("linkVal"), criteria.getLinkvalMax()));
                }
            }

            // TODO: effCats con join su tabella effetti (qui serve mapping tra Card ed Effect)

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

