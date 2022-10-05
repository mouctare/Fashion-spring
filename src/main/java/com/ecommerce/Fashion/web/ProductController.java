package com.ecommerce.Fashion.web;

import com.ecommerce.Fashion.entity.Product;
import com.ecommerce.Fashion.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product/{id}")
    public void createProduct(@PathVariable long id, @RequestBody Product product){
        productService.saveProduct(id, product);
    }

    @GetMapping("/product")
    public List<Product> getAll(){
        return productService.getAll();
    }
}
