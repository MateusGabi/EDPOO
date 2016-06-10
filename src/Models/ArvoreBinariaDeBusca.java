package Models;

public class ArvoreBinariaDeBusca<T extends Number> extends ArvoreBinaria<T> {
	/**
	 * TODO método de inserção
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
	 * TODO método de search
	 * 
	 * @param value
	 * @return
	 */
	public boolean search(T value) {
		return false;
	}

	/**
	 * TODO método de remove
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

		ab.exibirDeitada();
	}
}
