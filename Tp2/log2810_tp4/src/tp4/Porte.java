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
	public Password getpasswordArray(int index) { return passwordArray.get(index);}
	public int getNombreInstructions(){ return nombreInstructions;}
	public int getNombrePassWord() {return nombrePassWord;}
	
	
	
}
