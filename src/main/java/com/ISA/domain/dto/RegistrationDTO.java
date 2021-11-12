package com.ISA.domain.dto;

public class RegistrationDTO {

    private String name;
    private String surname;
    private String email;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String description;
    private String type;
    private String ownerType;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getAddress(){
        return  address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public  String getPhoneNumber(){
        return  phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public  String getDescription(){
        return  description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getOwnerType(){
        return  ownerType;
    }
    public void setOwnerType(String ownerType){
        this.ownerType = ownerType;
    }
}
