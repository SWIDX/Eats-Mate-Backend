package com.swidx.mapservice.Repository;

import com.swidx.mapservice.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<InformationEntity,Integer>{


}
