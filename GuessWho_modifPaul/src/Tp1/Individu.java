package Tp1;

public class Individu {

    private String prenom;
    private String cheveux;
    private String yeux;
    private String genie;

    public Individu( String prenom, String cheveux, String yeux, String genie){ // Constructeur par param
        this.prenom = prenom;
        this.cheveux = cheveux;
        this.yeux = yeux;
        this.genie = genie;
    }

    public Individu(){ //Constructeur par defaut
        this.prenom = "";
        this.cheveux = "";
        this.yeux = "";
        this.genie = "";
    }

    public Individu( Individu individu){ //Constructeur de Copie
    }
    //Getters

    public String getPrenom(){
        return this.prenom;
    }
    public String getCheveux(){
        return this.cheveux;
    }
    public String getYeux(){
        return this.yeux;
    }
    public String getGenie(){
        return this.genie;
    }

    //Setters
    public void setPrenom(String prenom){
        this.prenom= prenom;
    }
    public void setCheveux(String cheveux){
        this.cheveux = cheveux;
    }
    public void setYeux(String yeux){
        this.yeux = yeux;
    }
    public void setGenie(String genie){
        this.genie =genie;
    }

    @Override
    public String toString() {
        return "Individu [Prenom = " + prenom + ",\t Couleur des cheveux = " + cheveux + ",\t Couleur des yeux = "
                + yeux + ",\t Département de génie = " + genie + "]\n";
    }





}