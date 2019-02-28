package Tp1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GuessWho {
    private  ArrayList<Individu> individus= new ArrayList<>(); // individus.txt


    public void lectureIndividus() throws IOException {
        Scanner in1 = null;
            in1 = new Scanner(new BufferedReader(new FileReader("Individus.txt")));
            while(in1.hasNextLine()) {
                String temp_string1 = in1.nextLine();

                Scanner lineScanner1 = new Scanner(temp_string1);
                lineScanner1.useDelimiter(" ");
                String str1 = new String();
                while (lineScanner1.hasNext()) {
                    str1 += lineScanner1.next() + ",";
                }

                String[] string_result1 = str1.split(Pattern.quote(","));
                this.individus.add(new Individu(string_result1[0], string_result1[1], string_result1[2], string_result1[3]));
            }

    }

}

