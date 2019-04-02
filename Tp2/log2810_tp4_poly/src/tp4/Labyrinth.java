package tp4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import tp4.Automate;
import tp4.Porte;
import tp4.Instruction;

public class Labyrinth {
	ArrayList<Password> passwordArray = new ArrayList<>();
	ArrayList<Instruction> instructionArray = new ArrayList<>();
	ArrayList<Porte> historique = new ArrayList<>();
	ArrayList<Porte> temp = new ArrayList<>();
	Porte porte = new Porte();
	/**
	 * 
	 * @param porteValider
	 */
	public void ajoutCheminParcourue(Porte porteValider) {
		//ArrayList<Porte> historique = new ArrayList<>();
		Automate automate = new Automate();
		Porte porte = new Porte(porteValider.getNomPorte(),porteValider.getArrayInstruction(),automate.genererAutomate(porteValider));
		historique.add(porte);
	}
	/**
	 * 
	 */
	public void afficherLeCheminParcourue() {
		for(int i =0; i< historique.size();i++) {
			System.out.println("\nEvenement Porte");
			System.out.println("\ta. "+historique.get(i).getNomPorte());
			System.out.println("\tb.");
			for(int j =0; j<historique.get(i).getNombrePassWord();j++) {
				historique.get(i).getpasswordArray(j).afficherProf();
			}
			if(historique.get(i).estGouffre()== false) {
				System.out.println("\tc."+"Cette porte n'est pas un gouffre");
			}else {
				System.out.println("\tc."+"Cette porte est un gouffre");
			}
			
		}
		
	}
	/**
	 * 
	 * @param nomPorte
	 * @return
	 */
	public Porte ouvrirPorte(String nomPorte) {
		if(!passwordArray.isEmpty()) {
			passwordArray.clear();
		}
		if(!instructionArray.isEmpty()) {
			instructionArray.clear();
		}
		boolean mdpString= false;
		Scanner input = null;
		int counterMdp=0, counterInstructions =0;
		String temp_mdp, temp_porte;
		try {
		input = new Scanner( new BufferedReader(new FileReader(nomPorte)));
		int numbLigne = 0;
		while(input.hasNextLine()) {
			String temp_str = input.nextLine();
			Scanner lineScanner = new Scanner(temp_str);
			//lineScanner.useDelimiter(" ");
			String str = new String();
			//str=lineScanner.next();
			while(lineScanner.hasNext()) {
				str=lineScanner.next();
				// lit chaque instruction d'une ligne et l'ajoute
				if(str.contentEquals("{") || str.contentEquals("}")) {
					//System.out.println("Inutile");
					numbLigne++;
					//str=lineScanner.next();
				}
				else if(numbLigne==1 && str != "{") {
					//System.out.println(str);
					//System.out.println(str.length());
					//System.out.println(str.charAt(0));
					//System.out.println(str.charAt(3));
					if(str.length()<=4 || str.charAt(4)==','){
						instructionArray.add(new Instruction(str.charAt(0),str.charAt(3),str.charAt(0)));
					}else {
						instructionArray.add(new Instruction(str.charAt(0),str.charAt(3),str.charAt(4)));
					}
					//counterInstructions++;
				}
				else if(numbLigne >1) {
					passwordArray.add(new Password(str,lineScanner.next()));
					//System.out.println(str);
					
				}
				//str=lineScanner.nextLine();
				//System.out.println(str);
			}
			//System.out.println("Sortie");
		}	
	/*
	 * 
	 * 
	 * */
		}catch(IOException e) {
			e.printStackTrace();
		}
		Porte porteReturn = new Porte(nomPorte,instructionArray,passwordArray);
		ajoutCheminParcourue(porteReturn);
		//Automate automate = new Automate();
		//automate.genererAutomate(porteReturn);
		return porteReturn;
		//Automate automate = new Automate();
		//automate.creerAutomate(porteReturn);
		//automate.afficherAutomate(porteReturn);
		//automate.afficherMotDePasseValider(automate.genererAutomate(porteReturn));
		
		//TEST///////////////////////////////////////////////////////////////////
		/*for(int i=0; i<instructionArray.size();i++) {
			instructionArray.get(i).afficher();
		}*/
		
		//System.out.println(counterInstructions);
		
		/*for(int i =0; i<passwordArray.size(); i++) {
			//System.out.print(passwordArray.get(i).getMotDePasse());
			passwordArray.get(i).afficher();
		}*/
		///////////////////////////////////////////////////////////////////////////
	 }
	/**
	 * 
	 * @param porteReturn
	 * @return
	 */
	public Porte AutomateValidePorte(Porte porteReturn) {
		Automate automate = new Automate();
		Porte porteValider = new Porte(porteReturn.getNomPorte(), porteReturn.getArrayInstruction(),automate.genererAutomate(porteReturn));
		return porteValider;
	}
	/**
	 * 
	 * @param porteReturn
	 */
	public void afficherOuvrirPorte(Porte porteReturn) {
		Automate automate = new Automate();
		automate.afficherMotDePasseValider(automate.genererAutomate(porteReturn));
	}

}
