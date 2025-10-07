//dichiarare due numeri e calcolare somma, differenza, divisione e prodotto//


import java.util.Scanner;
 
public class Calcolatrice {
    
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        float a, b, somma, differenza, prodotto, divisione;
        
        System.out.print("Inserisci il primo numero: ");
        a = scanner.nextInt();
        
        System.out.print("Inserisci il secondo numero: ");
        b = scanner.nextInt();
        
        somma = a + b;
        differenza = a - b;
        prodotto = a * b;
        divisione =  a / b ;
        
        System.out.println("la somma e' : " +somma);
        System.out.println("la differenza e' : " +differenza);
        System.out.println("la prodotto e' : " +prodotto);
        System.out.println("la divisione e' : " +divisione);
  }   
}
