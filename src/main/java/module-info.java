module skins.skins {

    requires javafx.controls;
    requires javafx.fxml;


    opens skins.skins to javafx.fxml;
    exports skins.skins;
}