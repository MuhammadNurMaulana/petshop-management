/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_maulana;

/**
 *
 * @author maula
 */
public class categories {
    private String id;
    private String name;
    private Double price;
    private String type;
    private String status;
    
    public categories(String id, String name, Double price, String type, String status){
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.status = status;
    }
    
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public Double getPrice(){
        return price;
    }
    
    public String getType(){
        return type;
    }
    
    public String getStatus(){
        return status;
    }
}
