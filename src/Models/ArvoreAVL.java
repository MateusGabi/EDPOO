package Models;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Árvore AVL.
 * 
 * Um árvore AVL é uma árvore binária de busca que mantém o balanceamento entre
 * a altura das sub-árvores esquerda e direita em todos os nós da árvore. Desta
 * forma, as operações de busca, inserção e remoção tem custo assintótico igual
 * a O(log(n)).
 * 
 * @author eraldo
 * 
 */
public class ArvoreAVL<T extends Comparable<T>> extends ArvoreBinariaBusca<T> {

	/**
	 * Retorna a raiz convertida para <code>NoAVL</code>.
	 * 
	 * <p>
	 * Como esta classe extende a classe <code>ArvoreBinariaBusca</code>, cujo
	 * atributo (herdado aqui) é do tipo <code>No</code>, precisamos
	 * frequentemente converter a referência para o nó raiz para o tipo correto
	 * neste caso que é <code>NoAVL</code>. Para evitar esta conversão em vários
	 * pontos do código, criamos este método.
	 * </p>
	 * 
	 * @return referência para o nó raiz do tipo <code>NoAVL<T></code>.
	 */
	public NoAVL<T> getRaiz() {
		return (NoAVL<T>) raiz;
	}

	/**
	 * Insere o valor <code>info</code> na árvore.
	 * 
	 * @param info
	 *            valor a ser inserido no árvore.
	 */
	public void insere(T info) {
		if (raiz == null) {
			raiz = new NoAVL<T>(info);
			return;
		}

		insere(getRaiz(), null, false, info);
	}

	/**
	 * Insere (recursivamente) o valor <code>val</code> na sub-árvore cuja raiz
	 * é <code>n</code>.
	 * 
	 * @param n
	 *            nó da sub-árvore onde o novo valor deve ser inserido.
	 * @param pai
	 *            nó pai de <code>n</code>.
	 * @param filhoEsq
	 *            indica se o <code>n</code> é filho esquerdo de
	 *            <code>pai</code>.
	 * @param val
	 *            valor a ser inserido.
	 * @return <code>true</code> se a altura da sub-árvore cuja raiz é
	 *         <code>n</code> aumentou com a inserção; <code>false</code> caso
	 *         contrário.
	 */
	private boolean insere(NoAVL<T> n, NoAVL<T> pai, boolean filhoEsq, T val) {
		if (val.compareTo(n.info) < 0) {
			if (n.esq == null) {
				n.esq = new NoAVL<T>(val);
				return n.fb++ != 0;
			}
		}
		else if(val.compareTo(n.info) > 0) {
			
		}
		return false;
	}

	/**
	 * Rotaciona à direita a sub-árvore com raiz em <code>n</code>.
	 * 
	 * @param x
	 *            raiz da sub-árvore a ser rotacionada.
	 * @param pai
	 *            pai do nó <code>n</code>.
	 * @param filhoEsq
	 *            indica se <code>n</code> é filho esquerdo de <code>pai</code>.
	 */
	private void rotDir(NoAVL<T> x, NoAVL<T> pai, boolean filhoEsq) {
		// TODO: IMPLEMENTAR!
	}

	/**
	 * Rotaciona à esquerda a sub-árvore com raiz em <code>n</code>.
	 * 
	 * @param x
	 *            raiz da sub-árvore a ser rotacionada.
	 * @param pai
	 *            pai do nó <code>n</code>.
	 * @param filhoEsq
	 *            indica se <code>n</code> é filho esquerdo de <code>pai</code>.
	 */
	private void rotEsq(NoAVL<T> x, NoAVL<T> pai, boolean filhoEsq) {
		// TODO: IMPLEMENTAR!
	}

	/**
	 * Verifica os fatores de balanceamento de todos os nós da árvore.
	 * 
	 * @return <code>true</code> caso todos os FBs estejam corretos;
	 *         <code>false</code> caso contrário.
	 */
	public boolean verificaFBs() {
		return verificaFBs(getRaiz()) != Integer.MIN_VALUE;
	}

	/**
	 * Verifica se os fatores de balanceamento dos nós da sub-árvore cuja raiz é
	 * <code>n</code> estão corretos.
	 * 
	 * @param n
	 *            raiz da sub-árvore a ser verificada.
	 * @return altura da sub-árvore cuja raiz é <code>n</code>.
	 */
	private int verificaFBs(NoAVL<T> n) {
		if (n == null)
			return 0;

		/*
		 * Calcula altura da sub-árvore esquerda enquanto verifica os fatores de
		 * balanceamentos dos seus nós.
		 */
		int hEsq = verificaFBs(n.getEsq());
		if (hEsq == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		/*
		 * Calcula altura da sub-árvore direita enquanto verifica os fatores de
		 * balanceamentos dos seus nós.
		 */
		int hDir = verificaFBs(n.getDir());
		if (hDir == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		if (hEsq - hDir != n.fb) {
			System.err.printf("Erro no nó %s! FB=%d mas deveria ser %d\n", n,
					n.fb, hEsq - hDir);
			return Integer.MIN_VALUE;
		}

		if (n.fb < -1 || n.fb > 1) {
			System.err.printf(
					"Erro no nó %s! FB=%d (fora do intervalo válido)!\n", n,
					n.fb);
			return Integer.MIN_VALUE;
		}

		// Retorna a altura da sub-árvore cuja raiz é o nó n.
		return 1 + Math.max(hEsq, hDir);
	}
}
