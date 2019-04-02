package tp4;

import java.util.ArrayList;

import tp4.Password;

public class Automate {
	public Automate() {};
	ArrayList<Password> passwordValider = new ArrayList<>();
	public void creerAutomate(Porte porte) {
		for(int i =0; i<porte.getNombreInstructions();i++) {
			porte.getinstructionArray(i).afficher();
		}
		boolean estValide = false;
		//ArrayList<Password> passwordValider = new ArrayList<>();
		for(int i = 0; i< porte.getNombrePassWord();i++) {// Dans le tableau des mots de passe
			int tailleMdp=porte.getpasswordArray(i).getMotDePasse().length();
			char etat = 'S';
			int h =0;
				while(h<tailleMdp) {//dans la rangée des mots de passe
					//int tailleMdp=porte.getpasswordArray(i).getMotDePasse().length();
					int j=0;
						while(j<porte.getNombreInstructions()) {// dans le tableau des instructions
							System.out.println(porte.getNombreInstructions());
							System.out.println("Etat "+ etat);
							System.out.println("Etat Instruction " + porte.getinstructionArray(j).getEtatInitial());
							System.out.println("Lettre qu'on test "+ porte.getpasswordArray(i).getMotDePasse().charAt(h));
							System.out.println("Lettre de l'instruction "+porte.getinstructionArray(j).getlettreDuMdp());
							System.out.println("j"+j);
							System.out.println("h"+ h);
							System.out.println("i"+ i);
							if(etat == porte.getinstructionArray(j).getEtatInitial() && porte.getinstructionArray(j).getlettreDuMdp() == porte.getpasswordArray(i).getMotDePasse().charAt(h)){
								etat = porte.getinstructionArray(j).getEtatFinal();
								estValide = true;
								j=porte.getNombreInstructions();
								}
							else {
								estValide = false;
								j++;
								}
						}
						h++;
					}
				if(estValide == true) {
					passwordValider.add(new Password(estValide,porte.getpasswordArray(i).getMotDePasse(),porte.getpasswordArray(i).getNomPorte()));
				}else {
					passwordValider.add(new Password(estValide,porte.getpasswordArray(i).getMotDePasse(),porte.getpasswordArray(i).getNomPorte()));
				}
						
		}
		//return passwordValider;
		for(int i =0; i<passwordValider.size();i++) {
			passwordValider.get(i).afficher();
		}

	}	
	

}
