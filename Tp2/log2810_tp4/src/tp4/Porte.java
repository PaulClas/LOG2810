package tp4;

import java.util.ArrayList;

public class Porte {
	ArrayList<Instruction> instructionArray;
	ArrayList<Password> passwordArray;
	int nombreInstructions;
	int nombrePassWord;
	
	public Porte(ArrayList<Instruction> instructionArray,ArrayList<Password> passwordArray) {
		this.instructionArray = instructionArray;
		this.passwordArray = passwordArray;
		this.nombreInstructions = instructionArray.size();
		this.nombrePassWord = passwordArray.size();
	}
	public Porte() {}
	
	public Instruction getinstructionArray(int index) { return instructionArray.get(index); }
	
	/*
	public void setEtatInitial(char etatInitial) { this.etatInitial = etatInitial; }
	public char getlettreDuMdp() { return lettreDuMdp; }
	public void setlettreDuMdp(char lettreDuMdp) { this.lettreDuMdp = lettreDuMdp; }
	public char getEtatFinal() { return etatFinal; }
	public void setEtatFinal(char etatFinal) { this.etatFinal = etatFinal; }
	public void afficher() {System.out.println("Etat Initial: "+ getEtatInitial() + " lettreDuMdp: "+getlettreDuMdp()+ " Etat Final: "+ getEtatFinal());}
	*/
	
	
}
