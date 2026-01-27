package fr.itii;

import java.util.Scanner;

public class EntreeUser {

    private String pays;
    private String codePostal;

    public EntreeUser() {
    }

    public void saisir() {
        Scanner input = new Scanner(System.in);

        System.out.print("Entrer l'abbreviation du pays (ex : fr, us) : ");
        pays = input.nextLine();

        System.out.print("Entrer le code postal : ");
        codePostal = input.nextLine();
    }

    public String getPays() {
        return pays;
    }

    public String getCodePostal() {
        return codePostal;
    }
}
