package com.geek.market.services;

import com.geek.market.entities.ProductType;
import com.geek.market.repositories.ProductTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductTypeService {
    private ProductTypeRepository productTypeRepository;
    public List<ProductType> findAll(){
        return productTypeRepository.findAll();
    }

}
