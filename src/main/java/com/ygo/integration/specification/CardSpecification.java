package com.ygo.integration.specification;


import com.ygo.model.Card;
import com.ygo.model.critiria.CardCriteria;
import org.springframework.data.jpa.domain.Specification;

public interface CardSpecification {

    Specification<Card> getSpecification(CardCriteria criteria);
}
