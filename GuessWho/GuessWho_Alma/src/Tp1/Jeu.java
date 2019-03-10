package Tp1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    ArrayList<Individu> individusArray = new ArrayList<>();
    Individu individuMystère1 = new Individu();
    Individu individuMystère2 = new Individu();
    private ArrayList<Caracteristic> d_cheveu_values = new ArrayList<>();
    private ArrayList<Caracteristic> d_yeux_values = new ArrayList<>();
    private ArrayList<Caracteristic> d_departement_values = new ArrayList<>();


    public String questionAposer(Caracteristic caracteristic){

        switch(caracteristic.categorie_){
            case("Cheveux"):
                System.out.println("Est-ce que les individus ont les cheveux " + caracteristic.keyString_+ " ?");
                break;
            case("Yeux"):
                System.out.println("Est-ce que les individus ont les yeux " + caracteristic.keyString_+ " ?");
                break;
            case("Département de"):
                System.out.println("Est-ce que les individus sont dans le Département de " + caracteristic.keyString_+ " ?");
                break;
        }

        //Est-ce que les individus
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.next().trim();
        //System.out.println(reponse);
        return reponse;
    }

    public void jouer(){
        initTableauCaractéristiques();
        recenssement();
        //findIndexMax(d_cheveu_values);
        //findIndexMax(d_departement_values);
        //findIndexMax(d_yeux_values);
        System.out.println(findMaxDeToutesLesCaractéristiques().keyString_ +"  "+ findMaxDeToutesLesCaractéristiques().categorie_);
        ;
        System.out.println(questionAposer(findMaxDeToutesLesCaractéristiques()));




    }

    public Integer findIndexMax(ArrayList<Caracteristic> caracteristics){

        Integer indexMax=0;
        for(int i =0; i<caracteristics.size();i++){
            if(caracteristics.get(indexMax).count_<caracteristics.get(i).count_)
                indexMax= i;
        }
       // System.out.println(caracteristics.get(indexMax).key_ +"\t"+ caracteristics.get(indexMax).count_);

        return indexMax;


    }

    public Caracteristic findMaxDeToutesLesCaractéristiques(){
        Caracteristic plus_grande_caracteristique= new Caracteristic();


        Integer plusGrandCount = Math.max(Math.max(d_yeux_values.get(findIndexMax(d_yeux_values)).count_,
                d_cheveu_values.get(findIndexMax(d_cheveu_values)).count_),
                d_departement_values.get(findIndexMax(d_departement_values)).count_);
        if(d_cheveu_values.get(findIndexMax(d_cheveu_values)).count_== plusGrandCount)
            plus_grande_caracteristique = new Caracteristic(d_cheveu_values.get(findIndexMax(d_cheveu_values)).keyString_, "Cheveux");

        else if(d_yeux_values.get(findIndexMax(d_yeux_values)).count_== plusGrandCount)
            plus_grande_caracteristique= new Caracteristic(d_yeux_values.get(findIndexMax(d_yeux_values)).keyString_, "Yeux");

        else if(d_departement_values.get(findIndexMax(d_departement_values)).count_== plusGrandCount)
            plus_grande_caracteristique = new Caracteristic(d_departement_values.get(findIndexMax(d_departement_values)).keyString_, "Département de");
        return plus_grande_caracteristique;
    }
    /**
     *
     */
    public void recenssement(){

        for (int i = 0; i < this.individusArray.size(); i++){
            String car1 = individusArray.get(i).getCheveux();
            String car2 = individusArray.get(i).getYeux();
            String car3 = individusArray.get(i).getGenie();

            switch (car1) {
                case "N":
                    d_cheveu_values.get(0).count_ += 1;
                    break;
                case "R":
                    d_cheveu_values.get(1).count_ += 1;
                    break;
                case "B":
                    d_cheveu_values.get(2).count_ += 1;
                    break;
                case "M":
                    d_cheveu_values.get(3).count_ += 1;
                    break;
                default:
            }

            switch (car2) {
                case "B":
                    d_yeux_values.get(0).count_ += 1;
                    break;
                case "V":
                    d_yeux_values.get(1).count_ += 1;
                    break;
                case "N":
                    d_yeux_values.get(2).count_ += 1;
                    break;
                case "G":
                    d_yeux_values.get(3).count_ += 1;
                    break;
                case "M":
                    d_yeux_values.get(4).count_ += 1;
                    break;
                default:

            }

            switch (car3) {
                case "GI":
                    d_departement_values.get(0).count_ += 1;
                    break;
                case "GE":
                    d_departement_values.get(1).count_ += 1;
                    break;
                case "GP":
                    d_departement_values.get(2).count_ += 1;
                    break;
                case "GC":
                    d_departement_values.get(3).count_ += 1;
                    break;
                case "GA":
                    d_departement_values.get(4).count_ += 1;
                    break;
                case "GM":
                    d_departement_values.get(5).count_ += 1;
                    break;
                case "GB":
                    d_departement_values.get(6).count_ += 1;
                    break;
                case "GInd":
                    d_departement_values.get(7).count_ += 1;
                    break;
                case "ER":
                    d_departement_values.get(8).count_ += 1;
                    break;
                default:
            }
        }
    }

    public void choisirJoueurMystère(){
        System.out.println("Veuillez choisir les deux individus mystères dans ce tableau" +
                "que le programme devra trouver . \n");
        this.afficherTableau();
        System.out.println("Entrez le prenom du premier individus mystères:\n");
        Scanner scanner = new Scanner(System.in);
        String prenom = scanner.next().trim();
        trouverJoueurMystèreParPrenom(prenom, 1);
        System.out.println("Entrez le prenom du deuxième individus mystères:\n");
        String prenom2 = scanner.next().trim();
        trouverJoueurMystèreParPrenom(prenom2, 2);
        afficherTableauIndividuMystere();


    }

    /**
     *
     * @param prenom
     * @param nb
     */
    private void trouverJoueurMystèreParPrenom(String prenom, Integer nb){
        for(int i=0; i<individusArray.size(); i++){
            if(individusArray.get(i).getPrenom().equals(prenom)){
               if(nb.equals(1)) individuMystère1 = individusArray.get(i);
               if(nb.equals(2)) individuMystère2 = individusArray.get(i);
            }
        }

    }
    private void afficherIndividusMystèressélectionner(){
        System.out.println("Aide Mémoire: Vos individus mystères sont:\n ");
        this.afficherTableauIndividuMystere();
    }

    public void initTableauCaractéristiques(){
        d_cheveu_values.add(new Caracteristic("N","noir", 0));
        d_cheveu_values.add(new Caracteristic("R","roux", 0));
        d_cheveu_values.add(new Caracteristic("B","blond", 0));
        d_cheveu_values.add(new Caracteristic("M","marron", 0));


        d_yeux_values.add(new Caracteristic("B","bleu", 0));
        d_yeux_values.add(new Caracteristic("V","Vert", 0));
        d_yeux_values.add(new Caracteristic("N","noir", 0));
        d_yeux_values.add(new Caracteristic("G","gris", 0));
        d_yeux_values.add(new Caracteristic("M","marron", 0));

        d_departement_values.add(new Caracteristic("GI","génie informatique",0));
        d_departement_values.add(new Caracteristic("GE","génie électrique",0));
        d_departement_values.add(new Caracteristic("GP","génie physique",0));
        d_departement_values.add(new Caracteristic("GC","génie chimique",0));
        d_departement_values.add(new Caracteristic("GA","génie aérospatial",0));
        d_departement_values.add(new Caracteristic("GM","génie mécanique",0));
        d_departement_values.add(new Caracteristic("GB","génie biomédical",0));
        d_departement_values.add(new Caracteristic("GInd","génie industriel",0));
        d_departement_values.add(new Caracteristic("ER","génie énergétique",0));
    }

    public void lectureIndividu()throws IOException {
        File file = new File("Individus.txt");
        Scanner scanner = new Scanner(file);
        //ArrayList<Individu> individusArray = new ArrayList<>();
        while(scanner.hasNextLine()){
            String data[]=scanner.nextLine().split(" ");
            individusArray.add(new Individu(data[0], data[1], data[2], data[3]));
        }
        scanner.close();
    }

    private TablePrinter créerTableauIndividu(ArrayList<Individu> individusArray){
        TablePrinter table = new TablePrinter("Prenom","Couleur Cheveux", "Couleur Yeux", "Département de Génie" );
        for(int i=0; i<individusArray.size();i++) {
            //System.out.println(individusArray.get(i).getGenie());
            table.addRow(individusArray.get(i).getPrenom(), individusArray.get(i).getCheveux(), individusArray.get(i).getYeux(), individusArray.get(i).getGenie());
        }
        return table;

    }
    public void afficherTableau(){
        try {
            this.lectureIndividu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.créerTableauIndividu(individusArray).print();
    }

    private TablePrinter créerTableauMystère(Individu individuMystère1, Individu individuMystère2){
        TablePrinter tableIMystère = new TablePrinter("Prenom","Couleur Cheveux", "Couleur Yeux", "Département de Génie");
        tableIMystère.addRow(individuMystère1.getPrenom(), individuMystère1.getCheveux(), individuMystère1.getYeux(),individuMystère1.getGenie());
        tableIMystère.addRow(individuMystère2.getPrenom(), individuMystère2.getCheveux(), individuMystère2.getYeux(),individuMystère2.getGenie());
        return tableIMystère;
    }

    private void afficherTableauIndividuMystere(){
        this.créerTableauMystère(individuMystère1,individuMystère2).print();
    }


    private class Caracteristic{

        String key_, keyString_,categorie_;
        int count_;


        Caracteristic(){
            this(null, null, 0);
        }

        Caracteristic(String keyString, String categorie){
            this.keyString_=keyString;
            this.categorie_ = categorie;
        }

        Caracteristic(String key, String keyString, int count){
            this.key_ = key;
            this.keyString_ = keyString;
            this.count_ = count;

        }

    }


}
