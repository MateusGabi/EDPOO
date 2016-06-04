public class ArvoreBinariaNumerica<T extends Number>
	extends ArvoreBinaria<T>{
	
	Double maior;
	
	public Number maiorNumero(){
		return maiorNumero(true, this.raiz);
	}
	
	private Number maiorNumero(boolean firstTime, No<T> raiz) {
		
		if(raiz==null) return maior;
		
		if(firstTime) this.maior = raiz.info.doubleValue();
		
		else if(raiz.info.doubleValue() > maior) this.maior = raiz.info.doubleValue();
		
		maiorNumero(false, raiz.esq);
		maiorNumero(false, raiz.dir);
		
		return maior;
	}

	public static void main(String[] args) {
		ArvoreBinariaNumerica<Integer> ab = new ArvoreBinariaNumerica<>();
		for (int i = 0; i < 1000000; ++i)
			ab.insereAleatorio(i);
		
		ab.exibirDeitada();
		
		System.out.println(ab.maiorNumero());
	}
}
