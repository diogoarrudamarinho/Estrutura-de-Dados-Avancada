import java.util.ArrayList;
import java.util.List;

public class ArvAVL <T> {
   
    public static class No <T> {
    
        private int chave;
        private T valor;
        private No<T> esq;
        private No<T> dir;
        private int h;
        private int balanc;

        public No(int chave, T valor){
            this.chave = chave;
            this.valor = valor;;
            this.esq = null;
            this.dir = null;
            this.h = 0;
            this.balanc = 0;
        }
    }

    private No<T> raiz;

    /**
     * 
     * Retorna a altura da árvore/subárvore
     * 
     * @return {@code altura} da árvore/subárvore
     */
    public int getAltura() {
        return raiz.h;
    }

    /**
     * Verifica se a árvore está vazia.
     * 
     * @return  {@code true} se estiver vazia.
     *          {@code false} caso tenha pelo menos um nó.
     * 
    */

    public boolean isVazia(){
        return raiz == null;
    }

    /**
     * Verifica se o nó é uma folha.
     * 
     * @return  {@code true} se não tiver filhos, ou seja, se for uma folha.
     *          {@code false} caso tenha pelo menos um filho.
     * 
    */

    public boolean isFolha(No<T> no){
        return no.esq == null && no.dir == null;
    }
     
    /**
     * Calcula a altura de um nó.
     * 
     * @param no árvore/subárvore
     * @return a altura do nó; se o nó for nulo, retorna -1
     * 
    */
    
    private int altura(No<T> no) {
        return (no == null) ? -1 : 
                no.h;
    }

    /**
     * 
     * Retorna nó sucessor
     * 
     * @param no filho direito.
     * 
     * @return {@code no} mais a esquerda.
     * 
     */
    
    private No<T> sucessor(No<T> no){

        if (no.esq != null) 
           return sucessor(no);

        return no;
        
    }

     /**
     * Verifica o balanceamento da árvore.
     * 
     * @param no árvore/subárvore para rotacionar.
     * 
     * @return {@code no} balanceado.
     * 
    */

    private No<T> balanceia(No<T> no){

        if (no.balanc == 2) 
            if (no.esq != null && no.esq.balanc >= 0) 
                no = rotacaoDir(no);
            else
               no = rotacaoDuplaDireita(no);

        else if (no.balanc == -2) 

            if (no.dir != null && no.dir.balanc <= 0) 
                no = rotacaoEsq(no);
            else
                no = rotacaoDuplaEsquerda(no);
        
        return no;
    }

    /**
     * 
     * Faz a rotação pra esquerda.
     * 
     * @param no árvore/subárvore para rotacionar.
     * 
     * @return Nó rotacionado para esquerda.
     * 
    */

    private No<T> rotacaoEsq(No<T> no){

        No<T> noFilho = no.dir;

        no.dir = noFilho.esq;
        noFilho.esq = no;

        no.h = Math.max(altura(no.esq), altura(no.dir));
        noFilho.h = Math.max(altura(no.esq), altura(no.dir));

        return noFilho;
    }

    /**
     * 
     * Faz a rotação pra direita.
     * 
     * @param no árvore/subárvore para rotacionar.
     * 
     * @return Nó rotacionado para direita.
     * 
    */

    private No<T> rotacaoDir(No<T> no){

        No<T> noFilho = no.esq;

        no.esq = noFilho.dir;
        noFilho.dir = no;

        no.h = Math.max(altura(no.esq), altura(no.dir));
        noFilho.h = Math.max(altura(no.esq), altura(no.dir));

        return noFilho;
    }

    /**
     * 
     * Realiza a rotação dupla pra esquerda.
     * 
     * @param no árvore/subárvore para rotacionar.
     * 
     * @return nó balanceado após rotação dupla.
     * 
    */

    private No<T> rotacaoDuplaEsquerda(No<T> no) {

        no.esq = rotacaoDir(no.esq);

        no = rotacaoEsq(no);
        
        no.h = Math.max(altura(no.esq), altura(no.dir)) + 1;

        return no;
    }

    /**
     * 
     * Realiza a rotação dupla pra direita.
     * 
     * @param no árvore/subárvore para rotacionar.
     * 
     * @return nó balanceado após rotação dupla.
     * 
    */

    private No<T> rotacaoDuplaDireita(No<T> no) {

        no.dir = rotacaoEsq(no.dir); 

        no = rotacaoDir(no); 

        no.h = Math.max(altura(no.esq), altura(no.dir)) + 1;

        return no;
    }


    /**
     * Faz a busca de um valor a partir de uma chave.
     * 
     * @param chave do nó requisitado.
     * 
     * @return  {@code valor} do nó encontrado.
     *          {@code Null} caso nó não exista.
     * 
    */

    public T busca(int chave){
        return busca(raiz, chave); 
    }

    /**
     * Faz a busca recursiva do Nó por sua chave.
     * 
     * @param no raiz
     * @param chave do nó requisitado.
     * 
     * @return  {@code valor} do nó encontrado.
     *          {@code Null} caso nó não exista.
    */

    private T busca (No<T> no, int chave){

        if (no == null) 
            return null;
        
        if (chave < no.chave)
            return busca(no.esq, chave);
        else if (chave > no.chave)
            return busca(no.dir, chave);
        else
            return no.valor;
    }

    /**
     * 
     * Imprime a árvore em ordem.
     * 
    */

    public void imprime(){
        imprime(raiz);
    }

    /**
     * 
     * Imprime a árvore em ordem.
     * 
     * @param no raiz
     * 
    */

    private void imprime(No<T> no){

        System.out.print("{");

        if (no != null) 
        {
            imprime(no.esq);
            System.out.println(no.chave + " -> " + no.valor);
            imprime(no.dir);
        }

        System.out.print("}");
    }

    /**
     * 
     * Insere um Nó na Árvore e faz o balanceamento da árvore, caso necessário.
     * 
     * @param chave do novo nó
     * @param valor do novo nó
     * 
    */

    public void insere(int chave, T valor){
        raiz = insere(raiz, chave, valor);
    }

    /**
     * Método privado de inserção.
     * 
     * @param no raiz da árvore
     * @param chave do novo nó
     * @param valor do novo nó
     * 
     * @return Nó
     * 
    */

    private No<T> insere(No<T> no, int chave, T valor){

        if (no == null) 
            return new No<T>(chave, valor);
        
        if (chave < no.chave)
            no.esq = insere(no.esq, chave, valor);

        else if (chave > no.chave)
            no.dir = insere(no.dir, chave, valor);

        else
           no.valor = valor;

        //Atualiza a altura
        no.h = Math.max(altura(no.esq), altura(no.dir)) + 1;

        //Atualiza e verifica o balanceamento
        no.balanc = altura(no.esq) - altura(no.dir);
        no = balanceia(no);

        return no;
    }

    /**
     * 
     * Remove o nó a partir de sua chave e, se necessário, balanceia a árvore.
     * 
     * @param chave do nó a ser removido
     * 
     */

    public void remove(int chave){
       raiz = remove(raiz, chave);
    }

    /**
     * Remove o nó a partir de sua chave.
     * 
     * @param no árvore/subárvore
     * @param chave do nó a ser removido
     * 
     * @return  nó após a remoção,
     *          {@code null} caso remova filho ou não exista chave.
     */

    private No<T> remove(No<T> no, int chave){

        if (no == null) 
            return null;
        
        if (chave < no.chave)
            no.esq = remove(no.esq, chave);

        else if (chave > no.chave)
            no.dir = remove(no.dir, chave);
        
        else
        {
            if (isFolha(no)) 
                return null;

            else if (no.esq == null) 
                return no.dir;
            
            else if (no.dir == null)
                return no.esq;
            
            else
            {
                No<T> sucessor = sucessor(no);

                no.chave = sucessor.chave;
                no.valor = sucessor.valor;

                no.dir = remove(no.dir, sucessor.chave);
            }  
        }
        
        no.h = Math.max(altura(no.esq), altura(no.dir)) + 1;

        return balanceia(no);
    }

    /**
     * Método que calcula a quantidade mínima de nós para uma altura.
     * 
     * @param h altura
     * 
     * @return quantidade Mínima de nós para a altura.
     */

    public static int alturaMinima(int h){

        if(h == 0)
            return 0;
        if(h == 1)
            return 1;
        if(h == 2)
            return 2;
            
        return (alturaMinima(h - 1) + 
                alturaMinima(h - 2) + 1);
    }

    /**
     * Método que busca o nó ancestral em comum entre dois nós
     * 
     * @param chave1 chave do primeiro nó
     * @param chave2 chave do segundo nó
     * 
     * @return O {@code no} ancestral entre dos nós
     */

    public T ancestral(int chave1, int chave2){
        return ancestral(raiz, chave1, chave2).valor;
    }

    /**
     * Método privado que busca recursivamente o nó ancestral em comum entre dois nós.
     * 
     * @param no nó atual
     * @param chave1 chave do primeiro nó
     * @param chave2 chave do segundo nó
     * 
     * @return O {@code no} ancestral entre dos nós
     */

    private No<T> ancestral(No<T> no, int chave1, int chave2){

        if (no == null) 
            return null;
        
        if (chave1 < no.chave && chave2 < no.chave)
            return ancestral(no.esq, chave1, chave2);
        
        if (chave1 > no.chave && chave2 > no.chave)
            return ancestral(no.dir, chave1, chave2);
        
        return no;
            
    }   
    
    /**
     * Método estático que transforma uma árvore BST com atributos de AVL em uma árvore AVL
     * 
     * @param raiz raiz de uma árvore
     * 
     * @return uma árvore AVL
     */

    public static <T> ArvAVL<T> converteEmAVL(ArvAVL<T> raiz){

        return converte(raiz); 
    }

    /**
     * Método estático privado que faz a conversão da BST em AVL
     * 
     * @param arvore árvore a ser convertida
     * 
     * @return uma árvore AVL
     */

    private static <T> ArvAVL<T> converte(ArvAVL<T> arvore){

        List<No<T>> listaNos = criaListaOrdenada(arvore.raiz);
        ArvAVL<T> novaArvore = new ArvAVL<>();

        for (No<T> no : listaNos) 
            novaArvore.insere(no.chave, no.valor);
        
        return novaArvore;
    }

    /**
     * Método estático recursivo que transforma a BST em uma lista ordenada
     * 
     * @param no raiz da árvore
     * 
     * @return lista ordenada de nós.
     */

    private static <T> List<No<T>> criaListaOrdenada(No<T> no){

        List<No<T>> lista = new ArrayList<>();

        if (no != null) 
        {
            lista.addAll(criaListaOrdenada(no.esq));
            lista.add(no);
            lista.addAll(criaListaOrdenada(no.dir));    
        }

        return lista;
    }

}
