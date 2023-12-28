/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_maulana;

/**
 *
 * @author maula
 */
import javafx.beans.property.*;

public class CustModel {
    private final IntegerProperty custId;
    private final DoubleProperty productTotal;
    private final ObjectProperty<java.util.Date> productDate;

    public CustModel(int custId, double productTotal, java.util.Date productDate) {
        this.custId = new SimpleIntegerProperty(custId);
        this.productTotal = new SimpleDoubleProperty(productTotal);
        this.productDate = new SimpleObjectProperty<>(productDate);
    }

    // Getter dan setter sesuai kebutuhan
    public int getCustId() {
        return custId.get();
    }

    public IntegerProperty custIdProperty() {
        return custId;
    }

    public double getProductTotal() {
        return productTotal.get();
    }

    public DoubleProperty productTotalProperty() {
        return productTotal;
    }

    public java.util.Date getProductDate() {
        return productDate.get();
    }

    public ObjectProperty<java.util.Date> productDateProperty() {
        return productDate;
    }
}

