/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_maulana;

/**
 *
 * @author maula
 */
import java.sql.*;

public class database {

    public static Connection connectDb () {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/database_maulana","root" , "");

            return  connect;
        }catch (Exception e) {e.printStackTrace();}

        return null;
    }

}
