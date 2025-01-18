import java.util.NoSuchElementException;

public class ArvBinBusca<T>
{
	private No raiz; /* Raiz da �rvore. */

	private class No
	{
		private final int chave; /* Chave usada nas compara��es. */
        private int h; /* Altura */
		private T valor; /* Informa��o armazenada. */
		private No esq, dir; /* Refer�ncias para sub�rvores esquerda e direita. */

		/* Cria um n� com chave e valor fornecidos e esq = dir = null. */
		public No(int chave, T valor)
		{
			this.chave = chave;
			this.valor = valor;
			this.esq = null;
			this.dir = null;
            this.h = 0;
		}
	}
	
	/**
	 *  Cria��o de uma �rvore vazia. 
	 */
	public ArvBinBusca()
	{
		raiz = null;
	}

    /**
     * Método que mostra a altura da BST
     * 
     * @return a altura da BST
     */

    public int getAltura(){
        return raiz.h;
    }

    /**
     * Método privado que retorna a altura de uma subárvore 
     * 
     * @param no subárvore
     * 
     * @return altura da subárvore
     */

    private int getAltura(No no) {
        return (no == null) ? 
                -1 : no.h;
    }
	
	/** 
	 * Verifica se a �rvore est� vazia.
	 * 
	 * @return se a �rvore est� vazia ou possui algum elemento
	 */
	public boolean vazia()
	{
		return (raiz == null);
	}
	
	/**
	 * Apresenta o conte�do da �rvore em ordem sim�trica.
	 */
	public void mostra()
	{
		mostra(raiz);
	}
	
	private void mostra(No x)
	{
		/* Caso base (crit�rio de parada). */
		if(x == null)
			return;
		
		System.out.print("[");
		
		/* Chamada recursiva para a esquerda. */
		mostra(x.esq);
		
		/* Imprime a chave do n� corrente. */
		System.out.print("<" + x.chave + ">");
		
		/* Chamada recursiva para a direita. */
		mostra(x.dir);
		
		System.out.print("]");
	}
	
    /**
     * Retorna a menor chave da �rvore.
     *
     * @return a menor chave da �rvore
     * @throws NoSuchElementException se a �rvore est� vazia
     */
    public int min()
    {
        if(vazia()) 
        	throw new NoSuchElementException("�rvore est� vazia!");
        
        return min(raiz).chave;
    } 

    private No min(No x) { 
        if (x.esq == null) 
        	return x; 
        else
        	return min(x.esq); 
    }
    
    /**
     * Retorna o maior elemento da �rvore.
     *
     * @return o maior elemento da �rvore
     * @throws NoSuchElementException se a �rvore est� vazia
     */
    public int max() {
        if(vazia()) 
        	throw new NoSuchElementException("A �rvore est� vazia!");
        
        return max(raiz).chave;
    } 

    private No max(No x) {
        if (x.dir == null)
        	return x; 
        else
        	return max(x.dir); 
    }
    
    /**
     * Retorna o n�mero de n�s, isto �, pares (chave, valor), contidos na �rvore 
     * bin�ria de busca.
     * 
     * @return o n�mero de n�s da �rvore
     */
    public int tamanho()
    {
    	return tamanho(raiz);
    }
    
    private int tamanho(No x)
    {
    	/* Caso base (crit�rio de parada). */
    	if(x == null)
    		return 0;
    	
    	/* Chamada recursiva para sub�rvores esquerda e direita. */
    	return 1 + tamanho(x.esq) + tamanho(x.dir);
    }
    
    
    /**
     * Retorna a altura da �rvore bin�ria de busca.
     *
     * @return a altura da �rvore (uma �rvore de um �nico n� possui altura 0)
     */
    public int altura()
    {
    	return altura(raiz);
    }
    
    private int altura(No x)
    {
    	if(x == null)
    		return -1;
    	
    	int maxAltura = Math.max(altura(x.esq), altura(x.dir));
    	
    	return 1 + maxAltura;
    }
    
    
    /**
     * Essa �rvore bin�ria de busca cont�m a chave fornecida?
     *
     * @param  chave a chave fornecida
     * @return {@code true} se a �rvore cont�m a chave {@code chave} e
     *         {@code false} caso contr�rio
     * @throws IllegalArgumentException se {@code key} � {@code null}
     */
    public boolean contem(int chave) {
        if (chave == 0) 
        	throw new IllegalArgumentException("Chave == 0");
        
        return get(chave) != null;
    }
	
    /**
     * Retorna o valor associado � chave fornecida.
     *
     * @param  chave a chave a ser buscada
     * @return o valor associado � chave fornecida se tal chave se encontra na �rvore
     *         e {@code null} se a chave n�o est� na �rvore
     * @throws IllegalArgumentException if {@code chave} is {@code null}
     */
    public T get(int chave) 
    {
        return get(raiz, chave);
    }

    private T get(No x, int chave) 
    {
        if(chave == 0) 
        	throw new IllegalArgumentException("Chave == 0");
        
        /* A chave n�o se encontra na �rvore. */
        if(x == null)
        	return null;

        if(chave < x.chave) /* Deve-se ir para a esquerda. */
        	return get(x.esq, chave);
        else if(chave > x.chave) /* Deve-se ir para a direita. */
        	return get(x.dir, chave);
        else /* Chave encontrada. */
        	return x.valor;
    }
    
    /**
     * Insere na �rvore bin�ria de busca o par (chave, valor) fornecido. Caso a �rvore
     * j� possua a chave especificada, o valor antigo � sobrescrito com o novo valor 
     * fornecido. Remove o n� de chave igual � chave fornecida caso o valor especificado
     * seja {@code null}.
     *
     * @param  chave a chave fornecida
     * @param  valor o valor fornecido
     * @throws IllegalArgumentException se {@code chave} � {@code null}
     */
    public void insere(int chave, T valor)
    {
        raiz = insere(raiz, chave, valor);
    }

    private No insere(No no, int chave, T valor)
    {
    	
        if (no == null) 
            return new No(chave, valor);
            
        if (chave < no.chave)
            no.esq = insere(no.esq, chave, valor);
    
        else if (chave > no.chave)
            no.dir = insere(no.dir, chave, valor);

        else
            no.valor = valor;

        no.h = Math.max(getAltura(no.esq), getAltura(no.dir)) + 1;

        return no;
        
    }
	
    /**
     * Remove o n� de menor chave da �rvore.
     *
     * @throws NoSuchElementException se a �rvore est� vazia
     */
    public void removeMin() 
    {
        if(vazia())
        	throw new NoSuchElementException("A �rvore est� vazia!");
        
        raiz = removeMin(raiz);
    }

    /* M�todo recursivo que anda sempre para a esquerda, procurando o n�
     * de menor chave a ser removido. */
    private No removeMin(No x) 
    {
    	/* Caso n�o haja filho � esquerda, o n� corrente possui a menor chave. */
        if(x.esq == null) 
        	return x.dir;
        
        x.esq = removeMin(x.esq);
        
        return x;
    }

    /**
     * Remove o n� de maior chave da �rvore.
     *
     * @throws NoSuchElementException se a �rvore est� vazia
     */
    public void removeMax()
    {
        if(vazia())
        	throw new NoSuchElementException("A �rvore est� vazia!");
        
        raiz = removeMax(raiz);
    }

    /* M�todo recursivo que anda sempre para a direita, procurando o n�
     * de maior chave a ser removido. */
    private No removeMax(No x) 
    {
        if(x.dir == null)
        	return x.esq;
        
        x.dir = removeMax(x.dir);
        
        return x;
    }
    
    /**
     * Remove o n� cuja chave seja igual � {@code chave} fornecida.
     * 
     * @param chave a chave fornecida
     * @return {@code true} se foi poss�vel remover o n� de chave {@code chave} e
     *         {@code false} caso contr�rio
     */
    public void remove(int chave)
    {    	
    	raiz = remove(raiz, chave);    	
    }
    
    /* Remove o n� com o valor "val" da "�rvore" a partir do n� para o qual est� sendo chamada a fun��o. Obs: "ref_no" � o ponteiro que referencia o n� para o qual est� sendo chamada a fun��o, o qual pode ter que ser modificado. Retorna false se o valor n�o pertencer � "�rvore".
    */
    private No remove(No x, int chave)
    {
    	if (x == null)
    		return null;
    	
    	if(chave < x.chave)
    		x.esq = remove(x.esq, chave);
    	else if(chave > x.chave)
    		x.dir = remove(x.dir, chave);
    	else
    	{ 
    		if(x.dir == null)
    			return x.esq;
    		if(x.esq  == null)
    			return x.dir;
    		
    		No t = x;

    		/* Pega o menor da sub�rvore direita (mais � esquerda). */
    		x = min(t.dir);

    		/* Remove o menor. */
    		x.dir = removeMin(t.dir);

    		/* A sub�rvore esquerda se mant�m a mesma. */
    		x.esq = t.esq;
    	}

    	return x;
    }
    
    
    /**
     * Retorna a maior chave na �rvore que � menor ou igual � {@code chave} fornecida.
     *
     * @param  chave a chave fornecida
     * @return a maior chave na �rvore menor ou igual � {@code chave}
     * @throws NoSuchElementException se a �rvore est� vazia
     * @throws IllegalArgumentException se a {@code chave} � {@code null}
     */
    public int piso(int chave)
    {
        if (vazia()) 
        	throw new NoSuchElementException("A �rvore est� vazia!");
        
        No x = piso(raiz, chave);
        
        if (x == null)
        	return Integer.MIN_VALUE;
        else
        	return x.chave;
    } 

    private No piso(No x, int chave)
    {
        if (x == null)
        	return null;

        if (chave == x.chave)
        	return x;
        
        if (chave < x.chave)
        	return piso(x.esq, chave);
        
        No t = piso(x.dir, chave);

        if (t != null) 
        	return t;
        else
        	return x; 
    }

    
    /**
     * Retorna a menor chave na �rvore que � maior ou igual � {@code chave} fornecida.
     *
     * @param  chave a chave fornecida
     * @return a menor chave na �rvore maior ou igual � {@code chave}
     * @throws NoSuchElementException se a �rvore est� vazia
     * @throws IllegalArgumentException se a {@code chave} � {@code null}
     */
    public int topo(int chave)
    {
        if (vazia())
        	throw new NoSuchElementException("A �rvore est� vazia!");
        
        No x = topo(raiz, chave);
        
        if (x == null)
        	return Integer.MIN_VALUE;
        else 
        	return x.chave;
    }

    private No topo(No x, int chave)
    {
        if (x == null)
        	return null;
        
        if (chave == x.chave)
        	return x;
        
        if (chave < x.chave)
        { 
            No t = topo(x.esq, chave);
            
            if (t != null)
            	return t;
            else
            	return x; 
        } 
        
        return topo(x.dir, chave); 
    } 
}