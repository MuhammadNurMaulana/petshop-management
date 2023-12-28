/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_maulana;

/**
 *
 * @author maula
 */
public class list_product {

    private Integer id;
    private String productId;
    private String name;
    private String type;
    private Double price;
    private Integer qty;

    public list_product(Integer id, String productId, String name, String type, Double price, Integer qty) {
        
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.qty = qty;
    }

   
    public Integer getId(){
        return id;
    }
    
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }
}
