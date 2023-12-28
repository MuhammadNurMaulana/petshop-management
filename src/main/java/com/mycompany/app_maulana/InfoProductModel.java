/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_maulana;

/**
 *
 * @author maula
 */
import java.util.Date;

public class InfoProductModel {
    private int id;
    private int custId;
    private double productTotal;
    private Date productDate;

    public InfoProductModel(int id, int custId, double productTotal, Date productDate) {
        this.id = id;
        this.custId = custId;
        this.productTotal = productTotal;
        this.productDate = productDate;
    }

    public int getId() {
        return id;
    }

    public int getCustId() {
        return custId;
    }

    public double getProductTotal() {
        return productTotal;
    }

    public Date getProductDate() {
        return productDate;
    }
}

