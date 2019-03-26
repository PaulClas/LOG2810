package TP2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TextFileReader {

    private ArrayList<String[]> porte = new ArrayList<>(); // individus.txt

    public void ouvrirPorte(String a){
        // Read the file
        readFile(a);

        // Remove parts 
        removeUnnecessaryParts();


    }

    /**
     * Read 1 text file
     * Produce the array
     * @param a fichier de texte
     */
    public void readFile(String a){
        Scanner in1 = null;

        a = "./Labyrinthe/"+ a + ".txt";
        try{
            //S'occupe de lire le fichier
            in1 = new Scanner(new BufferedReader(new FileReader(a)));
            while(in1.hasNextLine()) {
                String temp_string1 = in1.nextLine();

                Scanner lineScanner1 = new Scanner(temp_string1);
                lineScanner1.useDelimiter(" ");
                String str1 = new String();
                while (lineScanner1.hasNext()) {
                    str1 += lineScanner1.next() + ",";
                }

                String[] string_result1 = str1.split(Pattern.quote(","));
                porte.add(string_result1);
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(in1 != null)
                in1.close();
        }
    }


    public void removeUnnecessaryParts(){
        // Cleaning arrayList
        porte.remove(0);
        porte.remove(1); // Removing the brackets
        String[] temp_str = porte.get(0);
        porte.remove(0);
        ArrayList<String> filter_str = new ArrayList<>();

        for (String str: temp_str) {
            if (!str.equals("")){
                filter_str.add(str);
            }
        }

        String[] filter_final = new String[filter_str.size()];
        filter_final = filter_str.toArray(filter_final);
        porte.add(0, filter_final);

        // Check for wrong entries
//        Iterator<String[]> iter = porte.iterator();
//        while(iter.hasNext()) {
//            for (String str : iter.next()) {
//                if (str.equals("")){
//                    porte.remove("");
//                }
//            }
//        }
    }

    /**
     *  Getter porte
     * @return
     */
    public ArrayList<String[]> getPeople() {
        return porte;
    }

    // Displays the network
    public void afficherPorte(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        if(porte.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder();

//        for(String[] row : porte)
//            // Verify that item not null and relation not 0
//            if( row != null && !row[1].equals("0"))
//                sb.append(row[0] + " " + row[2] + " " + row[1] + "\n");

        return sb.toString();
    }
}

