package fr.itii;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Entrer l'abbreviation du pays (ex : fr, us) :");
        String abbreviation_pays = input.nextLine();

        System.out.println("Entrer le code postal :");
        String codePostal = input.nextLine();

        try {
            Class.forName("org.postgresql.Driver");

            Connection connexionsql = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/lieu_database",
                    "toto",
                    "pwd"
            );

            connexionsql.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS lieu_table (" +
                            "id SERIAL PRIMARY KEY," +
                            "name VARCHAR(256)," +
                            "state VARCHAR(256)," +
                            "latitude VARCHAR(50)," +
                            "longitude VARCHAR(50)," +
                            "country VARCHAR(256)" +
                            ");"
            );

            URL url = new URL("https://api.zippopotam.us/" + abbreviation_pays + "/" + codePostal);
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();

            BufferedReader lirereponse = new BufferedReader(
                    new InputStreamReader(connexion.getInputStream())
            );

            StringBuilder json = new StringBuilder();
            String line;
            while ((line = lirereponse.readLine()) != null) {
                json.append(line);
            }
            lirereponse.close();
            connexion.disconnect();

            Gson gson = new Gson();
            fr.itii.ReponseZipo reponseZipo = gson.fromJson(json.toString(), fr.itii.ReponseZipo.class);

            String sql = "INSERT INTO lieu_table(name, state, latitude, longitude, country) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connexionsql.prepareStatement(sql);

            for (fr.itii.lieu_objet lieu : reponseZipo.getPlaces()) {
                stmt.setString(1, lieu.getNom());
                stmt.setString(2, lieu.getState());
                stmt.setString(3, lieu.getLatitude());
                stmt.setString(4, lieu.getLongitude());
                stmt.setString(5, reponseZipo.getCountry());
                stmt.executeUpdate();
            }

            stmt.close();
            connexionsql.close();

            System.out.println("Données inserées OK");

        } catch (Exception e) {
            e.printStackTrace();
        }

        input.close();
    }
}
