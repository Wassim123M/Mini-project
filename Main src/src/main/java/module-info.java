module com.simpleapp.miniproject3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.simpleapp.miniproject3 to javafx.fxml;
    exports com.simpleapp.miniproject3;
    exports com.simpleapp.miniproject3.db;
    opens com.simpleapp.miniproject3.db to javafx.fxml;
}