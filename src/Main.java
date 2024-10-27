public class Main {
    public static void main(String[] args) {
        
        ArvAVL<Integer> a = new ArvAVL<>();

       for (int i = 0; i < 10; i++) 
         a.insere(i,i);
 
        a.imprime();
       
    }
}
