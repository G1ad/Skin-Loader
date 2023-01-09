package skins.skins;

import java.util.Map;

public class URLMapper {
    private final String LINK = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
    private Map<String, Map<String,String>> urlMap;

    public URLMapper(){
        urlMap = Map.ofEntries(
                    Map.entry("Aatrox",Map.ofEntries(
                        Map.entry("default", LINK + "Aatrox" + "_0.jpg"),
                        Map.entry("Justicar Aatrox",LINK + "Aatrox" + "_1.jpg"),
                        Map.entry("Mecha Aatrox", LINK + "Aatrox" + "_2.jpg"),
                        Map.entry("Sea Hunter Aatrox", LINK +"Aatrox" + "_3.jpg"),
                        Map.entry("Blood Moon Aatrox", LINK +"Aatrox" + "_7.jpg"),
                        Map.entry("Prestige Blood Moon Aatrox", LINK + "Aatrox" + "_8.jpg"),
                        Map.entry("Victorious Aatrox", LINK + "Aatrox" + "_9.jpg"),
                        Map.entry("Odyssey Aatrox", LINK + "Aatrox" + "_11.jpg"),
                        Map.entry("Prestige Blood Moon Aatrox (2022)", LINK + "Aatrox" + "_20.jpg"),
                        Map.entry("Lunar Eclipse Aatrox", LINK + "Aatrox" + "_21.jpg")
            )),
                Map.entry("Ahri",Map.ofEntries(
                        Map.entry("default",LINK + "Ahri" + "_0.jpg"),
                        Map.entry("Dynasty Ahri",LINK + "Ahri" + "_1.jpg"),
                        Map.entry("Midnight Ahri",LINK + "Ahri" + "_2.jpg"),
                        Map.entry("Foxfire Ahri",LINK + "Ahri" + "_3.jpg"),
                        Map.entry("Popstar Ahri",LINK + "Ahri" + "_4.jpg"),
                        Map.entry("Challenger Ahri","https://image0.uhdpaper.com/wallpaper-hd/ahri-lol-challenger-splash-art-uhdpaper.com-hd-363.jpg"),
                        //Map.entry("Challenger Ahri",LINK + "Ahri" + "_5.jpg"),
                        Map.entry("Academy Ahri",LINK + "Ahri" + "_6.jpg"),
                        Map.entry("Arcade Ahri",LINK + "Ahri" + "_7.jpg"),
                        Map.entry("Star Guardian Ahri",LINK + "Ahri" + "_14.jpg"),
                        Map.entry("K/DA Ahri",LINK + "Ahri" + "_15.jpg"),
                        Map.entry("Prestige K/DA Ahri",LINK + "Ahri" + "_16.jpg"),
                        Map.entry("Elderwood Ahri",LINK + "Ahri" + "_17.jpg"),
                        Map.entry("Spirit Blossom Ahri",LINK + "Ahri" + "_27.jpg"),
                        Map.entry("K/DA ALL OUT Ahri",LINK + "Ahri" + "_28.jpg"),
                        Map.entry("Coven Ahri",LINK + "Ahri" + "_42.jpg"),
                        Map.entry("Prestige K/DA Ahri (2022)",LINK + "Ahri" + "_65.jpg"),
                        Map.entry("Arcana Ahri","https://image-1.uhdpaper.com/wallpaper/arcana-ahri-skin-splash-art-lol-hd-wallpaper-uhdpaper.com-360@1@g.jpg")
                        //Map.entry("Arcana Ahri",LINK + "Ahri" + "_66.jpg")
            )),
                Map.entry("Akali",Map.ofEntries(
                        Map.entry("default",LINK + "Akali" + "_0.jpg"),
                        Map.entry("Stinger Akali",LINK + "Akali" + "_1.jpg"),
                        Map.entry("Infernal Akali",LINK + "Akali" + "_2.jpg"),
                        Map.entry("All-star Akali",LINK + "Akali" + "_3.jpg"),
                        Map.entry("Nurse Akali",LINK + "Akali" + "_4.jpg"),
                        Map.entry("Blood Moon Akali",LINK + "Akali" + "_5.jpg"),
                        Map.entry("Silverfang Akali",LINK + "Akali" + "_6.jpg"),
                        Map.entry("Headhunter Akali",LINK + "Akali" + "_7.jpg"),
                        Map.entry("Sashimi Akali",LINK + "Akali" + "_8.jpg"),
                        Map.entry("K/DA Akali",LINK + "Akali" + "_9.jpg"),
                        Map.entry("Prestige K/DA Akali",LINK + "Akali" + "_13.jpg"),
                        Map.entry("PROJECT: Akali",LINK + "Akali" + "_14.jpg"),
                        Map.entry("True Damage Akali",LINK + "Akali" + "_15.jpg"),
                        Map.entry("K/DA ALL OUT Akali",LINK + "Akali" + "_32.jpg"),
                        Map.entry("Crime City Nightmare Akali",LINK + "Akali" + "_50.jpg"),
                        Map.entry("Prestige K/DA Akali (2022)",LINK + "Akali" + "_60.jpg"),
                        Map.entry("Star Guardian Akali",LINK + "Akali" + "_61.jpg")
                ))




        );
    }

    public Map<String, String> getOptions(String champion) {
        return urlMap.get(champion);
    }

    public String getURL(String skin, String link) {
        Map<String, String> options = urlMap.get(skin);
        return options.get(link);
    }

    public Map<String, Map<String, String>> getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(Map<String, Map<String, String>> urlMap) {
        this.urlMap = urlMap;
    }
}
