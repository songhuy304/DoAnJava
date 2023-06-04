package com.example.doanjava.daos;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class OrderViewModel {
    @NotBlank(message = "Customer name is required")
    private String CustomerName;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private String Phone;
    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
    private String Address;

    public String getTypePayment() {
        return TypePayment;
    }

    public void setTypePayment(String typePayment) {
        TypePayment = typePayment;
    }

    private String TypePayment ;

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


}
