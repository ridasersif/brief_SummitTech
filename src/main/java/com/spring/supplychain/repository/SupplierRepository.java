package com.spring.supplychain.repository;

import com.spring.supplychain.model.approvisionnement.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    boolean existsByEmail(String email);
}
