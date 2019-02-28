package Tp1;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){
        Reseau reseau = new Reseau();
        boolean conditionA= false;
        boolean conditionC = false;
        boolean quitter = false;
        do {
            System.out.println("Menu de sélection:\n\n\t(a)\tCréer le réseau social.\n\t(b)\tAfficher le réseau social." +
                    "\n\t(c)\tJouer à Qui est-ce ?\n\t(d)\tAfficher le résultat.\n\t(e)\tQuitter.\n\n");
            Scanner scanner = new Scanner(System.in);

            char selection = scanner.next().trim().charAt(0);

            switch (selection){
                case 'a':
                    System.out.println("Option (a) Créer le réseau social. a été sélectionné: \n");
                    System.out.println("Entrez le nom du premier fichier : (Individus.txt recommandé)\n");
                    String nomFichier1 = scanner.next();
                    System.out.println("Entrer le nom du deuxième fichier : (Relations.txt recommandé)\n");
                    String nomFichier2 = scanner.next();
                    if("Relations.txt".contentEquals(nomFichier1) && "Individus.txt".contentEquals(nomFichier2)){
                        reseau.creerReseauSocial(nomFichier2, nomFichier1);
                    }
                    else if("Relations.txt".contentEquals(nomFichier2) && "Individus.txt".contentEquals(nomFichier1)){
                        reseau.creerReseauSocial(nomFichier1, nomFichier2);
                    }
                    else{
                        System.out.println("Avertissement: Vous entrez des noms de fichiers non standard.");
                        //reseau.creerReseauSocial(nomFichier1, nomFichier2);
                        break;
                    }
                    conditionA = true;
                    break;
                case 'b':
                    if(!conditionA){
                        System.out.println("L'option (a) doit être sélectionner avant d'afficher le réseau social\n");
                    }
                    else {System.out.println("Option (b) Afficher le réseau social. a été sélectionné: \n\n");
                    reseau.afficherReseauSocial();} //si il n'a pas été créer, il n'y aura rien à afficher
                    break;
                case 'c':
                    if(!conditionA){
                        System.out.println("L'option (a) doit être sélectionner avant de jouer au jeu GuessWho? .\n");
                    }
                    else {System.out.println("Option (c) Jouer à Qui est-ce ? a été sélectionné: \n\n");
                    //Ajouter la fonction
                    conditionC = true;}
                    break;
                case 'd':
                    if(!conditionC){
                        System.out.println("L'option (c) doit être sélectionner avant d'afficher le résultat\n");
                    }
                    else{System.out.println("Option (d) Afficher le résultat. a été sélectionné: \n\n");}
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

        System.out.println("_______________________________\n\n\n");
        //agent1.identifierIndividus();




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
