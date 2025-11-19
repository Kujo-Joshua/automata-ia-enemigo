module com.hotline {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hotline to javafx.fxml;
    exports com.hotline;
}
