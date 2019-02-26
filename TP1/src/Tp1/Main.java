package Tp1;
import java.util.Scanner;
import java.lang.String;

public class Main {

    public static void main(String[] args){
        Reseau reseau = new Reseau();
        boolean quitter = false;
        do {
            System.out.println("Menu de sélection:\n\n\t(a)\tCréer le réseau social.\n\t(b)\tAfficher le réseau social." +
                    "\n\t(c)\tJouer à Qui est-ce ?\n\t(d)\tAfficher le résultat.\n\t(e)\tQuitter.\n\n");
            Scanner scanner = new Scanner(System.in);
            char selection = scanner.next().trim().charAt(0);
            switch (selection){
                case 'a':
                    System.out.println("Option (a) Créer le réseau social. a été sélectionné: \n \t Le réseau social" +
                            "a été créer.\n");
                    reseau.creerReseauSocial("Individus.txt", "Relations.txt");
                    break; // a et b devrait etre ensemble ?
                case 'b':
                    System.out.println("Option (b) Afficher le réseau social. a été sélectionné: \n\n");
                    reseau.afficherReseauSocial(); //si il n'a pas été créer, il n'y aura rien à afficher
                    break;
                case 'c':
                    System.out.println("Option (c) Jouer à Qui est-ce ? a été sélectionné: \n\n");
                    //Ajouter la fonction
                    break;
                case 'd':
                    System.out.println("Option (d) Afficher le résultat. a été sélectionné: \n\n");
                    //Ajouter la fonction
                    break;
                case 'e':
                    System.out.println("Option (e) Quitter. a été sélectionné:e");
                    //Ajouter la fonction
                    quitter= true;
                    break;
                default:
                    System.out.println("Erreur. Veuillez choisir une option valide !");

            }
        }while(!quitter);
        System.out.println("Au revoir.\n");

        //Reseau reseau = new Reseau();

        //reseau.creerReseauSocial("Individus.txt", "Relations.txt");

//        System.out.println(reseau);

       // reseau.afficherReseauSocial();

//        Agent agent1 = new Agent();

        Agent agent = new Agent(reseau.getPeople(), reseau.getRelations());

//        Agent.F_C_arrays test0 = agent.shortestPath("Maximer", "France");
        Agent.F_C_arrays test1 = agent.shortestPath("Nathurin", "Fleurette");
        Agent.F_C_arrays test2 = agent.shortestPath("Elorida","Alarie");

        System.out.println("test");
    }
}
