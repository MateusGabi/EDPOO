package Models;

public class ArvoreBinariaDeBusca<T extends Number> extends ArvoreBinaria<T> {
	/**
	 *  m�todo de inser��o
	 * 
	 * @param node
	 */
	public void insert(T value) {
		if (raiz == null) {
			raiz = new No<T>(value);
			return;
		}
		insert(this.raiz, value);
	}

	private void insert(No<T> node, T value) {
		if (node.info == null) {
			node = new No<T>(value);
			return;
		}

		if (value.doubleValue() < node.info.doubleValue()) {
			if (node.esq == null) {
				node.esq = new No<T>(value);
			}
			else {
				insert(node.esq, value);
			}
		}
		else {
			if (node.dir == null) {
				node.dir = new No<T>(value);
			}
			else {
				insert(node.dir, value);
			}			
		}

	}

	/**
	 * TODO m�todo de search
	 * 
	 * @param value
	 * @return
	 */
	public boolean search(T value) {
		return search(raiz, value);
	}
	
	private boolean search(No<T> node, T value){
		if (node == null) {
			return false;
		}
		if (node.info.doubleValue() == value.doubleValue()) {
			return true;
		}
		else if (value.doubleValue() < node.info.doubleValue()) {
			return search(node.esq, value);
		}
		else if (value.doubleValue() > node.info.doubleValue()) {
			return search(node.dir, value);
		}
		
		return false;
	}

	/**
	 * TODO m�todo de remove
	 * 
	 * @param value
	 */
	public void remove(T value) {

	}

	public static void main(String[] args) {
		ArvoreBinariaDeBusca<Integer> ab = new ArvoreBinariaDeBusca<Integer>();

		ab.insert(9);
		ab.insert(7);
		ab.insert(11);
		ab.insert(14);
		ab.insert(2);
		ab.insert(1);
		ab.insert(5);
		ab.insert(15);
		ab.insert(4);
		ab.insert(27);
		ab.insert(3);
		ab.insert(13);
		ab.insert(143);;
		ab.insert(75);
		ab.insert(1123);
		ab.insert(1432);
		ab.insert(2423);
		ab.insert(-1);
		ab.insert(-215);
		ab.insert(115);
		ab.insert(-4);
		ab.insert(237);
		ab.insert(31);
		ab.insert(132331);

		ab.exibirDeitada();
		
		System.out.println(ab.search(12));		
		System.out.println(ab.search(13));
	}
}
