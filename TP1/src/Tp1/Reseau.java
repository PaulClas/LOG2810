package Tp1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Reseau {

    private static ArrayList<String[]> people = new ArrayList<>(); // individus.txt
    private static ArrayList<String[]> relations = new ArrayList<>(); // relations.txt

    /**
     * Read 2 texts files
     * Produce the network
     * @param a fichier de texte
     * @param b fichier de texte
     */
    public static void creerReseauSocial(String a,String b){
        Scanner in1 = null;
        Scanner in2 = null;

        //Reading files
        try{
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
                people.add(string_result1);
            }


            in2 = new Scanner(new BufferedReader(new FileReader(b)));
            while(in2.hasNextLine()) {
                String temp_string2 = in2.nextLine();

                Scanner lineScanner2 = new Scanner(temp_string2);
                lineScanner2.useDelimiter(" ");
                String str2 = new String();
                while (lineScanner2.hasNext()) {
                    str2 += lineScanner2.next() + ",";
                }

                String[] string_result2 = str2.split(Pattern.quote(","));
                relations.add(string_result2);
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(in1 != null)
                in1.close();
            if(in2 != null)
                in2.close();
        }

        // Check for wrong entries in relations relation of 0
        Iterator<String[]> iter = relations.iterator();
        while(iter.hasNext()) {
            String[] temp_str = iter.next();
            if (temp_str[1].equals("0"))
                iter.remove();
        }
    }

    public ArrayList<String[]> getPeople() {
        return people;
    }

    public ArrayList<String[]> getRelations() {
        return relations;
    }

    // Display the network
    public void afficherReseauSocial(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        if(relations.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder();

        for(String[] row : relations)
            // Verify that item not null and relation not 0
            if( row != null && !row[1].equals("0"))
                sb.append(row[0] + " " + row[2] + " " + row[1] + "\n");

        return sb.toString();
    }
}