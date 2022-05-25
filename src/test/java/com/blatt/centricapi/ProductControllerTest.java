package com.blatt.centricapi;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class ProductControllerTest {

    @Test
    public void testAllWithMatchingCategory(){
        LocalRepo repository = Mockito.mock(LocalRepo.class);
        ProductController productController = new ProductController(repository);

        List<Product> productList = new ArrayList<>();


        Product prod1 = new Product("Shirt1", "Red hugo boss shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "apparel");
        Product prod2 = new Product("Shirt2", "1 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "app");
        Product prod3 = new Product("Shirt3", "2 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "apparel");
        Product prod4 = new Product("Shirt4", "3 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "app");

        productList.add(prod1);
        productList.add(prod2);
        productList.add(prod3);
        productList.add(prod4);

        Mockito.when(repository.findAll()).thenReturn(productList);
        List<Product> returnList = productController.all("apparel", 1, 10);

        Assert.assertEquals(returnList.size(), 2);
        Assert.assertEquals(returnList.get(0).getName(), prod3.getName());
        Assert.assertEquals(returnList.get(1).getName(), prod1.getName());
        Assert.assertEquals(returnList.get(0).getCategory(), "apparel");

    }

    @Test
    public void testAllWithNoMatchCategory(){
        LocalRepo repository = Mockito.mock(LocalRepo.class);
        ProductController productController = new ProductController(repository);

        List<Product> productList = new ArrayList<>();


        Product prod1 = new Product("Shirt1", "Red hugo boss shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "apparel");
        Product prod2 = new Product("Shirt2", "1 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "app");
        Product prod3 = new Product("Shirt3", "2 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "apparel");
        Product prod4 = new Product("Shirt4", "3 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "app");

        productList.add(prod1);
        productList.add(prod2);
        productList.add(prod3);
        productList.add(prod4);

        Mockito.when(repository.findAll()).thenReturn(productList);
        List<Product> returnList = productController.all("noMatch", 1, 10);

        Assert.assertTrue("Returned list should be empty as no matches were found", returnList.isEmpty());

    }


    @Test
    public void testAllWithPages(){
        LocalRepo repository = Mockito.mock(LocalRepo.class);
        ProductController productController = new ProductController(repository);

        List<Product> productList = new ArrayList<>();


        Product prod1 = new Product("Shirt1", "Red hugo boss shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "apparel");
        Product prod2 = new Product("Shirt2", "1 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "app");
        Product prod3 = new Product("Shirt3", "2 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "apparel");
        Product prod4 = new Product("Shirt4", "3 shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "app");

        productList.add(prod1);
        productList.add(prod2);
        productList.add(prod3);
        productList.add(prod4);

        Mockito.when(repository.findAll()).thenReturn(productList);
        List<Product> returnList = productController.all("", 2, 2);

        Assert.assertEquals(returnList.size(), 2);
        Assert.assertEquals(returnList.get(0).getName(), prod2.getName());
        Assert.assertEquals(returnList.get(1).getName(), prod1.getName());
        Assert.assertEquals(returnList.get(0).getCategory(), prod2.getCategory());
        Assert.assertEquals(returnList.get(1).getCategory(), prod1.getCategory());


    }

    @Test
    public void testNewProduct(){
        LocalRepo repository = Mockito.mock(LocalRepo.class);
        ProductController productController = new ProductController(repository);

        Product newProduct = new Product("Red Shirt", "Red hugo boss shirt", "Hugo Boss", new String[]{"red", "shirt", "slim fit"}, "apparel");

        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(newProduct);
        Product returnProduct = productController.newProduct(newProduct);

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        String dateReturned = df.format(new Date());

        Assert.assertEquals(returnProduct.getName(), returnProduct.getName());
        Assert.assertEquals(returnProduct.getCreated_at(), dateReturned);

    }
}
