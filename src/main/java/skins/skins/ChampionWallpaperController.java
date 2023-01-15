package skins.skins;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private ObjectMapper mapper;

    List<String> skinName;
    List<Integer> numSkin;
    String dynamicKey;

    public void initialize(){

        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // Add a list of champions to the first ComboBox
        championComboBox.getItems().addAll(
                "Aatrox", "Ahri", "Akali", "Akshan", "Alistar", "Amumu", "Anivia", "Annie", "Aphelios", "Ashe",
                "Aurelion Sol", "Azir", "Bard", "Bel'Veth", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Camille",
                "Cassiopeia", "Cho'Gath", "Corki", "Darius", "Diana", "Dr.Mundo", "Draven", "Ekko", "Elise", "Evelynn",
                "Ezreal", "Fiddlesticks", "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves",
                "Graves", "Gwen", "Hecarim", "Heimerdinger", "Illaoi", "Irelia", "Ivern", "Janna", "Jarvan IV", "Jax",
                "Jayce", "Jhin", "Jinx", "K'Sante", "Kai'sa", "Kalista", "Karma", "Karthus", "Kassadin", "Katarina",
                "Kayle", "Kayle", "Kayn", "Kennen", "Kha'Zix", "Kindred", "Kled", "Kog'Maw", "Leblanc", "Lee Sin",
                "Leona", "Lillia", "Lissandra", "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai", "Master Yi",
                "Miss Fortune", "Mordekaiser", "Morgana", "Nami", "Nasus", "Nautilus", "Neeko", "Nidalee", "Nilah",
                "Nocturne", "Nunu", "Olaf", "Orianna", "Ornn", "Pantheon", "Poppy", "Pyke", "Qiyana", "Quinn", "Rakan",
                "Rammus", "Rek'Sai", "Rell", "Renata Glasc", "Renekton", "Riven", "Rumble", "Ryze", "Samira", "Sejuani",
                "Senna", "Seraphine", "Sett", "Shaco", "Shen", "Shyvama", "Singed", "Sion", "Sivir", "Skaner", "Sona",
                "Soraka", "Swain", "Sylas", "Syndra", "Tahm Kench", "Taliyah", "Talon", "Taric", "Teemo", "Thresh",
                "Tristana", "Trundle", "Tryndamere", "Twisted Fate", "Twitch", "Udyr", "Urgot", "Varus", "Vayne",
                "Veigar", "Vel'Koz", "Vex", "Vi", "Viego", "Viktor", "Vladimir", "Volibear", "Warwick", "Wukong",
                "Xayah", "Xerath", "Xin Zhao", "Yasuo", "Yone", "Yorick", "Yuumi", "Zac", "Zed", "Zeri", "Ziggs",
                "Zilan", "Zoe", "Zyra");


        // Add a change listener to the first ComboBox to update the second ComboBox when a selection is made
        championComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {

            skinComboBox.getItems().clear();
            try {
                skinList().forEach(skin -> skinComboBox.getItems().addAll(skin));
            }
            catch(IOException e) {
                throw new RuntimeException(e);
            }
        });

        skinComboBox.setOnAction(event ->{

            String skin = skinComboBox.getValue();
            linkHyperlink.setText(skin);
            linkHyperlink.setOnAction(click -> {
                try {
                    hostServices.showDocument(getSkinUrl());
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    public List<String> skinList() throws IOException {

        skinName = new ArrayList<>();
        for(JsonNode skinNode : getSkinNode()) {
            skinName.add(skinNode.path("name").asText());
        }
        return skinName;
    }

    public List<Integer> skinNumberList() throws IOException {

        numSkin = new ArrayList<>();
        for(JsonNode skinNode : getSkinNode()) {
            numSkin.add(skinNode.path("num").asInt());
        }
        return numSkin;
    }

    public int correspondingIntSkinNumber()throws IOException{

        int number = 0;
        for(int i = 0; i < skinList().size(); i++) {
            if(skinList().get(i).equals(skinComboBox.getValue())) {
                number = i;
                break;
            }
        }
        int correspondingInt = skinNumberList().get(number);
        return  correspondingInt;
    }

    public String getSkinUrl() throws IOException {
        String url = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" +
                getDynamicKey() + "_" + correspondingIntSkinNumber() +".jpg";
        return url;
    }

    public JsonNode getSkinNode() throws IOException {
        JsonNode root = mapper.readTree(getChampionURL());
        JsonNode skinsNode = root.path("data").path(getDynamicKey()).path("skins");
        return skinsNode;
    }

    public URL getChampionURL() throws MalformedURLException {
        URL url = new URL(
                "http://ddragon.leagueoflegends.com/cdn/12.23.1/data/en_US/champion/" + getDynamicKey() + ".json");
        return url;
    }

    public String getDynamicKey(){
        this.dynamicKey = championComboBox.getValue();
        dynamicKey = String.join("",dynamicKey.split("\\s|'"));
        return dynamicKey;
    }

    public void setGetHostController(HostServices hostServices) {
        this.hostServices = hostServices;
    }

}





