package Tp1;

import java.util.ArrayList;
import java.util.Scanner;

public class Agent {

    private ArrayList<String[]> arrayListPeople_ = new ArrayList<>();
    private ArrayList<String[]> arrayListRelations_ = new ArrayList<>();
    private ArrayList<String[]> arrayListPeopleSafeCopy_ = new ArrayList<>();
    private ArrayList<String> nomreponse_ = new ArrayList<>();
    private String[] nompastrouvé_ = new String[2];
    private ArrayList<F_C_arrays> nomChemin_ = new ArrayList<>();
    private String[] caracteristicInd_ = new String[3];
    private ArrayList<Caracteristic> d_cheveu_values = new ArrayList<>();
    private ArrayList<Caracteristic> d_yeux_values = new ArrayList<>();
    private ArrayList<Caracteristic> d_departement_values = new ArrayList<>();
    private static int compteur_ = 0;

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

    /**
     * Constructeur par defaut
     */
    Agent () {
        this(null,null); // Constructor chaining
    }

    /**
     * Constructeur par parametre
     * @param Individus
     * @param Relations
     */
    Agent(ArrayList<String[]> Individus, ArrayList<String[]> Relations){
        this.arrayListPeople_ = Individus;
        this.arrayListRelations_ = Relations;
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
     * Getter people
     * @return the remaining people
     */
    public ArrayList<String[]> getArrayListPeople_() {
        return arrayListPeople_;
    }

    /**
     * Getter relations
     * @return the relations
     */
    public ArrayList<String[]> getArrayListRelations_() {
        return arrayListRelations_;
    }

    /**
     * Trouve le chemin le plus court entre deux individus
     * @param Node1 starting node
     * @param Node2 end node
     */
    public F_C_arrays trouverChaineContacts(String Node1, String Node2) {
        // Initialize les chemins

        // L(v) = infinity
        double inf = Double.POSITIVE_INFINITY;
        ArrayList<Matrix> matrice = new ArrayList<>();
        for (int i = 0; i < this.arrayListRelations_.size(); i++){
            int relation = Integer.parseInt(this.arrayListRelations_.get(i)[1]);
            if(relation > 0){
                matrice.add(new Matrix(this.arrayListRelations_.get(i)[0], this.arrayListRelations_.get(i)[2], relation, inf));
                matrice.add(new Matrix(this.arrayListRelations_.get(i)[2], this.arrayListRelations_.get(i)[0], relation, inf));
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

    /**
     * Initialize un tableau des caractéristiques
     */
    public void init_tableau_car(){
        //Dresser le portrait des caracteristique
        d_cheveu_values.add(new Caracteristic("N","noir", 0, false));
        d_cheveu_values.add(new Caracteristic("R","roux", 0, false));
        d_cheveu_values.add(new Caracteristic("B","blond", 0, false));
        d_cheveu_values.add(new Caracteristic("M","marron", 0, false));

        d_yeux_values.add(new Caracteristic("B","bleu", 0, false));
        d_yeux_values.add(new Caracteristic("V","vert", 0, false));
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
     * S'occupe de trouver les deux individus mystères
     */
    public void identifierIndividus(){
        // Before changing arraylispeople clone it
        this.arrayListPeopleSafeCopy_ = (ArrayList) this.arrayListPeople_.clone();
        // count caracteristique/personne trouve pour chaque categorie de caracteristique {cheveux , yeux , departement}
        int[] count_caracteristique = new int[3];
        int found = 0;
        ArrayList<int[]> temp_answer = new ArrayList<>();

        init_tableau_car();

        // Boucle qui pose les questions se rattachant au plus grand ensemble
        do {
            // Avoir le portrait
            this.recensement();

            //Trouve maximum
            int[] temp_max; // format [array index, index in array, value]
            temp_max = findQuestion(count_caracteristique[0], count_caracteristique[1], count_caracteristique[2]);

            if(count_caracteristique[0] == 2 && temp_max[0] == 0)
                break;

            // Prend la réponse
            String reponse = this.poseQuestion(temp_max[0], temp_max[1]);

            // Selon réponse
            if(reponse.equals("o") || reponse.equals("oui pour les 2 individus")){
                count_caracteristique[temp_max[0]] += 2;
                updateQuestionStatus(temp_max[0], temp_max[1]);
                removePeople(temp_max[0], temp_max[1], true);
                compteur_++;
            }
            else if (reponse.equals("u") || reponse.equals("oui pour un seul individu")){
                count_caracteristique[temp_max[0]] += 1;
                updateQuestionStatus(temp_max[0], temp_max[1]);
                temp_answer.add(temp_max);
                compteur_++;
            }
            else if (reponse.equals("n") || reponse.equals("non pour les 2 individus")){
                updateQuestionStatus(temp_max[0], temp_max[1]);
                removePeople(temp_max[0], temp_max[1], false);
                compteur_++;
            }
            else if (reponse.equals("s")){
                System.out.println(this);
            }

            found = count_caracteristique[0] + count_caracteristique[1] + count_caracteristique[2];
            if (found == 6){
                findSingleIndividuals(temp_answer);
                break;
            }

        } while(this.arrayListPeople_.size() > 2 || compteur_ > 100);// Arrete si poser plus de 100 questions ou size

        // Traite la reponse final si oui non les individus on bien ete trouvé
        String response;
        nomreponse_ = new ArrayList<>();
        for (String[] row: this.arrayListPeople_){
            nomreponse_.add(row[0]);
        }
        do {
            if (this.arrayListPeople_.size() == 2)
                System.out.println("Les individus mystère étaient-ils " + this.arrayListPeople_.get(0)[0] + " et " + this.arrayListPeople_.get(1)[0] + "?");
            else{
                System.out.println("Les deux individus mystères n'ont pu être identifier, les individus restants sont: \n" + this.toString());
                response = "n";
                break;
            }

            response = captureUserInput();
        }while (!(response.equals("o") || response.equals("oui pour les 2 individus")
                ||response.equals("u") || response.equals("oui pour un seul individu")
                ||response.equals("n") || response.equals("non pour les 2 individus")));
        if(!(response.equals("o") || response.equals("oui pour les 2 individus")))
            finalAnswer(response);

        // Trouve la chaine la plus courte et enleve arcs indesirables
        System.out.println("Entrer la caractéristique indésirable pour les cheveux");
        this.caracteristicInd_[0] = captureUserInput();

        System.out.println("Entrer la caractéristique indésirable pour les yeux");
        this.caracteristicInd_[1] = captureUserInput();

        System.out.println("Entrer la caractéristique indésirable pour le departement");
        this.caracteristicInd_[2] = captureUserInput();

        enleverArcsIndesirables(this.caracteristicInd_[0], this.caracteristicInd_[1], this.caracteristicInd_[2]);

        nomChemin_.add(trouverChaineContacts(this.nomreponse_.get(0),this.nomreponse_.get(1)));
    }

    /**
     * S'occupe de gérer la reponse final
     */
    public void finalAnswer(String response){
        int count = 0;

        if (response.equals("u") || response.equals("oui pour un seul individu")) {
            ArrayList<String> possibleNames = new ArrayList<>();
            ArrayList<String> possibleWrongNames = new ArrayList<>();
            //fetch possible names
            for (String [] row: this.arrayListPeopleSafeCopy_) {
                possibleNames.add(row[0]);
            }
            // fetch names in current solution
            for (String [] row: this.arrayListPeople_) {
                possibleWrongNames.add(row[0]);
            }
            // Enleve le mauvais nom
            do {
                System.out.println("Entrer le nom erroné : ");
                String nomErroné = captureUserInput();

                if (possibleWrongNames.contains(nomErroné)){
                    count ++;
                    this.nomreponse_.remove(nomErroné);
                }
                else
                    System.out.println("Le nom n'est pas dans l'ensemble d'individu proposé ");
            }while(count < 1);
            // met le bon nom a sa place
            do {
                System.out.println("Entrer le bon nom : ");
                this.nompastrouvé_[0] = captureUserInput();

                if (possibleNames.contains(this.nompastrouvé_[0])){
                    count ++;
                    this.nomreponse_.add(this.nompastrouvé_[0]);
                }
                else
                    System.out.println("Le nom n'est pas dans l'ensemble d'individu");
            }while(count < 2);
        } else if (response.equals("n") || response.equals("non pour les 2 individus")) {
            nomreponse_.clear();
            ArrayList<String> possibleNames = new ArrayList<>();
            //fetch possible names
            for (String [] row: this.arrayListPeopleSafeCopy_) {
                possibleNames.add(row[0]);
            }
            // Rajoute les bons noms
            do {
                System.out.println("Entrer le premier nom : ");
                this.nompastrouvé_[0] = captureUserInput();

                if (possibleNames.contains(this.nompastrouvé_[0])) {
                    this.nomreponse_.add(this.nompastrouvé_[0]);
                    count++;
                } else {
                    System.out.println("Le nom1 n'est pas dans l'ensemble d'individu");
                    while (!possibleNames.contains(this.nompastrouvé_[0])) {
                        System.out.println("Entrer le premier nom : ");
                        this.nompastrouvé_[0] = captureUserInput();
                    }
                }

                System.out.println("Entrer le second nom : ");
                this.nompastrouvé_[1] = captureUserInput();

                if (possibleNames.contains(this.nompastrouvé_[1])){
                    this.nomreponse_.add(this.nompastrouvé_[1]);
                    count++;
                }
                else{
                    System.out.println("Le nom2 n'est pas dans l'ensemble d'individu");
                    while (!possibleNames.contains(this.nompastrouvé_[1])) {
                        System.out.println("Entrer le second nom : ");
                        this.nompastrouvé_[1] = captureUserInput();
                    }
                }
            }while(count < 2);
        }
    }

    /**
     * Capture the user input from the console
     * @return
     */
    public String captureUserInput(){
        String result = "";
        // Consume the remainig \n
        Scanner scan = new Scanner(System.in);
        scan.reset();
        while(true){
            if (scan.hasNextLine())
                result += scan.nextLine();
            if (result != "")
                break;
        }
        return result;
    }

    /**
     * When answer his "u" will narrow down the remaining possible choices if necessary
     * @param maybeInSolution possible final poeple
     */
    public void findSingleIndividuals(ArrayList<int[]> maybeInSolution){
        int size = this.arrayListPeople_.size();
        if (size == 2) //Les deux individus sont deja trouvé
            return;

        ArrayList<String[]> temp_result = new ArrayList<>();
        ArrayList<String> temp_cheveu = new ArrayList<>();
        ArrayList<String> temp_yeux = new ArrayList<>();
        ArrayList<String> temp_departement = new ArrayList<>();
        int[] count_cheveu1 = new int[2];
        int[] count_yeux2 = new int[2];
        int[] count_departement3 = new int[2];

        //for loop to populate each array
        for (int[] row : maybeInSolution) {
            if(row[0] == 0)
                temp_cheveu.add(this.d_cheveu_values.get(row[1]).key_);

            if(row[0] == 1)
                temp_yeux.add(this.d_yeux_values.get(row[1]).key_);

            if(row[0] == 2)
                temp_departement.add(this.d_departement_values.get(row[1]).key_);
        }

        // Echantillon correspondant
        for (int i = 0; i < size; i++) {
            if((temp_cheveu.contains(this.arrayListPeople_.get(i)[1])  || temp_cheveu.size() == 0)
                    && (temp_yeux.contains(this.arrayListPeople_.get(i)[2]) || temp_yeux.size() == 0)
                    && (temp_departement.contains(this.arrayListPeople_.get(i)[3])  || temp_departement.size() == 0))
                temp_result.add(this.arrayListPeople_.get(i));
        }

        //Count caracterist to see individuals
        for (int i = 0; i < temp_result.size(); i++) {
            if(temp_cheveu.size() != 0) {
                if (temp_cheveu.get(0).equals(temp_result.get(i)[1])) {
                    count_cheveu1[0] += 1;
                }

                if (temp_cheveu.get(1).equals(temp_result.get(i)[1])) {
                    count_cheveu1[1] += 1;
                }
            }
            if(temp_yeux.size() != 0) {
                if (temp_yeux.get(0).equals(temp_result.get(i)[2])) {
                    count_yeux2[0] += 1;
                }

                if (temp_yeux.get(1).equals(temp_result.get(i)[2])) {
                    count_yeux2[1] += 1;
                }
            }
            if(temp_departement.size() != 0) {
                if (temp_departement.get(0).equals(temp_result.get(i)[3])) {
                    count_departement3[0] += 1;
                }

                if (temp_departement.get(1).equals(temp_result.get(i)[3])) {
                    count_departement3[1] += 1;
                }
            }
        }

        // Clears array so we can populate from only the requirements
        this.arrayListPeople_.clear();
        // Si une caractéristique a une seul occurence on va pouvoir déterminer nos individus
        for (int i = 0; i < count_cheveu1.length; i++) {
            if(count_cheveu1[i] == 1)
                for (int j = 0; j < temp_result.size(); j++) {
                    if (temp_cheveu.get(i).equals(temp_result.get(j)[1])){
                        this.arrayListPeople_.add(temp_result.get(j));
                        temp_result.remove(j);
                    }
                }
        }

        for (int i = 0; i < count_yeux2.length; i++) {
            if(count_yeux2[i] == 1)
                for (int j = 0; j < temp_result.size(); j++) {
                    if (temp_yeux.get(i).equals(temp_result.get(j)[2])) {
                        this.arrayListPeople_.add(temp_result.get(j));
                        temp_result.remove(j);
                    }
                }
        }

        for (int i = 0; i < count_departement3.length; i++) {
            if(count_departement3[i] == 1)
                for (int j = 0; j < temp_result.size(); j++) {
                    if (temp_departement.get(i).equals(temp_result.get(j)[3])) {
                        this.arrayListPeople_.add(temp_result.get(j));
                        temp_result.remove(j);
                    }
                }
        }

        // Si un seul trouvé devrait trouvé le second en eliminant de l'equation possibilité deja selectionné
        if(this.arrayListPeople_.size() > 0 || this.arrayListPeople_.size() < 2)
            for (String[] row : temp_result){
                if(!row[1].equals(this.arrayListPeople_.get(0)[1])
                && !row[2].equals(this.arrayListPeople_.get(0)[2])
                && !row[3].equals(this.arrayListPeople_.get(0)[3])
                ){
                    this.arrayListPeople_.add(row);
                    if (this.arrayListPeople_.size() ==2)
                        break;
                }
            }
    }

    /**
     * For "o" or "n" will eliminate the individual that doesn't fit the description
     * @param typeCaracteristic
     * @param car
     * @param inSolution
     */
    public void removePeople(int typeCaracteristic, int car, boolean inSolution){
        // This int will keep track of were to remove since remove method shifts the arraylist
        int tracker = 0;
        int size = this.arrayListPeople_.size();
        if (inSolution) {
            for (int i = 0; i < size; i++) {
                switch (typeCaracteristic) {
                    case 0:
                        if (!this.arrayListPeople_.get(i - tracker)[typeCaracteristic + 1].equals(this.d_cheveu_values.get(car).key_)) {
                            this.arrayListPeople_.remove(i - tracker);
                            tracker++;
                        }
                        break;
                    case 1:
                        if (!this.arrayListPeople_.get(i - tracker)[typeCaracteristic + 1].equals(this.d_yeux_values.get(car).key_)) {
                            this.arrayListPeople_.remove(i - tracker);
                            tracker++;
                        }
                        break;
                    case 2:
                        if (!this.arrayListPeople_.get(i - tracker)[typeCaracteristic + 1].equals(this.d_departement_values.get(car).key_)) {
                            this.arrayListPeople_.remove(i - tracker);
                            tracker++;
                        }
                        break;
                    default:
                }
            }
        }
        else if (!inSolution){
            for (int i = 0; i < size; i++) {
                switch (typeCaracteristic) {
                    case 0:
                        if (this.arrayListPeople_.get(i - tracker)[typeCaracteristic + 1].equals(this.d_cheveu_values.get(car).key_)) {
                            this.arrayListPeople_.remove(i - tracker);
                            tracker++;
                        }
                        break;
                    case 1:
                        if (this.arrayListPeople_.get(i - tracker)[typeCaracteristic + 1].equals(this.d_yeux_values.get(car).key_)) {
                            this.arrayListPeople_.remove(i - tracker);
                            tracker++;
                        }
                        break;
                    case 2:
                        if (this.arrayListPeople_.get(i - tracker)[typeCaracteristic + 1].equals(this.d_departement_values.get(car).key_)) {
                            this.arrayListPeople_.remove(i - tracker);
                            tracker++;
                        }
                        break;
                    default:
                }
            }
        }
    }

    /**
     * Will update the status of a question so it his not asked twice
     * @param typeCaracteristic from what array cheveu, yeux, dep.
     * @param car the specific car ex: yeux "bleu"
     */
    public void updateQuestionStatus(int typeCaracteristic, int car){
        switch (typeCaracteristic) {
            case 0:
                this.d_cheveu_values.get(car).qPose_ = true;
                break;
            case 1:
                this.d_yeux_values.get(car).qPose_ = true;
                break;
            case 2:
                this.d_departement_values.get(car).qPose_ = true;
                break;
            default:
        }
    }

    /**
     * Trouve la meilleur question a posé
     * Demande selon la caractéristique la plus présente
     * @param countCheveuTrouvé ind. trouvé pour cette car.
     * @param countyeuxTrouvé ind. trouvé pour cette car.
     * @param countdepTrouvé ind. trouvé pour cette car.
     * @return
     */
    public int[] findQuestion(int countCheveuTrouvé, int countyeuxTrouvé, int countdepTrouvé){
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

        //Compare les 3 resultats pour trouver celui avec le plus d'occurences
        max_car1 = max_car2[2] > max_car1[2] ? max_car2 : max_car1;
        max_car1 = max_car3[2] > max_car1[2] ? max_car3 : max_car1;
        return max_car1;
    }

    /**
     * S'occupe de transmettre la question en console
     * @param typeCaracteristic groupe [cheveu , yeux, dep.]
     * @param car caract. [ex: bleu, marron]
     * @return Valeur entrer par l'adversaire
     */
    public String poseQuestion(int typeCaracteristic, int car){
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

        return captureUserInput();
    }

    /**
     * Compte les caractéristiques pour déterminer celle avec le plus d'occurences
     */
    public void recensement() {
        // Put count back to 0
        for (Caracteristic row: this.d_cheveu_values) {
            row.count_ = 0;
        }
        for (Caracteristic row: this.d_yeux_values) {
            row.count_ = 0;
        }
        for (Caracteristic row: this.d_departement_values) {
            row.count_ = 0;
        }

        // Count les attributs
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

    /**
     * Met a 0 les relations ou les individus ont des caracteristiques indesirables
     * @param car1
     * @param car2
     * @param car3
     */
    public void enleverArcsIndesirables(String car1, String car2, String car3){
        // Trouver dans individu les noms
        ArrayList<String> nomPossible = new ArrayList<>();
        for (String[] row: this.arrayListPeopleSafeCopy_){
            if ((row[1].equalsIgnoreCase(car1) || row[2].equalsIgnoreCase(car2) || row[3].equalsIgnoreCase(car3)))
                nomPossible.add(row[0]);
        }

        // Parcourir relation et changer la relation a 0 si necessaire
        for (String[] row : this.arrayListRelations_) {
            if(nomPossible.contains(row[0]) || nomPossible.contains(row[2]))
                row[1] = "0";
        }
    }

    /**
     * print the result of the agent option "d"
     */
    public void afficherResultat() {

        //Affichage sous-graphe
        StringBuilder sb = new StringBuilder();
        if(this.arrayListRelations_.size() == 0)
            System.out.println(sb.toString());

        for(String[] row : this.arrayListRelations_)
            // Verify that item not null and relation not 0
            if(row != null)
                sb.append(row[0] + " " + row[2] + " " + row[1] + "\n");

        //Affichage meilleur chaine
        sb.append("\nLa meilleur chaine : \n");
        for (F_C_arrays row : this.nomChemin_) {
            sb.append(row.nodes_ + " => ");
        }

        //Nombre de questions posés
        sb.append("\nLe nombre de questions posées : \n");
        sb.append(compteur_);

        //Noms des individus mystères
        sb.append("\nLe nom des individus trouvés : \n");
        for (String[] row : this.arrayListPeople_) {
            sb.append(row[0] + "\n");
        }
        //Nom des individus mystères qui n'ont pas été devinés, s'il y a lieu
        sb.append("Les individus qui n'ont pas été trouvé : \n");
        for (String row : this.nompastrouvé_) {
            if (row != null)
            sb.append(row + "\n");
        }
        // Les trois caractéristique indésirables
        sb.append("Les caractéristiques indésirbales sont : " + this.caracteristicInd_[0] + ", "
                + this.caracteristicInd_[1] + ", " + this.caracteristicInd_[2] +"\n");

        System.out.println(sb.toString());
    }

    /**
     * Output the remaining people
     * @return String
     */
    @Override
    public String toString() {
        if(arrayListPeople_.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append("Les suspects encore sur la liste sont : \n");
        for(String[] row : arrayListPeople_)
            // Verify that item not null and relation not 0
            if( row != null && !row[1].equals("0"))
                sb.append(row[0] + "\n");

        return sb.toString();
    }
}
