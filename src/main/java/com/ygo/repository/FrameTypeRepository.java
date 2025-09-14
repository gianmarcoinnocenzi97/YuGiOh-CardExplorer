package com.ygo.repository;

import com.ygo.model.FrameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameTypeRepository extends JpaRepository<FrameType, Long> {


}
