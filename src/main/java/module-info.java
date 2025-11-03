module com.simpleapp.miniproject3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.simpleapp.miniproject3 to javafx.fxml;
    exports com.simpleapp.miniproject3;
}