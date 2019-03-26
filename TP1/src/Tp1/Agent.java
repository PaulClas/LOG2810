package Tp1;

import java.util.ArrayList;
import java.util.Scanner;

public class Agent {

    private ArrayList<String[]> arrayListPeople_ = new ArrayList<>();
    private ArrayList<String[]> arrayListRelations_ = new ArrayList<>();
    private ArrayList<Caracteristic> d_cheveu_values = new ArrayList<>();
    private ArrayList<Caracteristic> d_yeux_values = new ArrayList<>();
    private ArrayList<Caracteristic> d_departement_values = new ArrayList<>();

    private class Matrix {
        String nodeStart_, nodeAdjacent_;
        int relation_;
        double weight_;

        Matrix(){
            this(null, null, 0, 0);
        }

        Matrix(String nodeStart, String nodeAdjacent, int relation, double weight){
            this.nodeStart_ = nodeStart;
            this.nodeAdjacent_ = nodeAdjacent;
            this.relation_ = relation;
            this.weight_ = weight;
        }
    }

    public class F_C_arrays{
        ArrayList<String> nodes_;
        double weight_;

        F_C_arrays(){
            this(null, 0);
        }

        F_C_arrays(ArrayList<String> nodes, double weight){
            this.nodes_ = nodes;
            this.weight_ = weight;
        }
    }

    Agent () {
        this(null,null); // Constructor chaining
    }

    Agent(ArrayList<String[]> Individus, ArrayList<String[]> Relations){
        this.arrayListPeople_ = Individus;
        this.arrayListRelations_ = Relations;
    }

    /**
     * Will find the shortest path between 2 given nodes
     * @param Node1 starting node
     * @param Node2 end node
     */
    public F_C_arrays shortestPath(String Node1, String Node2) {
        // Initialize les chemins

        // L(v) = infinity
        double inf = Double.POSITIVE_INFINITY;
        ArrayList<Matrix> matrice = new ArrayList<>();
        for (String[] row : this.arrayListRelations_){
            int relation = Integer.parseInt(row[1]);
            if(relation != 0){
                matrice.add(new Matrix(row[0], row[2], relation, inf));
                matrice.add(new Matrix(row[2], row[0], relation, inf));
            }
        }
        // L(a) = 0
        matrice.add(new Matrix(Node1, Node1, 0, 0));
        // S = {}
        ArrayList<String> S_examined = new ArrayList<>();
        ArrayList<Matrix> F_wayFixed = new ArrayList<>();
        ArrayList<String> C_wayFinal = new ArrayList<>();

        while (!S_examined.contains(Node2)) {
            // Trouve sommet avec dist min qui n'est pas deja dans S
            double min_weight = inf;
            int pos_index = 0;
            for (int i = 0; i < matrice.size(); i++){
                if (min_weight > matrice.get(i).weight_)
                    if (!S_examined.contains(matrice.get(i).nodeAdjacent_)){
                        pos_index = i;
                        min_weight = matrice.get(i).weight_;
                    }
            }
            if(min_weight == inf){
                C_wayFinal.add("noPathFound");
                return new F_C_arrays(C_wayFinal, 0);
            }

            // Inclu le sommet u dans S
            S_examined.add(matrice.get(pos_index).nodeAdjacent_);

            F_wayFixed.add(new Matrix(matrice.get(pos_index).nodeStart_, matrice.get(pos_index).nodeAdjacent_,
                    matrice.get(pos_index).relation_, matrice.get(pos_index).weight_) );

            if(S_examined.contains(Node2))
                break;

            // for tous sommets voisin pas dans encore dans S
            // Trouver tous les sommets voisin.
            for (int i =0; i < matrice.size(); i++) {
                if(S_examined.get(S_examined.size() - 1).equals(matrice.get(i).nodeStart_)
                        && !S_examined.contains(matrice.get(i).nodeAdjacent_))
                    {
                    // if L(u) + w(u,v) < L(v)
                    double relation_temp = (double)matrice.get(i).relation_;
                    double weight_temp = F_wayFixed.get(F_wayFixed.size() - 1).weight_ + relation_temp;
                    if (weight_temp < matrice.get(i).weight_)
                        // L(v) = L(u) + w(u,v) TO be changed in matrice
                        matrice.get(i).weight_ = F_wayFixed.get(F_wayFixed.size() - 1).weight_ + matrice.get(i).relation_;
                }
            }
            // break
        }

        // On repasse pour extraire les noeuds du chemin
        C_wayFinal.add(0, Node2);
        double relation_temp = (double)F_wayFixed.get(F_wayFixed.size() - 1).relation_;
        double weight_temp = F_wayFixed.get(F_wayFixed.size() - 1).weight_ - relation_temp;
        String nodeStart_temp = F_wayFixed.get(F_wayFixed.size() - 1).nodeStart_;

        // Boucle pour ajouter noeud intermediaire
        for(int j = F_wayFixed.size() - 1; j >= 1; --j){
            if(nodeStart_temp.equals(F_wayFixed.get(j).nodeAdjacent_)
                    &&  weight_temp == F_wayFixed.get(j).weight_){
                C_wayFinal.add(0, F_wayFixed.get(j).nodeAdjacent_);
                nodeStart_temp = F_wayFixed.get(j).nodeStart_;
                relation_temp = (double)F_wayFixed.get(j).relation_;
                weight_temp = F_wayFixed.get(j).weight_ - relation_temp;
            }

        }
        C_wayFinal.add(0, Node1);

        return new F_C_arrays(C_wayFinal, F_wayFixed.get(F_wayFixed.size() - 1).weight_);
    }


    public void init_tableau_car(){
        //Dresser le portrait des caracteristique
        d_cheveu_values.add(new Caracteristic("N","noir", 0, false));
        d_cheveu_values.add(new Caracteristic("R","roux", 0, false));
        d_cheveu_values.add(new Caracteristic("B","blond", 0, false));
        d_cheveu_values.add(new Caracteristic("M","marron", 0, false));

        d_yeux_values.add(new Caracteristic("B","bleu", 0, false));
        d_yeux_values.add(new Caracteristic("V","Vert", 0, false));
        d_yeux_values.add(new Caracteristic("N","noir", 0, false));
        d_yeux_values.add(new Caracteristic("G","gris", 0, false));
        d_yeux_values.add(new Caracteristic("M","marron", 0, false));

        d_departement_values.add(new Caracteristic("GI","génie informatique",0, false));
        d_departement_values.add(new Caracteristic("GE","génie électrique",0, false));
        d_departement_values.add(new Caracteristic("GP","génie physique",0, false));
        d_departement_values.add(new Caracteristic("GC","génie chimique",0, false));
        d_departement_values.add(new Caracteristic("GA","génie aérospatial",0, false));
        d_departement_values.add(new Caracteristic("GM","génie mécanique",0, false));
        d_departement_values.add(new Caracteristic("GB","génie biomédical",0, false));
        d_departement_values.add(new Caracteristic("GInd","génie industriel",0, false));
        d_departement_values.add(new Caracteristic("ER","génie énergétique",0, false));
    }

    /**
     * Finds the 2 mystery persons
     */
    public void identifierIndividus(){
        // count caracteristique trouve pour chaque categorie de caracteristique {cheveux , yeux , departement}
        int[] count_caracteristique = new int[3];

        init_tableau_car();

        do {
            // Avoir le portrait
            this.recensement();

            //Trouve maximum
            int[] temp_max; // format [array index, index in array, value]
            temp_max = findNextQuestion(count_caracteristique[0], count_caracteristique[1], count_caracteristique[2]);

            // Prend la réponse
            String reponse = this.poseQuestion(temp_max[0], temp_max[1]);

            // Selon réponse
            if(reponse == "o" || reponse == "oui pour les 2 individus"){
                count_caracteristique[temp_max[0]] += 2;
            } else if (reponse == "u" || reponse == "oui pour un seul individu"){
                count_caracteristique[temp_max[0]] += 1;
            }
            else if (reponse == "n" || reponse == "non pour les 2 individus"){
                for (int i = 0; i < this.arrayListPeople_.size(); i++) {
                    // Need to add plus 1 to shift to caracteristics first index his name
                    switch (temp_max[1]) {
                        case 0:
                            if(this.arrayListPeople_.get(i)[temp_max[0]+1] == this.d_cheveu_values.get(temp_max[0]).key_)
                                this.arrayListPeople_.remove(i);
                            break;
                        case 1:
                            if(this.arrayListPeople_.get(i)[temp_max[0]+1] == this.d_yeux_values.get(temp_max[0]).key_)
                                this.arrayListPeople_.remove(i);
                            break;
                        case 2:
                            if(this.arrayListPeople_.get(i)[temp_max[0]+1] == this.d_departement_values.get(temp_max[0]).key_)
                                this.arrayListPeople_.remove(i);
                            break;
                        default:
                    }
                }
            }

        } while(this.arrayListPeople_.size() > 2
                || (count_caracteristique[0] + count_caracteristique[1] + count_caracteristique[2]) < 6);// Recensement

        System.out.println("test");
    }

    public int[] findNextQuestion(int countCheveuTrouvé, int countyeuxTrouvé, int countdepTrouvé){
        // format [array index, index in array, value]
        int[] max_car1 = new int[3], max_car2 = new int[3], max_car3 = new int[3];
        max_car1[0] = 0;
        max_car2[0] = 1;
        max_car3[0] = 2;

        // Condition if pour ne pas bouclé les caractéristiques deja parcouru
        if (countCheveuTrouvé < 2){
            for (int i = 0; i < this.d_cheveu_values.size(); i++) {
                if (this.d_cheveu_values.get(i).qPose_ == false)
                    if (max_car1[2] < this.d_cheveu_values.get(i).count_) {
                        max_car1[1] = i;
                        max_car1[2] = this.d_cheveu_values.get(i).count_;
                    }
            }
        }
        if (countyeuxTrouvé < 2){
            for (int i = 0; i < this.d_yeux_values.size(); i++) {
                if (this.d_yeux_values.get(i).qPose_ == false)
                    if (max_car2[2] < this.d_yeux_values.get(i).count_) {
                        max_car2[1] = i;
                        max_car2[2] = this.d_yeux_values.get(i).count_;
                    }
            }
        }
        if (countdepTrouvé < 2){
            for (int i = 0; i < this.d_departement_values.size(); i++) {
                if (this.d_departement_values.get(i).qPose_ == false) {
                    if (max_car3[2] < this.d_departement_values.get(i).count_) {
                        max_car3[1] = i;
                        max_car3[2] = this.d_departement_values.get(i).count_;
                    }
                }
            }
        }

        //Compare les 3 resultats
        max_car1 = max_car2[2] > max_car1[2] ? max_car2 : max_car1;
        max_car1 = max_car3[2] > max_car1[2] ? max_car3 : max_car1;
        return max_car1;
    }

    /**
     *
     * @param car
     * @param typeCaracteristic
     * @return
     */
    public String poseQuestion(int car, int typeCaracteristic){
        switch (typeCaracteristic) {
            case 0:
                System.out.println("Les individus ont-ils les cheveux " + this.d_cheveu_values.get(car).keyString_ + "?");
                break;
            case 1:
                System.out.println("Les individus ont-ils les yeux " + this.d_yeux_values.get(car).keyString_ + "?");
                break;
            case 2:
                System.out.println("Les individus sont-ils du département de " + this.d_departement_values.get(car).keyString_ + "?");
                break;
            default:
        }

        // To get and return response
        Scanner scan = new Scanner(System.in);
        String result = "";
        result+=scan.nextLine();
        scan.close();
        return result;
    }

    public void recensement() {
        // Put count back to 0
        for (Caracteristic row: this.d_cheveu_values) {
            row.count_ = 0;
        }
        for (Caracteristic row: this.d_cheveu_values) {
            row.count_ = 0;
        }
        for (Caracteristic row: this.d_cheveu_values) {
            row.count_ = 0;
        }

        // Count the attributs
        for (int i = 0; i < this.arrayListPeople_.size(); i++){
            String car1 = this.arrayListPeople_.get(i)[1];
            String car2 = this.arrayListPeople_.get(i)[2];
            String car3 = this.arrayListPeople_.get(i)[3];

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

    private class Caracteristic{
        String key_, keyString_;
        int count_;
        boolean qPose_;

        Caracteristic(){
            this(null, null, 0, false);
        }

        Caracteristic(String key, String keyString, int count, boolean qPose){
            this.key_ = key;
            this.keyString_ = keyString;
            this.count_ = count;
            this.qPose_ = false;
        }
    }

    /**
     * Determines the best path between 2 people
     * @param nom1 String
     * @param nom2 String
     */
    public void trouverChaineContacts(String nom1, String nom2){

        //Demande 3 caractéristiques indésirables

        // Élimine les relations qui partagent ces caractéristiques relation devient 0



        //
    }

    /**
     * print the result of the agent
     */
    public void afficherResultat() {}

}
