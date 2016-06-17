/**
 * Nó de uma árvore binária genérica.
 * 
 * @author eraldo
 * 
 */
class No<T> {
	/**
	 * Informação armazenada neste nó.
	 */
	T info;

	/**
	 * Filho esquerdo.
	 */
	No<T> esq;

	/**
	 * Filho direito.
	 */
	No<T> dir;

	/**
	 * Cria novo nó com a informação dada.
	 * 
	 * @param info
	 */
	No(T info) {
		this.info = info;
	}

	/**
	 * Cria novo nó com a informação e os filhos dados.
	 * 
	 * @param info
	 * @param esq
	 * @param dir
	 */
	No(T info, No<T> esq, No<T> dir) {
		this.info = info;
		this.esq = esq;
		this.dir = dir;
	}

	public String toString() {
		return info.toString();
	}
}
