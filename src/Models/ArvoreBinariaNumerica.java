package Models;

public class ArvoreBinariaNumerica<T extends Number> extends ArvoreBinaria<T> {

	private double maior;
	private double sum;
	private int height;

	public Number maiorNumero() {
		return maiorNumero(true, this.raiz);
	}

	private Number maiorNumero(boolean firstTime, No<T> raiz) {

		if (raiz == null)
			return maior;

		if (firstTime)
			this.maior = raiz.info.doubleValue();

		else if (raiz.info.doubleValue() > maior)
			this.maior = raiz.info.doubleValue();

		maiorNumero(false, raiz.esq);
		maiorNumero(false, raiz.dir);

		return maior;
	}

	/**
	 * Retorna a soma de todos os números da árvore.
	 * 
	 * @return
	 */
	private double sum() {
		return sum(raiz);
	}

	public double sum(No<T> root) {

		if (root == null)
			return sum;

		sum += root.info.doubleValue();

		sum(root.esq);
		sum(root.dir);

		return sum;
	}

	public static void main(String[] args) {
		ArvoreBinariaNumerica<Integer> ab = new ArvoreBinariaNumerica<>();
		for (int i = 0; i < 12; ++i)
			ab.insereAleatorio(i);

		ab.exibirDeitada();

		System.out.println("Maior número -> " + ab.maiorNumero());

		// Teste sum()
		System.out.println("Soma dos Valores -> " + ab.sum());

		// Teste valuesOfLeafs()
		System.out.print("As folhas são ->");
		ab.valuesOfLeafs();
		System.out.println();

		// Teste height()
		System.out.println("A altura da árvore é -> " + ab.height());
		
		// Teste mostLeft()
		System.out.println("O elemento mais a esquerda é -> " + ab.mostLeft());

		
		// Teste mostRight()
		System.out.println("O elemento mais a direita é -> " + ab.mostRight());
	}
}
