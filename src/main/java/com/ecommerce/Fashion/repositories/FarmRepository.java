package com.ecommerce.Fashion.repositories;

import com.ecommerce.Fashion.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    @Override
    Farm getById(Long id);
}
