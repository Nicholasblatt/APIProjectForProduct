package com.blatt.centricapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
class ProductController {

    private final LocalRepo repository;

    ProductController(LocalRepo repository) {
        this.repository = repository;
    }

    // This is where GET requests are handled. and parameter values are processed
    @GetMapping("/v1/products")
    List<Product> all(
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int maxEntries

    ) {
        List<Product> products = repository.findAll();
        int firstEntry = (page - 1) * maxEntries;
        int lastEntry = (page - 1) * maxEntries + (maxEntries - 1);
        if(!category.equals("")){
            List<Product> productsWithCategories = new ArrayList<>();
            for(Product product : products){
                if(product.getCategory().equals(category)){
                    productsWithCategories.add(product);
                }
            }
            products = productsWithCategories;
        }
        Collections.reverse(products);
        products = pagedList(products, firstEntry, lastEntry);
        return products;
    }

    // This is where POST requests are handled
    @PostMapping("/v1/products")
    Product newProduct(@RequestBody Product newProduct) {
        newProduct.setCreated_at();
        return repository.save(newProduct);
    }


    // This helper function gives us a paged sublist where we request
    private static<T> List<T> pagedList(List<T> list, int start, int end)
    {
        List<T> subList = new ArrayList<>();

        if (end >= list.size()){
            end = list.size() - 1;
        }

        for (int i = start; i <= end; i++) {
            subList.add(list.get(i));
        }

        return subList;
    }
}
