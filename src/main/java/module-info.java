module com.example.demo {
    requires java.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jstk;
    requires org.apache.logging.log4j;


    opens bank to javafx.graphics;
    exports com.example.demo;
}