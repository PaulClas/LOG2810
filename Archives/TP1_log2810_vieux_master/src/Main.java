import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;



public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>(50);		
		String fileName = "C:/Users/maouab/IdeaProjects/TP1_LOG2810/Individus.txt";
        File file = new File(fileName);
        FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedReader br = new BufferedReader(fr);
        String line;
        
        try {
			while((line = br.readLine()) != null){
				
				for (int i = 0; i < list.size(); i++ ) {
			    String[] parts = line.split(" ");
			    String part1 = parts[0];
			    String part2 = parts[1];
			    String part3 = parts[2];
			    String part4 = parts[3];
			    list.add( part1 ); 
			    list.add( part2 ); 
			    list.add( part3 );
			    list.add( part4 );
			    for (String e : list)  
			    {  
			     System.out.println(e);  
			    } 
			    
				}
			    

				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for (String e : list)  
        {  
         System.out.println(e);  
        } 
        for (int j = 0; j < list.size(); j++ ) {
            System.out.println(list.get(j));

	}
	}

}
