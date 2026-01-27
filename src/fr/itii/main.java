package fr.itii;

public class main {

    public static void main(String[] args) {
        try {
            EntreeUser entree = new EntreeUser();
            entree.saisir();

            String pays = entree.getPays();
            String codePostal = entree.getCodePostal();

            ConnexionHttp http = new ConnexionHttp();
            String json = http.appelerApi(entree.getPays(), entree.getCodePostal());

            TraitementJson traitement = new TraitementJson();
            ReponseZipo reponse = traitement.parser(json);

            ConnexionSql sql = new ConnexionSql();
            sql.inserer(reponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
