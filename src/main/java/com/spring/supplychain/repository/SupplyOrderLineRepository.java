package com.spring.supplychain.repository;

import com.spring.supplychain.model.approvisionnement.SupplyOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyOrderLineRepository extends JpaRepository<SupplyOrderLine, Long> {
}
