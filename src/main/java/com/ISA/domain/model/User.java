package com.ISA.domain.model;

public class User {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String description;
    private String type;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
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
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
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
    public String getType(){
        return  type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getRole() {return type;}

}
