package com.spring.supplychain.repository;

import com.spring.supplychain.model.approvisionnement.BillOfMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillOfMaterialRepository extends JpaRepository<BillOfMaterial, Long> {
}
