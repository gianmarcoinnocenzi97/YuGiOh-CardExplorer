package com.ygo.repository;

import com.ygo.model.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rarityepository extends JpaRepository<Rarity, Long> {


}
