package tp4;

public class Instruction {
	
	char etatInitial;
	char lettreDuMdp;
	char etatFinal;
	
	public Instruction() {};
	public Instruction(char etatInitial, char lettreDuMdp, char etatFinal) {
		this.etatInitial = etatInitial;
		this.lettreDuMdp = lettreDuMdp;
		this.etatFinal = etatFinal;
	}
	public char getEtatInitial() { return etatInitial; }
	public void setEtatInitial(char etatInitial) { this.etatInitial = etatInitial; }
	public char getlettreDuMdp() { return lettreDuMdp; }
	public void setlettreDuMdp(char lettreDuMdp) { this.lettreDuMdp = lettreDuMdp; }
	public char getEtatFinal() { return etatFinal; }
	public void setEtatFinal(char etatFinal) { this.etatFinal = etatFinal; }
	public void afficher() {System.out.println("Etat Initial: "+ getEtatInitial() + " lettreDuMdp: "+getlettreDuMdp()+ " Etat Final: "+ getEtatFinal());}

}
