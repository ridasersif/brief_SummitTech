package com.spring.supplychain.repository;

import com.spring.supplychain.model.approvisionnement.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

}
