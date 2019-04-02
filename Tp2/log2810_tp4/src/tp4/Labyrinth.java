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
public class Labyrinth {
	ArrayList<Password> passwordArray = new ArrayList<>();
	ArrayList<Instruction> instructionArray = new ArrayList<>();
	
	public void ouvrirPorte(String nomPorte) {
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
		Porte porte1 = new Porte(instructionArray,passwordArray);
		
		Automate automate = new Automate();
		automate.creerAutomate(porte1);
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

}
