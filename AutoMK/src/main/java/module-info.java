module org.example.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;


    opens org.example.server to javafx.fxml;
    exports org.example.server;
}