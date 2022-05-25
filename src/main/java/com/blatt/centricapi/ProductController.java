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


    // Aggregate root
    // tag::get-aggregate-root[]
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
    // end::get-aggregate-root[]

    @PostMapping("/v1/products")
    Product newProduct(@RequestBody Product newProduct) {
        newProduct.setCreated_at();
        return repository.save(newProduct);
    }


    public static<T> List<T> pagedList(List<T> list, int start, int end)
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
