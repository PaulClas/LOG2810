package Tp1;

import java.util.ArrayList;

public class Agent {

    private ArrayList<String[]> arrayListPeople_ = new ArrayList<>();
    private ArrayList<String[]> arrayListRelations_ = new ArrayList<>();

    private class Matrix {
        String nodeStart_;
        String nodeAdjacent_;
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
            matrice.add(new Matrix(row[0], row[2], relation, inf));
            matrice.add(new Matrix(row[2], row[0], relation, inf));
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
     * Finds the 2 mystery persons
     */
    public void identifierIndividus(){

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
