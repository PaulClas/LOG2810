package tp4;
import java.util.ArrayList;
import java.util.Scanner;

import tp4.Labyrinth;

public class main {
	public char GLOBAL = 'S';

	public static void main(String[] args) {
		String nomPorte= null;
		Porte porte = new Porte();
		boolean conditionA=false;
		Labyrinth labyrinth = new Labyrinth();
		boolean quitter = false;
		do {
			System.out.println("\nMenu de selection:\n\n\t(a)\tEntrer dans le labyrinthe\n\t(b)\tOuvrir une porte" +
                    "\n\t(c)\tAfficher le chemin parcouru\n\t(d)\tQuitter\n\n");
            Scanner scanner = new Scanner(System.in);
            
            char selection = scanner.next().trim().charAt(0);
            //int choixPorte= scanner.nextInt();
            switch(selection) {
            	case'a':
            		conditionA= true;
            		System.out.println("Bienvenue dans le Labyrinth\nVous venez d'ouvrir la Porte1. Vous etes au debut du Labyrinth\n");
            		//labyrinth.ouvrirPorte("Porte1.txt");
            		labyrinth.afficherOuvrirPorte(labyrinth.ouvrirPorte("Porte1.txt"));
            		//alabyrinth.ajoutCheminParcourue(labyrinth.ouvrirPorte("Porte1.txt"));
            		break;
            	case'b':
            		if(conditionA ==true) {
            			System.out.println("Entrez le numero de la porte que vous desirez ouvrir:\n");
            			String choixPorte= scanner.next();
            			if(nomPorte!="Boss") {
            				nomPorte= "Porte"+choixPorte+".txt";
            				porte =labyrinth.ouvrirPorte(nomPorte);
            				labyrinth.afficherOuvrirPorte(porte);
            				//labyrinth.ajoutCheminParcourue(labyrinth.ouvrirPorte(nomPorte));
            				if(!porte.estGouffre()) {
            					System.out.println("Cette porte est un gouffre\nRetour au debut du labyrinthe");
            					//labyrinth.ajoutCheminParcourue(labyrinth.ouvrirPorte(nomPorte));
            					labyrinth.afficherOuvrirPorte(labyrinth.ouvrirPorte("Porte1.txt"));
            					//labyrinth.ajoutCheminParcourue(labyrinth.ouvrirPorte(nomPorte));
            				}
            			}else{
            				
            			}
            		}
            		break;
            	case'c':
            		labyrinth.afficherLeCheminParcourue();
            		break;
            	case'd':
            		quitter = true;
            		System.out.println("Option (d) Quitter a ete selectionner");
            		break;
            	default:
            		System.out.println("Erreur. Veuillez choisir une option valide");
            }
		}while(!quitter);
	}

}
