module com.trust {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens com.trust to javafx.fxml;

    exports com.trust;
}
