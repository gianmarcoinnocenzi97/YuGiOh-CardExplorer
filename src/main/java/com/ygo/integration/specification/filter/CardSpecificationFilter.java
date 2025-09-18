package com.ygo.integration.specification.filter;

import com.ygo.integration.specification.CardSpecification;
import com.ygo.model.Card;
import com.ygo.model.Effect;
import com.ygo.model.critiria.CardCriteria;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CardSpecificationFilter implements CardSpecification {

    @Override
    public Specification<Card> getSpecification(CardCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // IDs
            if (criteria.getIds() != null && !criteria.getIds().isEmpty()) {
                predicates.add(root.get("id").in(criteria.getIds()));
            }

            // Name (LIKE, case insensitive)
            if (Objects.nonNull(criteria.getName())) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
            }

            // Type / Race
            if (Objects.nonNull(criteria.getType())) {
                predicates.add(cb.equal(root.get("type"), criteria.getType()));
            }
            if (Objects.nonNull(criteria.getRace())) {
                predicates.add(cb.equal(root.get("race"), criteria.getRace()));
            }

            // Archetypes / Attributes / FrameTypes (multi-value)
            if (criteria.getArchetypes() != null && !criteria.getArchetypes().isEmpty()) {
                predicates.add(root.get("archetype").in(criteria.getArchetypes()));
            }
            if (criteria.getAttributes() != null && !criteria.getAttributes().isEmpty()) {
                predicates.add(root.get("attribute").in(criteria.getAttributes()));
            }
            if (criteria.getFrameTypes() != null && !criteria.getFrameTypes().isEmpty()) {
                predicates.add(root.get("frameType").in(criteria.getFrameTypes()));
            }

            // ATK
            if (criteria.getAtkMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("atk"), criteria.getAtkMin()));
                predicates.add(cb.isNotNull(root.get("atk")));
                predicates.add(cb.notEqual(root.get("atk"), -1));
            }

            // DEF
            if (criteria.getDefMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("def"), criteria.getDefMin()));
                predicates.add(cb.isNotNull(root.get("def")));
                predicates.add(cb.notEqual(root.get("def"), -1));
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

            // Effect Categories (effCat, with optional "all" operator)
            if (criteria.getEffCats() != null && !criteria.getEffCats().isEmpty()) {
                Join<Card, Effect> effects = root.join("effects");
                predicates.add(effects.get("name").in(criteria.getEffCats()));

                if (query != null && "all".equalsIgnoreCase(criteria.getEffCatOp())) {
                    query.groupBy(root.get("id"));
                    query.having(cb.equal(cb.countDistinct(effects.get("name")), criteria.getEffCats().size()));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
