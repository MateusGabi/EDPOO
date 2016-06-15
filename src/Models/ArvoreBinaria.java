package Models;

import java.util.Random;

import javax.swing.SingleSelectionModel;

/**
 * NÃ³ de uma Ã¡rvore binÃ¡ria genÃ©rica.
 * 
 * @author eraldo
 * 
 */
class No<T> {
	public No(T val) {
		this.info = val;
	}
	T info;
	No<T> esq;
	No<T> dir;

}

/**
 * Ã�rvore binÃ¡ria de nÃºmeros inteiros.
 * 
 * @author eraldo
 * 
 */
public class ArvoreBinaria<T> {

	/**
	 * Generador de nÃºmeros aleatÃ³rios usado para inserÃ§Ã£o aleatÃ³ria.
	 *
	 * Obs.: Se precisar reproduzir uma Ã¡rvore que causou um erro, passe um
	 * nÃºmero inteiro como parÃ¢metro do construtor da classe 
	 * <code>Random</code>. Experimente com diferentes valores atÃ© causar o
	 * erro novamente.
	 */
	private static Random rand = new Random();

	/**
	 * NÃ³ raiz.
	 */
	protected No<T> raiz;

	/**
	 * Cria uma Ã¡rvore vazia.
	 */
	public ArvoreBinaria() {
		raiz = null;
	}

	/**
	 * Exibe Ã¡rvore no formato deitada.
	 */
	public void exibirDeitada() {
		if (raiz == null) {
			// Ã�rvore vazia.
			System.out.printf("%s\n", "<null>");
			return;
		}

		// Exibe raiz e chama mÃ©todo recursivo para os seus dois filhos.
		exibirDeitada(raiz.dir, "", false);
		System.out.printf("%s\n", raiz.info);
		exibirDeitada(raiz.esq, "", true);
		System.out.println();
	}

	/**
	 * MÃ©todo recursivo que exibe Ã¡rvore no formato deitada.
	 * 
	 * @param n
	 *            raiz da sub-Ã¡rvore a ser exibida.
	 * @param prefix
	 *            nÃ­vel do nÃ³ <code>n</code> na Ã¡rvore original.
	 */
	private void exibirDeitada(No<T> n, String prefix, boolean filhoEsquerdo) {
		if (n == null)
			return;

		// Imprime nÃ³ e sub-Ã¡rvores.
		if (filhoEsquerdo) {
			exibirDeitada(n.dir, prefix + "| ", false);
			System.out.printf("%s%s\n", prefix + "|>", n.info);
			exibirDeitada(n.esq, prefix + "  ", true);
		} else {
			exibirDeitada(n.dir, prefix + "  ", false);
			System.out.printf("%s%s\n", prefix + "|>", n.info);
			exibirDeitada(n.esq, prefix + "| ", true);
		}
	}

	/**
	 * Insere o valor <code>val</code> na Ã¡rvore. O local que este novo valor Ã©
	 * armazenado Ã© aleatÃ³rio.
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
	 * Exibe os valores dos nós folhas da árvore.
	 */
	public void valuesOfLeafs() {
		valuesOfLeafs(raiz);
	}

	private void valuesOfLeafs(No<T> root) {
		if (root.esq == null && root.dir == null) {
			System.out.print(" " + root.info);
			return;
		}

		if (root.esq != null)
			valuesOfLeafs(root.esq);

		if (root.dir != null)
			valuesOfLeafs(root.dir);
	}

	/**
	 * Retorna a altura da árvore.
	 * 
	 * @return
	 */
	public int height() {
		return height(0, raiz);
	}

	private int height(int i, No<T> root) {
		if(root == null) return i;
		
		int esq = height(i+1, root.esq);
		int dir = height(i+1, root.dir);
		
		return (esq > dir) ? esq : dir;
		
	}
	
	/**
	 * Retorna o elemento do nómmais à esquerda da árvore
	 * @return
	 */
	public T mostLeft() {
		return mostLeft(raiz);
	}
	
	private T mostLeft(No<T> root) {
		if(root.esq == null) return root.info;
		else
			return mostLeft(root.esq);
	}
	
	/**
	 * Retorna o elemento do nómmais à direita da árvore
	 * @return
	 */
	public T mostRight() {
		return mostRight(raiz);
	}
	
	private T mostRight(No<T> root) {
		if(root.dir == null) return root.info;
		else
			return mostRight(root.dir);
	}

	/**
	 * Programa teste.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Cria uma Ã¡rvore aleatÃ³ria com os nÃºmeros 0, 1, ..., 9.
		ArvoreBinaria<Integer> ab = new ArvoreBinaria<>();
		for (int i = 0; i < 10; ++i)
			ab.insereAleatorio(i);

		// Exibe Ã¡rvore no formato deitada.
		System.out.println("Deitada:");
		ab.exibirDeitada();
		
		// Exibe elementos da Ã¡rvore em diferentes ordens.
		System.out.print("Pre-ordem: ");
		ab.exibirPreOrdem();
		System.out.println();
		System.out.print("Pos-ordem: ");
		ab.exibirPosOrdem();
		System.out.println();
		System.out.print("Em-ordem: ");
		ab.exibirEmOrdem();
		System.out.println();
		
		int value = 9;
		
		boolean existe = ab.busca(9);
		
		System.out.println("Possui "+ value +"? "+existe);
	}

	public void exibirPreOrdem() {
		printPreOrder(raiz);
	}

	private void printPreOrder(No<T> raiz) {
		
		if(raiz==null) return;
		
		System.out.print(raiz.info);
		
		printPreOrder(raiz.esq);
		printPreOrder(raiz.dir);
		
	}

	public void exibirPosOrdem() {
		
		printPosOrdem(raiz);
		
	}

	private void printPosOrdem(No<T> raiz) {
		if(raiz==null) return;
				
		printPreOrder(raiz.esq);
		printPreOrder(raiz.dir);
		
		System.out.print(raiz.info);
		
	}

	public void exibirEmOrdem() {
		printEmOrdem(raiz);		
	}

	private void printEmOrdem(No<T> node) {
		if(node==null) return;
				
		printPreOrder(node.esq);
		
		System.out.println(node.info);
		
		printPreOrder(node.dir);
		
	}
	
	public boolean busca(T val) {
		return busca(raiz, val);
	}

	private boolean busca(No<T> root, T value) {
		
		if (root == null) return false;

		if (root.info.equals(value)) return true;

		if (busca(root.esq, value)) return true;

		if (busca(root.dir, value)) return true;
		
		return false;
	}
}
