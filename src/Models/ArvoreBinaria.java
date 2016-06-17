import java.util.Random;

/**
 * Árvore binária de números inteiros.
 * 
 * @author eraldo
 * 
 */
public class ArvoreBinaria<T> {

	/**
	 * Generador de números aleatórios usado para inserção aleatória.
	 */
	private static Random rand = new Random();

	/**
	 * Nó raiz.
	 */
	protected No<T> raiz;

	/**
	 * Cria uma árvore vazia.
	 */
	public ArvoreBinaria() {
		raiz = null;
	}

	/**
	 * Retorna o número de nós nesta árvore.
	 * 
	 * @return número de nós nesta árvore.
	 */
	public int tamanho() {
		return tamanho(raiz);
	}

	/**
	 * Função recursiva que retorna o número de nós na sub-árvore cuja raiz é o
	 * nó dado <code>n</code>.
	 * 
	 * @return número de nós na sub-árvore cuja raiz é <code>n</code>.
	 */
	protected int tamanho(No<T> n) {
		if (n == null)
			return 0;
		return 1 + tamanho(n.esq) + tamanho(n.dir);
	}

	/**
	 * Retorna um vetor com os valores armazenados nesta árvore.
	 * 
	 * @return
	 */
	public Object[] toArray() {
		Object[] vet = new Object[tamanho()];
		toArray(raiz, vet, 0);
		return vet;
	}

	/**
	 * Método recursivo que preenche o sub-vetor <code>a[pos...]</code> com os
	 * elementos na sub-árvore cuja raiz é <code>n</code>.
	 * 
	 * @param n
	 *            nó raiz da sub-árvore cujos elementos devem ser copiados para
	 *            o vetor.
	 * @param vet
	 *            vetor que receberá os elementos da árvore.
	 * @param pos
	 *            índice do vetor a partir de onde os elemento serão copiados.
	 * @return a quantidade de elementos copiados para o vetor.
	 */
	private int toArray(No<T> n, Object[] vet, int pos) {
		if (n == null)
			return 0;
		int tam = toArray(n.esq, vet, pos);
		vet[pos + tam] = n.info;
		tam += 1;
		tam += toArray(n.dir, vet, pos + tam);
		return tam;
	}

	/**
	 * Exibe nós em pré-ordem.
	 */
	public void exibePreOrdem() {
		exibePreOrdem(raiz);
		System.out.println();
	}

	/**
	 * Exibe nós em pós-ordem.
	 */
	public void exibePosOrdem() {
		exibePosOrdem(raiz);
		System.out.println();
	}

	/**
	 * Exibe nós em-ordem.
	 */
	public void exibeEmOrdem() {
		exibeEmOrdem(raiz);
		System.out.println();
	}

	/**
	 * Método recursivo para exibir em pós-ordem a sub-árvore cuja raiz é
	 * <code>n</code>.
	 * 
	 * @param n
	 *            raiz da sub-árvore a ser exibida.
	 */
	private void exibePreOrdem(No<T> n) {
		if (n == null)
			return;
		System.out.print(" " + n.info);
		exibePreOrdem(n.esq);
		exibePreOrdem(n.dir);
	}

	/**
	 * Método recursivo para exibir a sub-árvore cuja raiz é <code>n</code> em
	 * pós-ordem.
	 * 
	 * @param n
	 *            raiz da sub-árvore a ser exibida.
	 */
	private void exibePosOrdem(No<T> n) {
		if (n == null)
			return;
		exibePosOrdem(n.esq);
		exibePosOrdem(n.dir);
		System.out.print(" " + n.info);
	}

	/**
	 * Método recursivo para exibir em-ordem a sub-árvore cuja raiz é
	 * <code>n</code>.
	 * 
	 * @param n
	 *            raiz da sub-árvore a ser exibida.
	 */
	private void exibeEmOrdem(No<T> n) {
		if (n == null)
			return;
		exibeEmOrdem(n.esq);
		System.out.print(" " + n.info);
		exibeEmOrdem(n.dir);
	}

	/**
	 * Exibe árvore no formato deitada.
	 */
	public void exibeDeitada() {
		if (raiz == null) {
			// Árvore vazia.
			System.out.printf("%s\n", "<null>");
			return;
		}

		// Exibe raiz e chama método recursivo para os seus dois filhos.
		exibeDeitada(raiz.dir, "", false);
		System.out.printf("%s\n", raiz);
		exibeDeitada(raiz.esq, "", true);
		System.out.println();
	}

	/**
	 * Método recursivo que exibe árvore no formato deitada.
	 * 
	 * @param n
	 *            raiz da sub-árvore a ser exibida.
	 * @param prefix
	 *            nível do nó <code>n</code> na árvore original.
	 */
	private void exibeDeitada(No<T> n, String prefix, boolean filhoEsquerdo) {
		if (n == null)
			return;

		// Imprime nó e sub-árvores.
		if (filhoEsquerdo) {
			exibeDeitada(n.dir, prefix + "| ", false);
			System.out.printf("%s%s\n", prefix + "|>", n);
			exibeDeitada(n.esq, prefix + "  ", true);
		} else {
			exibeDeitada(n.dir, prefix + "  ", false);
			System.out.printf("%s%s\n", prefix + "|>", n);
			exibeDeitada(n.esq, prefix + "| ", true);
		}
	}

	/**
	 * Busca o valor dado <code>val</code> na árvore.
	 * 
	 * @param val
	 *            valor a ser buscado (comparação é realizada pelo método
	 *            <code>equals(...)</code>)
	 * @return <code>true</code> caso o valor dado é encontrato na árvore, ou
	 *         <code>false</code> caso contrário.
	 */
	public boolean busca(T val) {
		return busca(raiz, val);
	}

	/**
	 * Busca o valor dado <code>val</code> na sub-árvore cuja raiz é nó dado
	 * <code>n</code>
	 * 
	 * @param n
	 *            raiz da sub-árvore a ser buscada.
	 * @param val
	 *            valor a ser buscado.
	 * @return <true> se o valor é encontrao, <code>falso</code> caso contrário.
	 */
	private boolean busca(No<T> n, T val) {
		if (n == null)
			return false;

		if (n.info.equals(val))
			return true;

		if (busca(n.esq, val))
			return true;

		if (busca(n.dir, val))
			return true;

		return false;
	}

	/**
	 * Insere o valor <code>val</code> na árvore. O local que este novo valor é
	 * armazenado é aleatório.
	 * 
	 * @param val
	 */
	public void insereAleatorio(T val) {
		if (raiz == null) {
			raiz = new No<T>(val);
			return;
		}

		No<T> n = raiz;
		while (true) {
			if (ArvoreBinaria.rand.nextBoolean()) {
				// Esquerda.
				if (n.esq == null) {
					n.esq = new No<T>(val);
					return;
				} else
					n = n.esq;
			} else {
				// Direita.
				if (n.dir == null) {
					n.dir = new No<T>(val);
					return;
				} else
					n = n.dir;
			}
		}
	}

	/**
	 * Retorna <code>true</code> caso a árvore seja vazia.
	 * 
	 * @return
	 */
	public boolean vazia() {
		return raiz == null;
	}
}
