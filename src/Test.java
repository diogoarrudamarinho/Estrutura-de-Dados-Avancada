import Trabalho_A.Arvores.ArvAVL;

public class Test {
    
    public static void main(String[] args) {
        
        ArvAVL<Integer> a = new ArvAVL<>();

        a.insere(1, 1);
        a.insere(2, 2);
        a.insere(3, 3);

        a.remove(0);

        a.imprime();
    }
}
