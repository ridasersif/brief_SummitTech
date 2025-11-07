package com.spring.supplychain.repository;

import com.spring.supplychain.model.approvisionnement.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
    boolean existsByOrderNumber(String orderNumber);
}
