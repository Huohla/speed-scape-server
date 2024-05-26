package com.springboot.speedscape;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository
    <ProductEntity, Long> {
        List<ProductEntity> findByNameContainsIgnoreCase(String partialName);

        List<ProductEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

        List<ProductEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName,
            String partialDescription);
}
