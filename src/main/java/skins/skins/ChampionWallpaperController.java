package skins.skins;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ChampionWallpaperController {

    HostServices hostServices;

    @FXML
    private Hyperlink linkHyperlink;

    @FXML
    private ComboBox<String> championComboBox;

    @FXML
    private ComboBox<String> skinComboBox;

    private URLMapper urlMapper;
    // Create a skins options mapper


    public void initialize(){


        urlMapper = new URLMapper();
        // Add a list of champions to the first ComboBox
        championComboBox.getItems().addAll(
                "Aatrox", "Ahri", "Akali", "Akshan", "Alistar", "Amumu","Anivia","Annie","Aphelios","Ashe",
                "Aurelion Sol","Azir","Bard","Bel'Veth","Blitzcrank","Brand","Braum","Caitlyn","Camille","Cassiopeia",
                "Cho'Gath","Corki","Darius","Diana","Dr.Mundo","Draven","Ekko","Elise","Evelynn","Ezreal","Fiddlesticks",
                "Fiora","Fizz","Galio","Gangplank","Garen","Gnar","Gragas","Graves","Graves","Gwen","Hecarim","Heimerdinger",
                "Illaoi","Irelia","Ivern","Janna","Jarvan IV","Jax","Jayce","Jhin","Jinx","K'Sante","Kai'sa","Kalista",
                "Karma","Karthus","Kassadin","Katarina","Kayle","Kayle","Kayn","Kennen","Kha'Zix","Kindred","Kled",
                "Kog'Maw","Leblanc","Lee Sin","Leona","Lillia","Lissandra","Lucian","Lulu","Lux","Malphite","Malzahar",
                "Maokai","Master Yi","Miss Fortune","Mordekaiser","Morgana","Nami","Nasus","Nautilus","Neeko","Nidalee",
                "Nilah","Nocturne","Nunu & Willump","Olaf","Orianna","Ornn","Pantheon","Poppy","Pyke","Qiyana","Quinn",
                "Rakan","Rammus","Rek'Sai","Rell","Renata Glasc","Renekton","Riven","Rumble","Ryze","Samira","Sejuani",
                "Senna","Seraphine","Sett","Shaco","Shen","Shyvama","Singed","Sion","Sivir","Skaner","Sona","Soraka",
                "Swain","Sylas","Syndra","Tahm Kench","Taliyah","Talon","Taric","Teemo","Thresh","Tristana","Trundle",
                "Tryndamere","Twisted Fate","Twitch","Udyr","Urgot","Varus","Vayne","Veigar","Vel'Koz","Vex","Vi",
                "Viego","Viktor","Vladimir","Volibear","Warwick","Wukong","Xayah","Xerath","Xin Zhao","Yasuo","Yone",
                "Yorick","Yuumi","Zac","Zed","Zeri","Ziggs","Zilan","Zoe","Zyra");

        // Add a change listener to the first ComboBox to update the second ComboBox when a selection is made
        championComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the items in the second ComboBox
            skinComboBox.getItems().clear();
            // Add new items to the second ComboBox based on the selection in the first ComboBox
            Map<String,String> options = urlMapper.getOptions(championComboBox.getValue());
            skinComboBox.getItems().addAll(options.keySet());
        });

        skinComboBox.setOnAction(event -> {
            String champion = championComboBox.getValue();
            String skin = skinComboBox.getValue();
            String url = urlMapper.getURL(champion,skin);
            linkHyperlink.setText(skin);
            linkHyperlink.setOnAction(click -> {
                hostServices.showDocument(url);
            });
        });
    }

        public void setGetHostController(HostServices hostServices){
            this.hostServices = hostServices;
        }
    }




