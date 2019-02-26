package Tp1;

public class Main {

    public static void main(String[] args){

        Reseau reseau = new Reseau();

        reseau.creerReseauSocial("Individus.txt", "Relations.txt");

//        System.out.println(reseau);

        reseau.afficherReseauSocial();

//        Agent agent1 = new Agent();

        Agent agent = new Agent(reseau.getPeople(), reseau.getRelations());

//        Agent.F_C_arrays test0 = agent.shortestPath("Maximer", "France");
//        Agent.F_C_arrays test1 = agent.shortestPath("Nathurin", "Fleurette");
        Agent.F_C_arrays test2 = agent.shortestPath("Elorida","Alarie");

        System.out.println("test");
    }
}
