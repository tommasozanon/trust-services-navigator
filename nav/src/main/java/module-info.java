module com.trust {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires javafx.base;
    requires javafx.graphics;

    opens com.trust to javafx.fxml;

    exports com.trust;
}
