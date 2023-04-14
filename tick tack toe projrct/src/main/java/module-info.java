module com.example.ticktacktoeproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ticktacktoeproject to javafx.fxml;
    exports com.example.ticktacktoeproject;
}