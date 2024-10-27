/**
 * Classe para testes
 */

public class Main {
    public static void main(String[] args) {

        /*
        ArvAVL<Integer> a = new ArvAVL<>();

        for (int i = 0; i < 10; i++) 
            a.insere(i,i);
 
        a.imprime();
        */
        System.out.println("Quantidade minima de nós: ");
        for (int i = 1; i <= 10; i++)
            System.out.println("N("+ i + ") = " + ArvAVL.alturaMinima(i));
        
        
    }
}
