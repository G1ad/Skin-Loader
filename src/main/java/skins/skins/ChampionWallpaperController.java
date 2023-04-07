package skins.skins;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.HostServices;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ChampionWallpaperController {

    HostServices hostServices;

    @FXML
    private Hyperlink linkHyperlink;

    @FXML
    private Button downloadButton;

    @FXML
    private ComboBox<String> championComboBox;

    @FXML
    private ComboBox<String> skinComboBox;

    private static final String DOWNLOAD_DIRECTORY = "D:\\Download app fx";

    private List<String> champions;

    private ObjectMapper mapper;

    private String dynamicKey;

    public void initialize() throws IOException{

        champions = Files.readAllLines(Paths.get("Champions.txt"));
        // Add the list of champions to the first ComboBox
        champions.forEach(champion -> championComboBox.getItems().addAll(champion));

        championComboBox.setOnAction(event -> {
            String selectedChampion = championComboBox.getSelectionModel().getSelectedItem();
            if(selectedChampion !=null){
                skinComboBox.setDisable(false);
                skinComboBox.setPromptText("Select Skin");
                skinComboBox.getItems().clear();
            }else{
                skinComboBox.setDisable(true);
            }
            getSkinListInNewThread();
        });


        skinComboBox.setOnAction(event ->{
            String selectedSkin = skinComboBox.getSelectionModel().getSelectedItem();
            if (selectedSkin != null) {
                downloadButton.setDisable(false);
                linkHyperlink.setDisable(false);
            }
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

    @FXML
    public void handleDownloadButtonClick()throws IOException {
        downloadFile(getSkinUrl());
    }

    private void getSkinListInNewThread(){
        Task<ObservableList<String>> task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                ObservableList<String> skins = FXCollections.observableArrayList();
                for(Map.Entry<String,Integer> entry : skinMap().entrySet()){
                    String skinName = entry.getKey();
                    skins.add(skinName);
                }
                return skins;
            }
        };
        task.setOnSucceeded(e -> skinComboBox.setItems(task.getValue()));
        System.out.println("ciao");
        new Thread(task).start();
    }

    private Map<String,Integer> skinMap() throws IOException {
        Map<String, Integer> skinsMap = new HashMap<>();

        for(JsonNode skinNode : getSkinNode()){
            String skinName =  skinNode.get("name").asText();
            int id = skinNode.get("num").asInt();
            skinsMap.put(skinName,id);
        }
        return skinsMap;
    }

    private String getSkinUrl() throws IOException {

        String skinName = skinComboBox.getValue();
        int id = skinMap().get(skinName);

        String url = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" +
                getDynamicKey() + "_" + id +".jpg";
        return url;
    }

    private JsonNode getSkinNode() throws IOException {
        mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(getChampionURL());
        JsonNode skinsNode = root.path("data").path(getDynamicKey()).path("skins");
        return skinsNode;
    }

    private URL getChampionURL() throws MalformedURLException {
        URL url = new URL(
                "http://ddragon.leagueoflegends.com/cdn/12.23.1/data/en_US/champion/" + getDynamicKey() + ".json");
        return url;
    }

    private String getDynamicKey(){
        this.dynamicKey = championComboBox.getValue();
        dynamicKey = String.join("",dynamicKey.split("\\s|'"));
        return dynamicKey;
    }

    public void setGetHostController(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    private void downloadFile(String fileUrl){
        try {
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            connection.connect();
            String fileName = skinComboBox.getValue() + ".png";

            FileChooser fileChooser = new FileChooser();
            String defaultDownloadDir = System.getProperty("user.home") + "/Downloads";
            File initialDir = new File(defaultDownloadDir);
            fileChooser.setInitialDirectory(initialDir);

            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            fileChooser.setInitialFileName(getValidFileName(fileName));
            File selectedFile = fileChooser.showSaveDialog(null);
            if(selectedFile == null){
                return;
            }

            Path downloadPath = selectedFile.toPath();
            FileOutputStream outputStream = new FileOutputStream(downloadPath.toFile());
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int length;
            while((length = inputStream.read(buffer)) !=-1){
                outputStream.write(buffer,0,length);
            }
            outputStream.close();
            inputStream.close();
            System.out.println("Downloaded file: " + downloadPath);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private String getValidFileName(String fileName) {
        String invalidChars = "[\\\\/:*?\"<>|]";
        String cleanFileName = fileName.replaceAll(invalidChars, "");
        return cleanFileName;
    }

}





