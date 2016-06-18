package Models;

/**
 * Árvore binária de busca de tipo genérico.
 * 
 * @author eraldo
 * 
 */
public class ArvoreBinariaBusca<T extends Comparable<T>> extends
		ArvoreBinaria<T> {

	/**
	 * Insere o elemento <code>val</code> na ABB de acordo com sua ordem
	 * natural.
	 * 
	 * @param val
	 *            valor a ser inserido.
	 */
	public void insere(T val) {
		// Cria nó com o valor dado.
		No<T> n = new No<>(val);

		if (raiz == null) {
			// Árvore está vazia. Insere novo nó como raiz.
			raiz = n;
			return;
		}

		// Busca posição onde novo nó deve ser inserido.
		Aresta<T> a = busca(new Aresta<>(raiz, null), val);

		if (a.no != null) {
			// Valor já está na árvore.
		} else {
			// Valor não está na árvore e a.pai será o pai do novo nó.
			if (val.compareTo(a.pai.info) < 0)
				// Nó inserido como filho esquerdo.
				a.pai.esq = n;
			else
				// Nó inserido como filho direito.
				a.pai.dir = n;
		}
	}

	/**
	 * Busca o valor <code>val</code> na árvore.
	 * 
	 * @param val
	 * @return <code>true</code> se encontrar o valor; <code>false</code> caso
	 *         contrário.
	 */
	public boolean busca(T val) {
		Aresta<T> a = busca(new Aresta<T>(raiz, null), val);
		return a.no != null;
	}

	/**
	 * Busca na sub-árvore, cuja raiz é <code>n</code>, um nó cuja chave é
	 * <code>val</code>.
	 * 
	 * @param a
	 *            aresta contendo a raiz da sub-árvore onde o valor deve ser
	 *            buscado e o pai deste nó.
	 * @param val
	 *            valor a ser buscado.
	 * @return aresta cujo nó é aquele com chave igual a <code>val</code> ou
	 *         <code>null</code>, caso não exista tal nó; e cujo pai é o pai do
	 *         nó encontrado.
	 */
	private Aresta<T> busca(Aresta<T> a, T val) {
		while (a.no != null) {
			int comp = val.compareTo(a.no.info);
			if (comp == 0)
				return a;
			// Anda com a referência do pai.
			a.pai = a.no;
			// Anda com a referência do nó.
			if (comp < 0)
				a.no = a.no.esq;
			else
				a.no = a.no.dir;
		}
		return a;
	}

	/**
	 * Remove o nó que contém o valor <code>val</code> na ABB.
	 * 
	 * @param val
	 *            valor cujo nó será removido.
	 * @return
	 */
	public boolean remove(T val) {
		// Busca o nó (e seu pai) que contenha o valor dado.
		Aresta<T> a = busca(new Aresta<T>(raiz, null), val);

		if (a.no == null)
			// Valor não está na árvore.
			return false;

		if (a.no == raiz) {
			// Valor a ser removido está na raiz.
			raiz = removeRaiz(raiz);
			return true;
		}

		if (a.no == a.pai.esq)
			// O nó a ser removido é filho esquerdo de p.
			a.pai.esq = removeRaiz(a.no);
		else
			// O nó a ser removido é filho direito de p.
			a.pai.dir = removeRaiz(a.no);

		return true;
	}

	/**
	 * Remove o nó raiz da sub-árvore com raiz <code>r</code>.
	 * 
	 * @param r
	 *            raiz de sub-árvore as ser removida.
	 * @return nova raiz da sub-árvore cuja raiz era <code>r</code>. Este nó
	 *         pode ser o filho esquerdo (ou direito) da raiz quando a raiz não
	 *         tem filho direito (ou esquerdo). Quando a raiz tem dois filhos
	 *         não nulos, então a nova raiz é o predecessor (maior valor da
	 *         sub-árvore esquerda) da raiz.
	 */
	private No<T> removeRaiz(No<T> r) {
		if (r.esq == null)
			// Nova raiz é o filho direito.
			return r.dir;

		if (r.dir == null)
			// Nova raiz é o filho esquerdo.
			return r.esq;

		// Raiz a ser removida tem os dois filhos não nulos.
		No<T> p = null;
		// Começa na sub-árvore esquerda...
		No<T> q = r.esq;
		// ... e pega o maior valor.
		while (q.dir != null) {
			p = q;
			q = q.dir;
		}

		/*
		 * O nó encontrado, certamente, não tem filho direito. Portanto, podemos
		 * removê-lo (para assumir o lugar da raiz removida) e colocar seu único
		 * filho no seu lugar.
		 */
		if (p != null) {
			/*
			 * Quando o predecessor da raiz é seu filho, a substituição é mais
			 * simples. Neste caso, os dois passos abaixo não devem ser
			 * realizados.
			 */
			p.dir = q.esq;
			q.esq = r.esq;
		}
		q.dir = r.dir;
		return q;
	}

	/**
	 * Exibe chaves na árvore que estejam no intervalo (<code>a</code>,
	 * <code>b</code>).
	 * 
	 * @param a
	 *            limite inferior (exclusive).
	 * @param b
	 *            limite superior (exclusive).
	 */
	public void exibeIntervalo(T a, T b) {
		exibeIntervalo(raiz, a, b);
		System.out.println();
	}

	/**
	 * Exibe chaves na árvore cuja raiz é <code>n</code> que estejam no
	 * intervalo (<code>a</code>, <code>b</code>).
	 * 
	 * @param n
	 * @param a
	 * @param b
	 */
	private void exibeIntervalo(No<T> n, T a, T b) {
		if (n == null)
			return;

		exibeIntervalo(n.esq, a, b);

		if (a.compareTo(n.info) < 0 && b.compareTo(n.info) > 0)
			System.out.printf("%d ", n.info);

		exibeIntervalo(n.dir, a, b);
	}

	/**
	 * Exibe chaves na árvore que estejam no intervalo (<code>a</code>,
	 * <code>b</code>).
	 * 
	 * Este método é mais eficiente do que o método acima porque não percorre,
	 * necessariamente, toda a árvore. Este método usa a ordenação da árvore
	 * para evitar visitar sub-árvores contendo, exclusivamente, chaves fora do
	 * intervalo solicitado.
	 * 
	 * @param a
	 *            limite inferior (exclusive).
	 * @param b
	 *            limite superior (exclusive).
	 */
	public void exibeIntervaloAbb(T a, T b) {
		exibeIntervaloAbb(raiz, a, b);
		System.out.println();
	}

	/**
	 * Exibe chaves na árvore cuja raiz é <code>n</code> que estejam no
	 * intervalo (<code>a</code>, <code>b</code>).
	 * 
	 * Este método é mais eficiente do que o método acima porque não percorre,
	 * necessariamente, toda a árvore. Este método usa a ordenação da árvore
	 * para evitar visitar sub-árvores contendo, exclusivamente, chaves fora do
	 * intervalo solicitado.
	 * 
	 * @param n
	 * @param a
	 * @param b
	 */
	private void exibeIntervaloAbb(No<T> n, T a, T b) {
		if (n == null)
			return;
		if (a.compareTo(n.info) < 0) {
			exibeIntervaloAbb(n.esq, a, b);
			if (b.compareTo(n.info) > 0) {
				System.out.printf("%d ", n.info);
				exibeIntervaloAbb(n.dir, a, b);
			}
		} else if (b.compareTo(n.info) > 0)
			exibeIntervaloAbb(n.dir, a, b);
	}

	/**
	 * 
	 * @return o menor valor na árvore.
	 */
	public T menor() {
		if (raiz == null)
			return null;
		No<T> n = raiz;
		while (n.esq != null)
			n = n.esq;
		return n.info;
	}

	/**
	 * 
	 * @return o maior valor na árvore.
	 */
	public T maior() {
		if (raiz == null)
			return null;
		No<T> n = raiz;
		while (n.dir != null)
			n = n.dir;
		return n.info;
	}

	/**
	 * Representa uma aresta de uma árvore. Armazena a referência para um nó e
	 * para o pai deste nó.
	 * 
	 * @author eraldo
	 * 
	 * @param <T>
	 *            Tipo do nó.
	 */
	private static class Aresta<T> {
		/**
		 * Nó filho.
		 */
		No<T> no;

		/**
		 * Nó pai.
		 */
		No<T> pai;

		/**
		 * Construtor.
		 * 
		 * @param no
		 *            nó filho.
		 * @param pai
		 *            nó pai.
		 */
		Aresta(No<T> no, No<T> pai) {
			this.no = no;
			this.pai = pai;
		}
	}
}
