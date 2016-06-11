package Models;

public class ArvoreBinariaDeBusca<T extends Comparable<T>> extends ArvoreBinaria<T> {

	private static class Aresta<T> {
		No<T> no;
		No<T> pai;

		public Aresta(No<T> no, No<T> pai) {
			super();
			this.no = no;
			this.pai = pai;
		}

	}

	/**
	 * m�todo de inser��o
	 * 
	 * @param node
	 */
	public void insert(T value) {
		insert(this.raiz, value);
	}

	private void insert(No<T> node, T value) {
		if (raiz == null) {
			raiz = new No<T>(value);
			return;
		}
		if (node.info == null) {
			node = new No<T>(value);
			return;
		}

		if (value.compareTo(node.info) < 0) {
			if (node.esq == null) {
				node.esq = new No<T>(value);
			} else {
				insert(node.esq, value);
			}
		} else {
			if (node.dir == null) {
				node.dir = new No<T>(value);
			} else {
				insert(node.dir, value);
			}
		}

	}

	/**
	 * m�todo de search
	 * 
	 * @param value
	 * @return
	 */
	public boolean search(T value) {
		return search(raiz, value);
	}

	private boolean search(No<T> node, T value) {
		if (node == null) {
			return false;
		}
		if (node.info.equals(value)) {
			return true;
		} else if (value.compareTo(node.info) < 0) {
			return search(node.esq, value);
		} else if (value.compareTo(node.info) > 0) {
			return search(node.dir, value);
		}

		return false;
	}

	private Aresta<T> buscar(Aresta<T> a, T val) {
		while (a.no != null) {

			int comp = val.compareTo(a.no.info);

			if (comp == 0)
				return a;
			
			a.pai = a.no;
			
			if (comp < 0)
				a.no = a.no.esq;
			else
				a.no = a.no.dir;
		}
		
		return a;
	}

	/**
	 * TODO m�todo de remove
	 * 
	 * @param value
	 */
	public boolean remover(T value) {
		Aresta<T> a = buscar(new Aresta<T>(raiz, null), value);
		
		if(a.no == null) return false;
		if(a.no == raiz) {
			raiz = removerRaiz(raiz);
			return true;
		}
		if(a.no == a.pai.esq)
			a.pai.esq = removerRaiz(a.no);
		else
			a.pai.dir = removerRaiz(a.no);
		
		return true;

	}

	private No<T> removerRaiz(No<T> raiz) {
		if (raiz.esq == null) return raiz.dir;
		if (raiz.dir == null) return raiz.esq;
		
		No<T> p = null;
		No<T> q = raiz.esq;
		
		while (q.dir != null) {
			p = q;
			q = q.dir;
		}
		
		if (p != null) {
			p.dir = q.esq;
			q.esq = raiz.esq;
		}
		
		q.dir = raiz.dir;
		return q;
	}

	public static void main(String[] args) {
		ArvoreBinariaDeBusca<Integer> ab = new ArvoreBinariaDeBusca<Integer>();

		ab.insert(4);
		ab.insert(2);
		ab.insert(5);
		ab.insert(6);
		ab.insert(1);
		ab.insert(3);
		ab.insert(7);

		ab.exibirDeitada();

		// removendo folha
		System.out.println("Há o número 6? " + ab.search(6));
		System.out.println("removendo o 6... " + ab.remover(6));
		System.out.println("Há o número 6? " + ab.search(6));
		ab.exibirDeitada();

		// removendo apenas o q tem filho da direita
		System.out.println("Há o número 5? " + ab.search(5));
		System.out.println("removendo o 5... " + ab.remover(5));
		System.out.println("Há o número 5? " + ab.search(5));
		ab.exibirDeitada();
		
		 // removendo um q tem 2 filhos
		 System.out.println("Há o número 2? " + ab.search(2));
		 System.out.println("removendo o 2... " + ab.remover(2));
		 System.out.println("Há o número 2? " + ab.search(2));
		 ab.exibirDeitada();

		 // removendo raiz
		 System.out.println("Há o número 4? " + ab.search(4));
		 System.out.println("removendo o 4... " + ab.remover(4));
		 System.out.println("Há o número 4? " + ab.search(4));
		 ab.exibirDeitada();
	}
}
