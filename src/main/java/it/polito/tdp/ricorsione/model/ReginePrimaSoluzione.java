package it.polito.tdp.ricorsione.model;
import java.util.*;

public class ReginePrimaSoluzione {

	public void cercaRegine(int N) {
		//dimensione scacchiera
		List<Integer> parziale = new ArrayList<Integer>();
		regina_ricorsiva(parziale, 0, N);
		
	}
	private void regina_ricorsiva(List<Integer> parziale, int livello, int N)/* N = dim scacchiera */ {
		if(livello == N) { //caso terminale
			//ho trovato soluzione completa perché ho riempito tutte e N le righe
			System.out.println(parziale);
			
		}else { //caso normale
			/* ho già parziale[0] fino a parziale[livello-1] gia decisi
			   devo decidere parziale [livello] tra tutti i valori possibli da 0 a N-1(colonne), purché compatibili*/
			
			for(int col=0; col<N;col++) {
				//se col è compatibile con parziale : 
				if(compatibile(livello, col, parziale)) {
					
					parziale.add(col); //aggiungo una colonna alla soluzione
					regina_ricorsiva(parziale, livello+1, N);
					parziale.remove(parziale.size()-1);
					
					//provo a mettere una regina, risolvo la scacchiera e se ho altre posizioni possibili la tolgo 
					// aggiungo -> provo -> tolgo
				}
			}
		}
	}
	private boolean compatibile(int livello, int col, List<Integer>parziale) {
		
		//se nella lista parziale c'è già il valore 
		if(parziale.indexOf(new Integer (col)) != -1) { //se risulta -1 vuol dire che l'oggetto non è contenuto nella lista
			return false;
		}
		for(int riga=0; riga < parziale.size(); riga++) {
			//coordinate regina (R,C) = (riga, parziale.get(riga)) da confrontare con (R,C) = (livello, col)
			if( riga + parziale.get(riga) == livello + col) {
				//se la somma è uguale non va bene
				return false;
			}if(riga-parziale.get(riga) == livello-col) {
				//anche con la differenza non va bene
				return false;
			}
		}//se non ho trovato roba nella stessa colonna allora va bene
			return true;
	}
}
