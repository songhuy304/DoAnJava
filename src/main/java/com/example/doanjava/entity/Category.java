package com.example.doanjava.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Category")
public class Category {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "namecategory")
    private String namecategory;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<product> products;
    public Category(){

    }


    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String Image;
    @Transient
    private String completeImage;


    public Category(Long id, String namecategory, List<product> products) {
        Id = id;
        this.namecategory = namecategory;
        this.products = products;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    public List<product> getProducts() {
        return products;
    }

    public void setProducts(List<product> products) {
        this.products = products;
    }
}
