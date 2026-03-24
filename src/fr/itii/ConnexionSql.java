package fr.itii;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnexionSql {

    private static final String URL = "jdbc:postgresql://localhost:5432/lieu_database";
    private static final String USER = "toto";
    private static final String PASSWORD = "pwd";

    public void inserer(ReponseZipo reponse) throws Exception {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS lieu_table (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(256)," +
                        "state VARCHAR(256)," +
                        "latitude DOUBLE PRECISION," +
                        "longitude DOUBLE PRECISION," +
                        "country VARCHAR(256))"
        );

        String sql = "INSERT INTO lieu_table(name, state, latitude, longitude, country) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        for (LieuObjet lieu : reponse.getPlaces()) {
            stmt.setString(1, lieu.getNom());
            stmt.setString(2, lieu.getState());
            stmt.setDouble(3, Double.parseDouble(lieu.getLatitude()));
            stmt.setDouble(4, Double.parseDouble(lieu.getLongitude()));
            stmt.setString(5, reponse.getCountry());
            stmt.executeUpdate();
        }

        stmt.close();
        conn.close();

        System.out.println("Données insérées avec succès");
    }
}
