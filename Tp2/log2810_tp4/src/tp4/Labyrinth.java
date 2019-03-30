package tp4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Labyrinth {

	private ArrayList<String[]> porte = new ArrayList<>();
	String[]string_result;
	
	public void entrerDansLeLabyrinth() {
		Scanner input = null;
		int linecounter;
		
		try {
			//input = new Scanner( new BufferedReader(new FileReader("Porte1.txt")));
			//FileReader fichierPorte = new FileReader(nomPorte);
			FileReader fichierPorte = new FileReader("Porte1.txt");
			BufferedReader br = new BufferedReader(fichierPorte);
			String sCurrentLine = br.readLine();
			int lineNumber = 0;
			/*while(input.hasNextLine()) {
				String temp_str = input.nextLine();
				// Lit la ligne au complet
				System.out.println(temp_str);
				Scanner lineScanner = new Scanner(temp_str);
				lineScanner.useDelimiter(" ");
				String str = new String();
				while(lineScanner.hasNext()) {
					str+=lineScanner.next()+" ";
					// lit chaque instruction d'une ligne et l'ajoute
					System.out.println(str);
				}
				//String[]string_result = str.split(Pattern.quote(","));
				string_result = str.split(Pattern.quote(" "));
				System.out.println(string_result.length);
				porte.add(string_result);
				
			}*/
			
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(input != null)
				input.close();	
		}
		System.out.println("---------");
		
		
	}

}
