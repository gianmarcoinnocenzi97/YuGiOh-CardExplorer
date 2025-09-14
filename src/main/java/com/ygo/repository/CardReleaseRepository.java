package com.ygo.repository;

import com.ygo.model.CardRelease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardReleaseRepository extends JpaRepository<CardRelease, Long> {


}
