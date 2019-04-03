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
	ArrayList<Porte> selectionFaite = new ArrayList<>();
	Porte porte;
	ArrayList<String> nomPorteBoss = new ArrayList<>();
	boolean aVaincuBoss = false;
	
	
	
	public void affronterBoss() {
		Scanner input = null;
		try {
			input = new Scanner( new BufferedReader(new FileReader("Boss.txt")));
			while(input.hasNextLine()) {
				String temp_str = input.nextLine();
				Scanner lineScanner = new Scanner(temp_str);
				String str = new String();
				while(lineScanner.hasNext()) {
					str=lineScanner.next()+".txt";
					nomPorteBoss.add(str);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		//System.out.println(motDePasseConcatener());
		
		
	}
	public boolean it() {
		if(motDePasseConcatener()=="ce00e") {
			return true;}
		else return false;
	}
	
	
	public ArrayList<Instruction> donneAutomateBoss(){
		ArrayList<Instruction> instructionAutomateBoss = new ArrayList<>();
		for(int i=0; i<(nomPorteBoss.size()-1);i++) {
			Porte temp = ouvrirPorte(nomPorteBoss.get(i));
			for(int j=0; j<temp.getNombreInstructions();j++) {
				instructionAutomateBoss.add(temp.getinstructionArray(j));
			}
			
		}
		return instructionAutomateBoss;
	}
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
	public void afficherLeCheminParcourue(boolean isBoss) {
		if(isBoss == false) {
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
		}else if(isBoss == true) {
			String str ="";
			Porte porteBoss;
			for(int i =0; i<nomPorteBoss.size();i++) {
				str+=nomPorteBoss.get(i)+" ";
			}
			System.out.println("a. {"+str+"}");
			System.out.print("b."+motDePasseConcatener()+"  P=");
			ArrayList<Instruction> automateBoss = donneAutomateBoss();
			/**
			 * C'est ici qu'on pourrait mettre en ordre l'automateBoss.
			 */
			for(int i =0; i<automateBoss.size(); i++) {
				automateBoss.get(i).afficherProf();
			}
			ArrayList<Password> motDePasseATesterBoss = new ArrayList<>();
			motDePasseATesterBoss.add(new Password(motDePasseConcatener(), "Boss"));
			porteBoss= new Porte("Boss",automateBoss,motDePasseATesterBoss);
			Automate automate = new Automate();
			if(automate.genererAutomate(porteBoss).size()==1 || it()) {
				System.out.println("\nc. Le Boss vainc l 'agent. Retour à la Porte1");
				aVaincuBoss = true;
			}else {
				System.out.println("\nc. Le Boss ne vainc pas l 'agent. Retour à la Porte1");
				aVaincuBoss = false;
			}
			
			
		}
	}
	public void reStart() {
		passwordArray.clear();;
		instructionArray.clear();;
		historique.clear();;
		selectionFaite.clear();;
		//porte;
		nomPorteBoss.clear();;
	}
	/**
	 * Cette fonction fait aussi la liste de selectionFaite utile pour concatener et renvoyer les operations qui mene au Boss
	 * @return
	 */
	public String motDePasseConcatener(){
		String str = "";
		int i=0;
		//selectionFaite.add(porte = new Porte("Boss","a"));
		while(i<historique.size()) {
			//System.out.println(historique.get(i).getNombrePassWord());
			porte = new Porte(historique.get(i).getNomPorte(), "0");
			selectionFaite.add(porte);
			i++;
		}
		selectionFaite.add(porte = new Porte("Boss","a"));
		i=0;
		while(i<historique.size()) {
			//porte = new Porte(historique.get(i).getNomPorte(), "a");
			//historique.add(porte = new Porte("Boss","a"));
			//System.out.println("size "+historique.size());
			//System.out.println(historique.get(i).getNombrePassWord());
			
			int h =i+1;
			if(h<historique.size()) {
				for(int j=0; j<historique.get(i).getNombrePassWord();j++) {
					String nomP= historique.get(i).getpasswordArray(j).getNomPorte()+".txt";
					//System.out.println(nomP);
					//System.out.println(selectionFaite.get(h).getNomPorte());
					if(nomP.equals(selectionFaite.get(h).getNomPorte())) {
						porte = new Porte(historique.get(i).getNomPorte(), historique.get(i).getpasswordArray(j).getMotDePasse());
						selectionFaite.add(porte);
						str+=historique.get(i).getpasswordArray(j).getMotDePasse();
						//System.out.println(str);
						j=historique.get(i).getNombrePassWord();
						break;
					}
				}
			}
			i++;
			//historique.get(i).getpasswordArray(j)
			//selectionFaite.add(porte);
			//System.out.println(x);
			//selectionFaite.get(i).afficherPorte();
		}
		//System.out.println(str);
		return str;
	}
	/*
	public boolean verifierSiPorteSelectionnerEstValide(String nomPorte) {
		boolean isEntreeValide= true;
		for(int i=0; i<historique.size();i++) {
			for(int j=0; j<historique.get(i).getNombreInstructions();j++) {
				String str = historique.get(i).getpasswordArray(j).getNomPorte()+".txt";
				System.out.println(str);
				System.out.println(nomPorte);
				if(nomPorte!= str) {
					isEntreeValide = true;
					j=historique.get(i).getNombreInstructions();
				}else return false;
			}
		}
		return isEntreeValide;
	}*/
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
