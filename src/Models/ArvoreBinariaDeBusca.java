package Models;

import java.util.ArrayList;
import java.util.List;

public class ArvoreBinariaDeBusca<T extends Comparable<T>> extends
        ArvoreBinaria<T> {

    private int length = 0;

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
        length++;
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

            if (comp == 0) {
                return a;
            }

            a.pai = a.no;

            if (comp < 0) {
                a.no = a.no.esq;
            } else {
                a.no = a.no.dir;
            }
        }

        return a;
    }

    /**
     * TODO m�todo de remove
     *
     * @param value
     */
    public boolean remover(T value) {

        length--;

        Aresta<T> a = buscar(new Aresta<T>(raiz, null), value);

        if (a.no == null) {
            return false;
        }
        if (a.no == raiz) {
            raiz = removerRaiz(raiz);
            return true;
        }
        if (a.no == a.pai.esq) {
            a.pai.esq = removerRaiz(a.no);
        } else {
            a.pai.dir = removerRaiz(a.no);
        }
        return true;

    }

    private No<T> removerRaiz(No<T> raiz) {
        if (raiz.esq == null) {
            return raiz.dir;
        }
        if (raiz.dir == null) {
            return raiz.esq;
        }

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

    public No<T> maior() {
        return maior(this.raiz);
    }

    private No<T> maior(No<T> raiz) {

        if (raiz.dir == null) {
            return raiz;
        } else {
            return maior(raiz.dir);
        }
    }

    public No<T> menor() {
        return menor(this.raiz);
    }

    private No<T> menor(No<T> raiz) {

        if (raiz.esq == null) {
            return raiz;
        } else {
            return menor(raiz.esq);
        }
    }

    public T menorQue(T value) {
        Object[] array = this.emOrdem();

        int i = 0;
        while ((i < array.length) && (((T) array[i]).compareTo(value) < 0)) {
            i++;
        }

        if (i + 1 == array.length) {
            System.out.println("Não há.");
            return null;
        }

        return (T) array[i];
    }

    public void entre(T a, T b) {
        if (a.compareTo(b) >= 0) {
            System.out.println("a deve ser menor que b");
        } else {
            entre(a, b, raiz);
        }
    }

    private void entre(T a, T b, No<T> node) {
        // Se node. info for maior que a, ir para esquerda

        // Se node.info for menor que a; 
        // Se estiver entre
        if (node != null) {
            entre(a, b, node.esq);
            // save(node.info, array);
            // System.out.print(node.info+"() ");
            if ((node.info.compareTo(a) > 0) && (b.compareTo(node.info) > 0)) {
                System.out.print(node.info + " ");
            }
            entre(a, b, node.dir);
        }
    }

    public Object[] emOrdem() {
        List list_temp = new ArrayList<>();
        list_temp = emOrdem(raiz, list_temp);

        Object[] array = new Object[length];

        int i = 0;
        for (Object object : list_temp) {
            array[i++] = object;
        }

        return array;
    }

    private List emOrdem(No<T> node, List array) {
        if (node != null) {
            emOrdem(node.esq, array);
            // save(node.info, array);
            // System.out.print(node.info+"() ");
            array.add(node.info);
            emOrdem(node.dir, array);
        }

        return array;
    }

    private void save(T info, Object[] array) {
        int i = 0;
        while (array[i++] != null) {
        }
        array[i] = info;
    }

    public static void main(String[] args) {
        ArvoreBinariaDeBusca<Integer> ab = new ArvoreBinariaDeBusca<Integer>();

        ab.insert(50);
        ab.insert(30);
        ab.insert(70);
        ab.insert(20);
        ab.insert(40);
        ab.insert(60);
        ab.insert(80);
        ab.insert(15);
        ab.insert(25);
        ab.insert(35);
        ab.insert(45);
        ab.insert(36);

        ab.exibirDeitada();

        // removendo folha
        int val = 30;
        System.out.println("Há o número " + val + "? " + ab.search(val));
        System.out.println("removendo o " + val + "... " + ab.remover(val));
        System.out.println("Há o número " + val + "? " + ab.search(val));
        ab.exibirDeitada();
		
//         // removendo apenas o q tem filho da direita
//         System.out.println("Há o número 5? " + ab.search(5));
//         System.out.println("removendo o 5... " + ab.remover(5));
//         System.out.println("Há o número 5? " + ab.search(5));
//         ab.exibirDeitada();
//        
//         // removendo um q tem 2 filhos
//         System.out.println("Há o número 2? " + ab.search(2));
//         System.out.println("removendo o 2... " + ab.remover(2));
//         System.out.println("Há o número 2? " + ab.search(2));
//         ab.exibirDeitada();
//        
//         // removendo raiz
//         System.out.println("Há o número 4? " + ab.search(4));
//         System.out.println("removendo o 4... " + ab.remover(4));
//         System.out.println("Há o número 4? " + ab.search(4));
//         ab.exibirDeitada();
         

        // testando maior número
        System.out.println("O maior número é " + ab.maior().info);

        // testando maior número
        System.out.println("O menor número é " + ab.menor().info);

        // testando tamanho da árvore
        System.out.println("O tamanho da árvore é " + ab.length);

        // testando array de elementos
        Object[] array = ab.emOrdem();
        for (Object info : array) {
            System.out.printf("%d ", info);
        }
        System.out.println();

        // Testando menorQue();
        System.out.println("\nTestando menorQue(): ");
        System.out.println(ab.menorQue(30));

        // Testando entre();
        int a=30, b=70;
        System.out.println("\nTestando entre("+a+", "+b+") :");
        ab.entre(a, b);
    }
}
