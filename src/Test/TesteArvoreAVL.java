package Test;

import java.util.Calendar;
import java.util.Random;

import Models.ArvoreAVL;
import Models.ArvoreBinariaBusca;

public class TesteArvoreAVL {

	private static double now() {
		return Calendar.getInstance().getTimeInMillis() / 1000d;
	}

	/**
	 * Quantidade de números a serem inseridos e buscados.
	 */
	private static final int LEN = 50;

	private static void Test(ArvoreBinariaBusca tree, int n) {
		
		
		
	}

	public static void main(String[] args) {
		// Cria nova árvore AVL.

		// Insere números aleatoriamente.
		// Random r = new Random();
		// for (int i = 0; i < LEN; ++i) {
		// avl.insere(r.nextInt(10 * LEN));

		// // Verifica fatores de balanceamento de toda a árvore.
		// if (!avl.verificaFBs())
		// break;
		// }

		// Exibe árvore em formato deitada.
		/* avl.exibeDeitada(); */

		int k = 4;
		
		// valor inicial
		int n = 1000;

		while (k-- > 0) {
			
			System.out.println("DOING..");
			System.out.println("-------------------------------------------------------------");
			
			ArvoreAVL<Integer> avl = new ArvoreAVL<Integer>();
			ArvoreBinariaBusca<Integer> abb = new ArvoreBinariaBusca();

			double start = now();
			
			// inserir seq na ABB
//			System.out.println();
			for (int i = 0; i < n; i++) {
				abb.insere(i);
			}
			System.out.println("Tempo de inser��o sequencial na ABB de "+n +" valores: " + (now() - start));
			
			
			
			start = now();
			
			// busca seq na ABB
			int temp = n;
			while(temp > 0) {
				abb.busca(temp);
				temp--;
			}
			System.out.println("Tempo de busca sequencial na ABB de "+n +" valores: " + (now() - start));

			
			
			start = now();
			
			// inserir seq na AVL			
			for (int i = 0; i < n; i++) {
				avl.insere(i);
			}
			System.out.println("Tempo de inser��o sequencial na AVL de "+n +" valores: " + (now() - start));
			
			
			start = now();
			
			// busca seq na ABB
			temp = n;
			while(temp > 0) {
				abb.busca(temp);
				temp--;
			}
			System.out.println("Tempo de busca sequencial na AVL de "+n +" valores: " + (now() - start));

			// Aleat�rios
			Random r = new Random();
			
			start = now();
			
			// inserir seq na ABB
//			System.out.println();
			for (int i = 0; i < n; i++) {
				abb.insere(r.nextInt(n));
			}
			System.out.println("Tempo de inser��o aleat�ria na ABB de "+n +" valores: " + (now() - start));
			
			
			
			start = now();
			
			// busca seq na ABB
			temp = 0;
			while(temp > 0) {
				abb.busca(r.nextInt(n));
				temp--;
			}
			System.out.println("Tempo de busca aleat�ria na ABB de "+n +" valores: " + (now() - start));

			
			
			start = now();
			
			// inserir seq na AVL			
			for (int i = 0; i < n; i++) {
				avl.insere(r.nextInt(n));
			}
			System.out.println("Tempo de inser��o aleat�ria na AVL de "+n +" valores: " + (now() - start));
			
			
			start = now();
			
			// busca seq na ABB
			temp = n;
			while(temp > 0) {
				abb.busca(r.nextInt(n));
				temp--;
			}
			System.out.println("Tempo de busca aleat�ria na AVL de "+n +" valores: " + (now() - start));

			

			n *= 10;
			System.out.println("-------------------------------------------------------------");
		}
	}

}
