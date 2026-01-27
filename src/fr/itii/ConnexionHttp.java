package fr.itii;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnexionHttp {

    public String appelerApi(String pays, String codePostal) throws Exception {
        URL url = new URL("https://api.zippopotam.us/" + pays + "/" + codePostal);
        HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
        connexion.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connexion.getInputStream())
        );

        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        reader.close();
        connexion.disconnect();

        return json.toString();
    }
}
