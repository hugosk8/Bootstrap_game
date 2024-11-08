module claude {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens claude to javafx.fxml;
    exports claude;
}
