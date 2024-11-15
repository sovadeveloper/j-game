module com.sovadeveloper.jgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sovadeveloper.jgame to javafx.fxml;
    exports com.sovadeveloper.jgame;
}