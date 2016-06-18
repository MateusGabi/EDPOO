package Models;

/**
 * Nó de uma árvore AVL. Além da informação armazenada no nó, das sub-árvores
 * esquerda e direita, esta classe possui um atributo adicional <code>fb</code>
 * que armazena o fator de balanceamento do nó.
 * 
 * @author eraldo
 * 
 */
class NoAVL<T> extends No<T> {
	/**
	 * Fator de balanceamento: altura da sub-árvore esquerda menos altura da
	 * sub-árvore direita.
	 */
	int fb;

	/**
	 * Cria um nó com a informação dada como argumento.
	 * 
	 * @param info
	 */
	NoAVL(T info) {
		super(info);
	}

	/**
	 * Cria nó com a informação e os filhos dados como argumentos.
	 * 
	 * @param info
	 * @param esq
	 * @param dir
	 */
	NoAVL(T info, NoAVL<T> esq, NoAVL<T> dir) {
		super(info, esq, dir);
	}

	/**
	 * Retorna o filho esquerdo já convertido para <code>NoAVL<T></code>.
	 * 
	 * <p>
	 * A classe <code>NoAVL</code> extende a classe <code>No</code> e, portanto,
	 * os atributos <code>esq</code> e <code>dir</code> herdados de
	 * <code>No</code> são do tipo <code>No</code>. Entretanto, na classe
	 * <code>ArvoreAVL</code> usamos apenas nós do tipo <code>NoAVL</code>. Para
	 * evitar realizar esta conversão em diversos pontos do código da
	 * <code>ArvoreAVL</code>, então criamos este método.
	 * </p>
	 * 
	 * @return
	 */
	NoAVL<T> getEsq() {
		return (NoAVL<T>) esq;
	}

	/**
	 * Retorna o filho direito já convertido para <code>NoAVL<T></code>.
	 * 
	 * <p>
	 * A classe <code>NoAVL</code> extende a classe <code>No</code> e, portanto,
	 * os atributos <code>esq</code> e <code>dir</code> herdados de
	 * <code>No</code> são do tipo <code>No</code>. Entretanto, na classe
	 * <code>ArvoreAVL</code> usamos apenas nós do tipo <code>NoAVL</code>. Para
	 * evitar realizar esta conversão em diversos pontos do código da
	 * <code>ArvoreAVL</code>, então criamos este método.
	 * </p>
	 * 
	 * @return
	 */
	NoAVL<T> getDir() {
		return (NoAVL<T>) dir;
	}

	public String toString() {
		return String.format("%s (%d)", info, fb);
	}
}
