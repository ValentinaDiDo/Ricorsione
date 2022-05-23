package it.polito.tdp.ricorsione.model;
import java.util.*;
public class QuadratoMagico {
	private int sommaCorretta;
	private int N;
	private int N2;

	public void risolviQuadrato(int N) { // N = dimensione quadrato
		this.N = N;
		this.N2 = N*N;
		this.sommaCorretta = N*(N2+1)/2;
		
		List<Integer> parziale = new ArrayList<Integer>();
		cerca(parziale, 0);
	}
	
	private void cerca(List<Integer> parziale, int livello) {
		
		//caso terminale -> ho inserito N^2 numeri
		if(livello == N*N) {
		if(controllaSomme(parziale)) {//se è giusta allora la stampo
			System.out.println(parziale);
		}
		}else {//caso normale -> prima di scendere di livello mi chiedo se ne vale la pena, in modo tale da evitare di fare controlli inutili
			//prima di continuare controllo somma riga precedente
			
			if(livello % N ==0 && livello != 0) { //dice se è multiplo di n -> % = resto della divisione
			if(!controllaRiga(livello/N-1, parziale))
				return; //se la somma non quadra, esco
			
			}
			
			for(int i = 1; i<=N*N; i++) {
				//devo chiedermi se i è un tentativo valido, ovvero se non è ancora stato usato
				if(!parziale.contains(i)) {
					//aggiungo i alla cella di livello -> ricorsione -> backtracking
					parziale.add(i);
					cerca(parziale, livello+1);
					parziale.remove(parziale.size()-1);
				}
			}
		}
	}
	
	private boolean controllaRiga(int riga, List<Integer> parziale) {
		int s = 0;
		for(int col = 0; col<N; col++) {
			s += parziale.get(riga*N + col);
		}
		if(s!=sommaCorretta)
			return false;
		else
			return true;
	}

	public boolean controllaSomme(List<Integer> parziale) {
		
		//controllo la somma per righe
		for(int riga = 0; riga<N; riga++) {
			int s = 0;
			for(int col = 0; col<N; col++) {
				s += parziale.get(riga*N + col); //questo per trovare la posizione precisa della casella
												 //se riga = 0 -> prendo indice 0+colonna 
			}
			if(s != sommaCorretta)
				return false;
		}
		
		//ora controllo per ogni colonna, la casella relativa ad ogni riga della colonna -> inverto riga e col
		for(int col = 0; col<N; col++) {
			int s = 0;
			for(int riga = 0; riga<N; riga++) {
				s += parziale.get(riga*N + col); //questo per trovare la posizione precisa della casella
												 //se riga = 0 -> prendo indice 0+colonna 
			}
			if(s != sommaCorretta) //se ne becco una sbagliata ritorno già falso
				return false;
		}
		
		//controllo diagonale 1
		int s = 0;
		for(int riga = 0 ; riga<N; riga++) {
			s += parziale.get(riga*N + riga);
		}if(s!=sommaCorretta)
			return false;
		
		//controllo antidiagonale
		s = 0;
		for(int col = 0; col<N; col++) {
			s+= parziale.get(col*N + (N-1-col));
		}
		if(s!=sommaCorretta)
			return false;

		return true;
	}
}
