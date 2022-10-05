package com.ecommerce.Fashion.services;


import com.ecommerce.Fashion.entity.Farm;
import com.ecommerce.Fashion.entity.Product;
import com.ecommerce.Fashion.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    FarmService farmService;

    public void saveProduct(Long id, Product product){

        Farm farm = farmService.getFarmById(id);
        if(farm == null){
            System.out.println("farm is null");
        }

        product.setFarm(farm);
        productRepository.save(product);

    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

}
