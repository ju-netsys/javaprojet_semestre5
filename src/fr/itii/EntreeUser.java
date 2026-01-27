package fr.itii;

import java.util.Scanner;

public class EntreeUser {

    private final String pays;
    private final String codePostal;

    public EntreeUser() {
        Scanner input = new Scanner(System.in);

        System.out.print("Entrer l'abbreviation du pays (ex : fr, us) : ");
        this.pays = input.nextLine();

        System.out.print("Entrer le code postal : ");
        this.codePostal = input.nextLine();
    }

    public String getPays() {
        return pays;
    }

    public String getCodePostal() {
        return codePostal;
    }
}
