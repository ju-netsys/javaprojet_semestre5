package fr.itii;

import com.google.gson.Gson;

public class TraitementJson {

    private final Gson gson = new Gson();

    public ReponseZipo parser(String json) {
        return gson.fromJson(json, ReponseZipo.class);
    }
}
