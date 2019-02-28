package Tp1;

public class Main {

    public static void main(String[] args){

        Reseau reseau = new Reseau();

        reseau.creerReseauSocial("Individus.txt", "Relations.txt");

//        System.out.println(reseau);

        reseau.afficherReseauSocial();

//        Agent agent1 = new Agent();

        Agent agent = new Agent(reseau.getPeople(), reseau.getRelations());

        //Lien simple  Valeur la plus petite 1
        Agent.F_C_arrays test0 = agent.shortestPath("Maximer", "Penelope");
        //Plusieurs liens Fonctionne n'a pas simplement pris le lien direct de 70
        Agent.F_C_arrays test1 = agent.shortestPath("Maximer", "France");
        // Pas de chemin
        Agent.F_C_arrays test2 = agent.shortestPath("Elorida","Alarie");
        // Pour s'amuser
        Agent.F_C_arrays test3 = agent.shortestPath("Adomard","Alarie");
        Agent.F_C_arrays test4 = agent.shortestPath("Adomard","Morido");

        System.out.println("test");
    }
}
