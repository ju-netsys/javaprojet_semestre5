package fr.itii;

import com.google.gson.annotations.SerializedName;

public class LieuObjet {

    @SerializedName("place name")
    private String nom;
    private String state;
    private String latitude;
    private String longitude;

    public String getNom() {
        return nom;
    }

    public String getState() {
        return state;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
