package Tp1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tp5.TablePrinter;

public class Jeu {
    ArrayList<Individu> individusArray = new ArrayList<>();
    Individu individuMystere1 = new Individu();
    Individu individuMystere2 = new Individu();
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
            case("Departement de"):
                System.out.println("Est-ce que les individus sont dans le Departement de " + caracteristic.keyString_+ " ?");
                break;
        }
        this.afficherInterfaceReponse();
        //Est-ce que les individus
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.next().trim();
        //System.out.println(reponse);
        return reponse;
    }

    public void jouer(){
        initTableauCaracteristiques();
        recenssement();
        this.choisirJoueurMystere();
        //findIndexMax(d_cheveu_values);
        //findIndexMax(d_departement_values);
        //findIndexMax(d_yeux_values);
        //this.afficherIndividusMysteresselectionner();
        //System.out.println(findMaxDeToutesLesCaracteristiques().keyString_ +"  "+ findMaxDeToutesLesCaracteristiques().categorie_);
        
        this.gestionReponse(this.questionAposer(findMaxDeToutesLesCaracteristiques()));




    }
    
    public void afficherInterfaceReponse() {
    	System.out.println("\tVous pouvez choisir entre les réponses differentes suivantes:\n"
    			+ "\t\to - oui\n\t\tn - non\n\t\tu - seulement l'un des deux\n\t\ta - afficher les invidus mystères que j'ai choisi au début\n\t\tr - afficher les invidus mystères qu'il reste");
    }
    
    public void gestionReponse(String reponse) {
    	switch(reponse) {
    		case "o":
    			
    			break;
    		case "n":
    			break;
    		case "u":
    			break;
    		case "a":
    			System.out.println("Option a");
    			this.afficherIndividusMysteresselectionner();
    			this.questionAposer(findMaxDeToutesLesCaracteristiques());
    			break;
    		case "r":
    			break;
    		default:
    			this.questionAposer(findMaxDeToutesLesCaracteristiques());
    	}
    	
    }
    public void removeLastIndexMax() {
    	switch(this.findMaxDeToutesLesCaracteristiques().getCategorie()) {
    	case "Cheveux":
    		//this.d_cheveu_values.remove()
    	}
    	
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

    public Caracteristic findMaxDeToutesLesCaracteristiques(){
        Caracteristic plus_grande_caracteristique= new Caracteristic();


        Integer plusGrandCount = Math.max(Math.max(d_yeux_values.get(findIndexMax(d_yeux_values)).count_,
                d_cheveu_values.get(findIndexMax(d_cheveu_values)).count_),
                d_departement_values.get(findIndexMax(d_departement_values)).count_);
        if(d_cheveu_values.get(findIndexMax(d_cheveu_values)).count_== plusGrandCount)
            plus_grande_caracteristique = new Caracteristic(d_cheveu_values.get(findIndexMax(d_cheveu_values)).keyString_, "Cheveux");

        else if(d_yeux_values.get(findIndexMax(d_yeux_values)).count_== plusGrandCount)
            plus_grande_caracteristique= new Caracteristic(d_yeux_values.get(findIndexMax(d_yeux_values)).keyString_, "Yeux");

        else if(d_departement_values.get(findIndexMax(d_departement_values)).count_== plusGrandCount)
            plus_grande_caracteristique = new Caracteristic(d_departement_values.get(findIndexMax(d_departement_values)).keyString_, "DÃ©partement de");
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

    public void choisirJoueurMystere(){
        System.out.println("Veuillez choisir les deux individus mysteres dans ce tableau" +
                "que le programme devra trouver . \n");
        this.afficherTableau();
        System.out.println("Entrez le prenom du premier individus mysteres:\n");
        Scanner scanner = new Scanner(System.in);
        String prenom = scanner.next().trim();
        trouverJoueurMystereParPrenom(prenom, 1);
        System.out.println("Entrez le prenom du deuxiÃ¨me individus mysteres:\n");
        String prenom2 = scanner.next().trim();
        trouverJoueurMystereParPrenom(prenom2, 2);
        afficherTableauIndividuMystere();


    }

    /**
     *
     * @param prenom
     * @param nb
     */
    private void trouverJoueurMystereParPrenom(String prenom, Integer nb){
        for(int i=0; i<individusArray.size(); i++){
            if(individusArray.get(i).getPrenom().equals(prenom)){
               if(nb.equals(1)) individuMystere1 = individusArray.get(i);
               if(nb.equals(2)) individuMystere2 = individusArray.get(i);
            }
        }

    }
    
    private void afficherIndividusMysteresselectionner(){
        System.out.println("Aide Memoire: Vos individus mysteres sont:\n ");
        this.afficherTableauIndividuMystere();
    }

    public void initTableauCaracteristiques(){
        d_cheveu_values.add(new Caracteristic("N","noir", 0));
        d_cheveu_values.add(new Caracteristic("R","roux", 0));
        d_cheveu_values.add(new Caracteristic("B","blond", 0));
        d_cheveu_values.add(new Caracteristic("M","marron", 0));


        d_yeux_values.add(new Caracteristic("B","bleu", 0));
        d_yeux_values.add(new Caracteristic("V","Vert", 0));
        d_yeux_values.add(new Caracteristic("N","noir", 0));
        d_yeux_values.add(new Caracteristic("G","gris", 0));
        d_yeux_values.add(new Caracteristic("M","marron", 0));

        d_departement_values.add(new Caracteristic("GI","genie informatique",0));
        d_departement_values.add(new Caracteristic("GE","genie electrique",0));
        d_departement_values.add(new Caracteristic("GP","genie physique",0));
        d_departement_values.add(new Caracteristic("GC","genie chimique",0));
        d_departement_values.add(new Caracteristic("GA","genie aerospatial",0));
        d_departement_values.add(new Caracteristic("GM","genie mecanique",0));
        d_departement_values.add(new Caracteristic("GB","genie biomedical",0));
        d_departement_values.add(new Caracteristic("GInd","genie industriel",0));
        d_departement_values.add(new Caracteristic("ER","genie energetique",0));
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

    private TablePrinter creerTableauIndividu(ArrayList<Individu> individusArray){
        TablePrinter table = new TablePrinter("Prenom","Couleur Cheveux", "Couleur Yeux", "Departement de Genie" );
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
        this.creerTableauIndividu(individusArray).print();
    }

    private TablePrinter creerTableauMystere(Individu individuMystere1, Individu individuMystere2){
        TablePrinter tableIMystere = new TablePrinter("Prenom","Couleur Cheveux", "Couleur Yeux", "Departement de Genie");
        tableIMystere.addRow(individuMystere1.getPrenom(), individuMystere1.getCheveux(), individuMystere1.getYeux(),individuMystere1.getGenie());
        tableIMystere.addRow(individuMystere2.getPrenom(), individuMystere2.getCheveux(), individuMystere2.getYeux(),individuMystere2.getGenie());
        return tableIMystere;
    }

    private void afficherTableauIndividuMystere(){
        this.creerTableauMystere(individuMystere1,individuMystere2).print();
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
        
        public String getKey(){
        	return key_;
        }
        public String getKeyString() {
        	return keyString_;
        }
        public int getCount() {
        	return count_;
        }
        public String getCategorie() {
        	return categorie_;
        }

    }


}
