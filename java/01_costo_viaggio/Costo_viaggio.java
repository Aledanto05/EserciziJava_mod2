/* Realizzare un programma in java che consenta di calcolare il costo di un viaggio conoscendo il numero di km percorsi il costo della benzina a litro e
 *il consumo dell'auto e aggiungere eventuali costi di pedaggio dell'autostrada*/

import java.util.Scanner;
public class Costo_viaggio {
        
    public Costo_viaggio() {
    }
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        
        int  costo_pedaggio = 3, numero_di_pedaggi, consumo_auto, costo_viaggio;
        
        System.out.print("Quanti pedaggi farai? ");
        numero_di_pedaggi = scanner.nextInt();
        
        System.out.print("Quanto ha consumato la tua auto? ");
        consumo_auto = scanner.nextInt();
        
        costo_viaggio = costo_pedaggio * numero_di_pedaggi + consumo_auto;
        
        System.out.print("Il costo totale del viaggio e': " + costo_viaggio);
    
    	
    }
    
}

