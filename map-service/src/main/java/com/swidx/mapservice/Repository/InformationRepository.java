package com.swidx.mapservice.Repository;

import com.swidx.mapservice.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformationRepository extends JpaRepository<InformationEntity,Integer>{

    List<InformationEntity> findByNameLike(String name);
}
