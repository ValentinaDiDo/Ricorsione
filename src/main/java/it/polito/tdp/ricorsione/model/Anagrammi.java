package it.polito.tdp.ricorsione.model;

public class Anagrammi {

	//riceve stringa e stampa tutti i suoi anagrammi
	public void anagramma(String s) { 
		
		//non basta sapere solo qual è il problema generale da risolvere, servono info in più
		//1. la soluzione parziale
		//2. il livello in cui mi trovo
		//3. le lettere che sono rimaste ancora da assegnare
		
		anagramma_ricorsiva("",0, s);
	
	}
	
	
	//Data la soluzione parziale, stampa tutti gli anagrammi che iniziano in quel modo
	private void anagramma_ricorsiva(String parziale, int livello, String rimanenti) {
		
		/*ricevo in ingresso una stringa parziale di soluzione parziale del mio anagramma. Avra tanti caratteri
		quanti saranno i livelli in cui sono arrivato.*/
		
		if(rimanenti.length()==0) { //il piccolo sottoproblema che posso risolvere
			System.out.println(parziale);
			
		}else{ //richiamo il metodo stesso e ricomincio da capo
			//es. parziale = 'AC', livello = 2, rimanenti = "BD"
			
			for(int pos=0; pos<rimanenti.length(); pos++) {
				String nuova_parziale = parziale + rimanenti.charAt(pos);
				String nuova_rimanenti = rimanenti.substring(0,pos) + rimanenti.substring(pos+1);
				anagramma_ricorsiva(nuova_parziale, livello+1, nuova_rimanenti);
			}
		}
		
	}
	public static void main(String[] args) {
		Anagrammi main = new Anagrammi();
		main.anagramma("ABCD");
		
	}
}
