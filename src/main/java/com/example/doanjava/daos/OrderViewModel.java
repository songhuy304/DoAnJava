package com.example.doanjava.daos;



public class OrderViewModel {

    private String CustomerName;

    private String Phone;

    private String Address;

    private Integer TypePayment ;

    public OrderViewModel() {
    }

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

    public Integer getTypePayment() {
        return TypePayment;
    }

    public void setTypePayment(Integer typePayment) {
        TypePayment = typePayment;
    }
}
