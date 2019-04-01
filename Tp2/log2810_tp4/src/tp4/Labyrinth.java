package tp4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Labyrinth {
	 public void ouvrirPorte(String nomPorte) {
		try {
		FileReader fichier = new FileReader(nomPorte);
		BufferedReader br = new BufferedReader(fichier);
		String ligneLive = br.readLine();
		Scanner input = new Scanner(br);
		int numbLigne = 0;
		while(input.hasNextLine()) {
			if(numbLigne ==0 || numbLigne ==2) {
				String inutile = ligneLive;
				System.out.println(inutile);
				numbLigne++;
			}
			if(numbLigne ==1) {
				//System.out.println(input.nextLine());
				
					// lit chaque instruction d'une ligne et l'ajoute
					//System.out.println(str);
				}
				
				numbLigne++;
			}
			if(numbLigne >2) {
				//System.out.println(input.nextLine());
				numbLigne++;
			}
			numbLigne++;
		
			
			
			
			
			
	/*
	 * 
	 * 
	 * */
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	 }

}
