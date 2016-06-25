package Models;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Ã�rvore AVL.
 * 
 * Um Ã¡rvore AVL Ã© uma Ã¡rvore binÃ¡ria de busca que mantÃ©m o balanceamento entre
 * a altura das sub-Ã¡rvores esquerda e direita em todos os nÃ³s da Ã¡rvore. Desta
 * forma, as operaÃ§Ãµes de busca, inserÃ§Ã£o e remoÃ§Ã£o tem custo assintÃ³tico igual
 * a O(log👎).
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
	 * atributo (herdado aqui) Ã© do tipo <code>No</code>, precisamos
	 * frequentemente converter a referÃªncia para o nÃ³ raiz para o tipo correto
	 * neste caso que Ã© <code>NoAVL</code>. Para evitar esta conversÃ£o em vÃ¡rios
	 * pontos do cÃ³digo, criamos este mÃ©todo.
	 * </p>
	 * 
	 * @return referÃªncia para o nÃ³ raiz do tipo <code>NoAVL<T></code>.
	 */
	public NoAVL<T> getRaiz() {
		return (NoAVL<T>) raiz;
	}

	/**
	 * Insere o valor <code>info</code> na Ã¡rvore.
	 * 
	 * @param info
	 *            valor a ser inserido no Ã¡rvore.
	 */
	public void insere(T info) {
		if (raiz == null) {
			raiz = new NoAVL<T>(info);
			return;
		}

		insere(getRaiz(), null, false, info);
	}

	/**
	 * Insere (recursivamente) o valor <code>val</code> na sub-Ã¡rvore cuja raiz
	 * Ã© <code>n</code>.
	 * 
	 * @param n
	 *            nÃ³ da sub-Ã¡rvore onde o novo valor deve ser inserido.
	 * @param pai
	 *            nÃ³ pai de <code>n</code>.
	 * @param filhoEsq
	 *            indica se o <code>n</code> Ã© filho esquerdo de
	 *            <code>pai</code>.
	 * @param val
	 *            valor a ser inserido.
	 * @return <code>true</code> se a altura da sub-Ã¡rvore cuja raiz Ã©
	 *         <code>n</code> aumentou com a inserÃ§Ã£o; <code>false</code> caso
	 *         contrÃ¡rio.
	 */
	private boolean insere(NoAVL<T> n, NoAVL<T> pai, boolean filhoEsq, T val) {
		if (val.compareTo(n.info) < 0) {
			if (n.esq == null) {
				n.esq = new NoAVL<T>(val);
				n.fb +=1;
				return n.fb != 0;
			}
			if (insere(n.getEsq(), n, true, val)) {
				n.fb++;
				if (n.fb > 1) {
					if (n.getEsq().fb < 0)
						rotEsq(n.getEsq(), n, true);
					rotDir(n, pai, filhoEsq);
					return false;
				}
				return n.fb != 0;
			}
			return false;
		} else {			
			if(n.dir == null){
				n.dir = new NoAVL<T>(val);
				n.fb -=1;
				return n.fb != 0;
			}
			if(insere(n.getDir(), n, false, val)){
				n.fb--;
				if(n.fb < -1){
					if(n.getDir().fb > 0)
						rotDir(n.getDir(), n, false);
					rotEsq(n, pai, filhoEsq);
					return false;
				}
				return n.fb != 0;
			}
			return false;
		}
	}

	/**
	 * Rotaciona Ã  direita a sub-Ã¡rvore com raiz em <code>n</code>.
	 * 
	 * @param x
	 *            raiz da sub-Ã¡rvore a ser rotacionada.
	 * @param pai
	 *            pai do nÃ³ <code>n</code>.
	 * @param filhoEsq
	 *            indica se <code>n</code> Ã© filho esquerdo de <code>pai</code>.
	 */
	private void rotDir(NoAVL<T> x, NoAVL<T> pai, boolean filhoEsq) {
		NoAVL<T> z = x.getEsq();
		z = x.getEsq();
		x.esq = z.getDir();
		z.dir = x;
		x.fb -= 1;
		if(z.fb > 0)
			x.fb -= z.fb;
		z.fb -=1;
		if(x.fb < 0)
			z.fb += x.fb;
		if(pai == null)
			raiz = z;
		else if(filhoEsq){
			pai.esq = z;
		}
		else{
			pai.dir = z;
		}
	}

	/**
	 * Rotaciona Ã  esquerda a sub-Ã¡rvore com raiz em <code>n</code>.
	 * 
	 * @param x
	 *            raiz da sub-Ã¡rvore a ser rotacionada.
	 * @param pai
	 *            pai do nÃ³ <code>n</code>.
	 * @param filhoEsq
	 *            indica se <code>n</code> Ã© filho esquerdo de <code>pai</code>.
	 */
	private void rotEsq(NoAVL<T> x, NoAVL<T> pai, boolean filhoEsq) {
		NoAVL<T> z = x.getDir();
		z = x.getDir();
		x.dir = z.getEsq();
		z.esq = x;
		x.fb += 1;
		if(z.fb < 0)
			x.fb -= z.fb;
		z.fb +=1;
		if(x.fb > 0)
			z.fb += x.fb;
		if(pai == null)
			raiz = z;
		else if(filhoEsq){
			pai.esq = z;
		}
		else{
			pai.dir = z;
		}
		
	}

	/**
	 * Verifica os fatores de balanceamento de todos os nÃ³s da Ã¡rvore.
	 * 
	 * @return <code>true</code> caso todos os FBs estejam corretos;
	 *         <code>false</code> caso contrÃ¡rio.
	 */
	public boolean verificaFBs() {
		return verificaFBs(getRaiz()) != Integer.MIN_VALUE;
	}

	/**
	 * Verifica se os fatores de balanceamento dos nÃ³s da sub-Ã¡rvore cuja raiz Ã©
	 * <code>n</code> estÃ£o corretos.
	 * 
	 * @param n
	 *            raiz da sub-Ã¡rvore a ser verificada.
	 * @return altura da sub-Ã¡rvore cuja raiz Ã© <code>n</code>.
	 */
	private int verificaFBs(NoAVL<T> n) {
		if (n == null)
			return 0;

		/*
		 * Calcula altura da sub-Ã¡rvore esquerda enquanto verifica os fatores de
		 * balanceamentos dos seus nÃ³s.
		 */
		int hEsq = verificaFBs(n.getEsq());
		if (hEsq == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		/*
		 * Calcula altura da sub-Ã¡rvore direita enquanto verifica os fatores de
		 * balanceamentos dos seus nÃ³s.
		 */
		int hDir = verificaFBs(n.getDir());
		if (hDir == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		if (hEsq - hDir != n.fb) {
			System.err.printf("Erro no nÃ³ %s! FB=%d mas deveria ser %d\n", n,
					n.fb, hEsq - hDir);
			return Integer.MIN_VALUE;
		}

		if (n.fb < -1 || n.fb > 1) {
			System.err.printf(
					"Erro no nÃ³ %s! FB=%d (fora do intervalo vÃ¡lido)!\n", n,
					n.fb);
			return Integer.MIN_VALUE;
		}

		// Retorna a altura da sub-Ã¡rvore cuja raiz Ã© o nÃ³ n.
		return 1 + Math.max(hEsq, hDir);
	}
	
	public void atualizaCaminho(Deque<NoAVL<T>> caminho, boolean removeuDaEsquerda) {
		while(caminho.size() > 0) {
			NoAVL<T> n = caminho.pop();
			NoAVL<T> pai = caminho.peekFirst();
			
			boolean nEsquerdaDePai = false;
			
			if(pai!=null)
				nEsquerdaDePai = (pai.esq == n);
			
			if (removeuDaEsquerda) {
				n.fb--;
				
				if (n.fb == -1) {
					return;
				}
				
				if (n.fb == -2) {
					if (n.getDir().fb > 0) {
						rotDir(n.getDir(), n, false);
						rotEsq(n, pai, nEsquerdaDePai);
					}
					else if (n.getDir().fb < 0) {
						rotEsq(n, pai, nEsquerdaDePai);
					}
					else {
						rotEsq(n, pai, nEsquerdaDePai);
						return;
					}
				}
			}
			else {
				// TODO CUZAOO
			}
			
			removeuDaEsquerda = nEsquerdaDePai;
		}
	}
}