package com.example.doanjava.entity;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Getter


@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "invoice_date")
    private Date invoiceDate = new Date();
    @Column(name = "total")

    private Double price;
    @Column(name = "CustomerName")
    private String CustomerName;
    @Column(name = "Phone")
    private String Phone;
    @Column(name = "Address")
    private String Address;
    @Column(name = "TypePayment")
    private String TypePayment ;

    public void setTypePayment(String typePayment) {
        TypePayment = typePayment;
    }

    @Column(name = "MaDH")
    private String MaDH ;

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }



    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemInvoice> itemInvoices = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        Invoice invoice = (Invoice) o;
        return getId() != null && Objects.equals(getId(),
                invoice.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}