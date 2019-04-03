package tp4;

public class Password {
	String motDePasse;
	String nomPorte;
	boolean estValide;
	
	public Password(String motDePasse, String nomPorte) {
		this.motDePasse = motDePasse;
		this.nomPorte=nomPorte;
	}
	public Password(boolean estValide, String motDePasse, String nomPorte) {
		this.estValide = estValide;
		this.motDePasse= motDePasse;
		this.nomPorte = nomPorte;
	}
	
	public Password(){
		
	}
	
	public boolean estValide() { return estValide; }
	public String estValideString(boolean estValide) {
		String valide;
		if(estValide())
			return valide =" valide";
		else return valide = " non valide";
	}
	public void setValide(boolean estValide) { this.estValide = estValide; }
	public String getMotDePasse() { return motDePasse; }
	public void setMdp(String motDePasse) { this.motDePasse = motDePasse; }
	public String getNomPorte() { return nomPorte; }
	public void setNomPorte(String nomPorte) { this.nomPorte = nomPorte; }
	public void afficher() {System.out.println("Mot de Passe: "+ getMotDePasse()+" "+getNomPorte()+ estValideString(estValide) );}
	public void afficherProf() {System.out.println("\t{"+getMotDePasse()+","+getNomPorte()+","+estValideString(estValide)+"}");}
	
	
}
