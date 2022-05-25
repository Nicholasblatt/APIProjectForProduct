package com.blatt.centricapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "products")
class Product {

    private @Id @GeneratedValue UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "brand")
    private String brand;
    @Column(name = "tags")
    private String [] tags;
    @Column(name = "category")
    private String category;
    @Column(name = "created_at")
    private String created_at;

    Product() {}

    Product(String name, String description, String brand, String [] tags, String category) {

        this.name = name;
        this.description = description;
        this.brand = brand;
        this.tags = tags;
        this.category = category;
        setCreated_at();
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getBrand(){
        return this.brand;
    }

    public String [] getTags(){
        return this.tags;
    }

    public String getCategory(){
        return this.category;
    }

    public String getCreated_at(){
        return this.created_at;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setTags(String [] tags) {
        this.tags = tags;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreated_at(){

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        this.created_at = df.format(new Date());
    }
}
