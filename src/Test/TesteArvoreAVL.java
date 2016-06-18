package Test;

import java.util.Random;

import Models.ArvoreAVL;

public class TesteArvoreAVL {

	/**
	 * Quantidade de números a serem inseridos e buscados.
	 */
	private static final int LEN = 50;

	public static void main(String[] args) {
		// Cria nova árvore AVL.
		ArvoreAVL<Integer> avl = new ArvoreAVL<Integer>();

		// Insere números aleatoriamente.
		Random r = new Random();
		for (int i = 0; i < LEN; ++i) {
			avl.insere(r.nextInt(10 * LEN));

			// Verifica fatores de balanceamento de toda a árvore.
			if (!avl.verificaFBs())
				break;
		}

		// Exibe árvore em formato deitada.
		avl.exibeDeitada();
	}

}
