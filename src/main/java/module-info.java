module skins.skins {

    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;


    opens skins.skins to javafx.fxml;
    exports skins.skins;
}