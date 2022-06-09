package com.onlinestore.onlinestore.dao;

import com.onlinestore.onlinestore.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDAO extends CrudRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByOrderById(Pageable pageable);
    List<Product> getByNameStartingWith(String name, Pageable pageable);
    Long countByNameStartingWith(String name);

    @Transactional
    @Modifying
    @Query("update Product p set p.name = ?1, p.description = ?2, p.price = ?3 where p.id = ?4")
    void updateNameAndDescriptionAndPriceById(String name, String description, Double price, Long id);
}