package com.geek.market.repositories;

import com.geek.market.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    Page<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(Pageable page, int max, int min);

    Page<Product> findByPriceGreaterThanEqual(Pageable page,int max);

    Page<Product> findByPriceLessThanEqual(Pageable page, int min);
}
