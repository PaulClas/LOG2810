package tp4;

import java.util.ArrayList;

public class Porte {
	ArrayList<Instruction> instructionArray;
	ArrayList<Password> passwordArray;
	int nombreInstructions;
	int nombrePassWord;
	String password;
	String nomPorte;
	
	public Porte(String nomPorte,ArrayList<Instruction> instructionArray,ArrayList<Password> passwordArray) {
		this.instructionArray = instructionArray;
		this.passwordArray = passwordArray;
		this.nombreInstructions = instructionArray.size();
		this.nombrePassWord = passwordArray.size();
		this.nomPorte = nomPorte;
	}
	public Porte(String nomPorte,String password) {
		this.nomPorte = nomPorte;
		this.password = password;
	}
	public Porte() {}
	
	public Instruction getinstructionArray(int index) { return instructionArray.get(index); }
	public Password getpasswordArray(int index) { return passwordArray.get(index);}
	public int getNombreInstructions(){ return nombreInstructions;}
	public int getNombrePassWord() {return nombrePassWord;}
	public String getPassword() {return password;}
	public String getNomPorte() {return nomPorte;}
	public ArrayList<Instruction> getArrayInstruction(){return instructionArray;}
	public ArrayList<Password> getMotDePasseValide() {
		ArrayList<Password> passwordValider = new ArrayList<>();
		for(int i=0; i<passwordArray.size();i++) {
			if(passwordArray.get(i).estValide()) {
				passwordValider.add(passwordArray.get(i));
			}
		}
		return passwordValider;
	}
	public boolean estGouffre() {
		if(getMotDePasseValide().size()>0){
			return false;}
		else { return true;}
	}
	public void afficherPorte() {
		System.out.println( nomPorte +" "+password);
	}
	
	
	
}
