package com.example.doanjava.entity;

import com.example.doanjava.Validator.annotation.ValidUserId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class product {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "Description")
    private String Description;
    @Column(name = "price")
    private Double price;
    @Column(name ="Image")
    private String Image;
    @Column(name ="Quantity")
    private Integer Quantity;
    @Column(name="Isactive")
    private Boolean Isactive;
    @ManyToOne
    @JoinColumn(name = "category_id" )
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemInvoice> itemInvoices = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @ValidUserId
    private User user;


    public product(){}
    public product(Long id, String title, String description, Double price, String image, Integer quantity, Boolean isactive, Category category) {
        Id = id;
        this.title = title;
        Description = description;
        this.price = price;
        Image = image;
        Quantity = quantity;
        Isactive = isactive;
        this.category = category;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Boolean getIsactive() {
        return Isactive;
    }

    public void setIsactive(Boolean isactive) {
        Isactive = isactive;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
