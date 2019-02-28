package Tp1;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class main {
    public static void main(String[] args)throws IOException {
        File file = new File("Individus.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Individu> individusArray = new ArrayList<>();
        while(scanner.hasNextLine()){
            String data[]=scanner.nextLine().split(" ");
            individusArray.add(new Individu(data[0], data[1], data[2], data[3]));
        }
        scanner.close();
        TablePrinter table = new TablePrinter("Prenom","Couleur Cheveux", "Couleur Yeux", "Département de Génie" );
        for(int i=0; i<individusArray.size();i++) {
            //System.out.println(individusArray.get(i).getGenie());
            table.addRow(individusArray.get(i).getPrenom(), individusArray.get(i).getCheveux(), individusArray.get(i).getYeux(), individusArray.get(i).getGenie());
        }
        table.print();
        //System.out.println(individusArray);
        //System.out.println(individusArray.get(i).getPrenom());
        HashMap hmCheveux = new HashMap();
        HashMap hmYeux = new HashMap();
        HashMap hmGenie = new HashMap();

        for (int i =0; i<individusArray.size(); i++) {
            hmCheveux.put(individusArray.get(i).getPrenom(),individusArray.get(i).getCheveux());
            hmYeux.put(individusArray.get(i).getPrenom(),individusArray.get(i).getYeux());
            hmGenie.put(individusArray.get(i),individusArray.get(i).getGenie());

        }













    }
}
