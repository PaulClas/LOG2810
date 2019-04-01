package tp4;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		String nomPorte= null;
		boolean conditionA=false;
		Labyrinth labyrinth = new Labyrinth();
		boolean quitter = false;
		do {
			System.out.println("Menu de selection:\n\n\t(a)\tEntrer dans le labyrinthe\n\t(b)\tOuvrir une porte" +
                    "\n\t(c)\tAfficher le chemin parcouru\n\t(d)\tQuitter\n\n");
            Scanner scanner = new Scanner(System.in);
            
            char selection = scanner.next().trim().charAt(0);
            switch(selection) {
            	case'a':
            		conditionA= true;
            		labyrinth.ouvrirPorte("Porte1.txt");
            		break;
            	case'b':
            		if(conditionA ==true) {
            			labyrinth.ouvrirPorte(nomPorte);
            		}
            		break;
            	case'c':
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
