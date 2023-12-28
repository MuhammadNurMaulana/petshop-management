module com.mycompany.app_maulana {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.graphics;
    requires javafx.base;
    requires java.base;
    
    
    opens com.mycompany.app_maulana to javafx.fxml;
    exports com.mycompany.app_maulana;
}
