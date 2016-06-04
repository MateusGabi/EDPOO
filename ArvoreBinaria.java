import java.util.Random;

/**
 * Nó de uma árvore binária genérica.
 * 
 * @author eraldo
 * 
 */
class No<T> {
	/* Definir um nó */
}

/**
 * Árvore binária de números inteiros.
 * 
 * @author eraldo
 * 
 */
public class ArvoreBinaria<T> {

	/**
	 * Generador de números aleatórios usado para inserção aleatória.
	 *
	 * Obs.: Se precisar reproduzir uma árvore que causou um erro, passe um
	 * número inteiro como parâmetro do construtor da classe 
	 * <code>Random</code>. Experimente com diferentes valores até causar o
	 * erro novamente.
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
	 * Exibe árvore no formato deitada.
	 */
	public void exibirDeitada() {
		if (raiz == null) {
			// Árvore vazia.
			System.out.printf("%s\n", "<null>");
			return;
		}

		// Exibe raiz e chama método recursivo para os seus dois filhos.
		exibirDeitada(raiz.dir, "", false);
		System.out.printf("%s\n", raiz.info);
		exibirDeitada(raiz.esq, "", true);
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
	private void exibirDeitada(No<T> n, String prefix, boolean filhoEsquerdo) {
		if (n == null)
			return;

		// Imprime nó e sub-árvores.
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
	 * Programa teste.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Cria uma árvore aleatória com os números 0, 1, ..., 9.
		ArvoreBinaria<Integer> ab = new ArvoreBinaria<>();
		for (int i = 0; i < 10; ++i)
			ab.insereAleatorio(i);

		// Exibe árvore no formato deitada.
		System.out.println("Deitada:");
		ab.exibirDeitada();

		// Exibe elementos da árvore em diferentes ordens.
		System.out.print("Pre-ordem: ");
		ab.exibirPreOrdem();
		System.out.print("Pos-ordem: ");
		ab.exibirPosOrdem();
		System.out.print("Em-ordem: ");
		ab.exibirEmOrdem();
	}
}
