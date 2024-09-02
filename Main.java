public class Main {
    public static void main(String[] args) {
        Fila filaA = new Fila();
        filaA.inicializa(4);
        int[] numbers_filaA = {12, 35, 52, 64};
        for (int i = 0; i < numbers_filaA.length; i++) {
            filaA.insere(numbers_filaA[i]);
        }
        System.out.println("fila A: ");
        filaA.imprime();

        Fila filaB = new Fila();
        filaB.inicializa(5);
        int[] numbers_filaB = {5, 15, 23, 55, 75};
        for (int i = 0; i < numbers_filaB.length; i++) {
            filaB.insere(numbers_filaB[i]);
        }
        System.out.println("fila B: ");
        filaB.imprime();

        Fila filaC = Merge.mergeFilas(filaA, filaB);

        System.out.println("Fila C após o merge:");
        filaC.imprime();
        System.out.println("Fila A e B após o merge:");
        filaA.imprime();
        filaB.imprime();
    }
}

class Fila {
    private int primeiro;
    private int ultimo;
    private int[] dados;
    private int capacidade;
    private int tamanho;

    public void inicializa(int n) {
        capacidade = n;
        dados = new int[capacidade];
        primeiro = 0;
        ultimo = -1;
        tamanho = 0;
    }

    public boolean cheia() {
        return tamanho == capacidade;
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    public void insere(int E) {
        if (!cheia()) {
            ultimo = (ultimo + 1) % capacidade;
            dados[ultimo] = E;
            tamanho++;
        } else {
            System.out.println("Fila cheia, não é possível inserir o elemento.");
        }
    }

    public int remove() {
        if (!vazia()) {
            int elementoRemovido = dados[primeiro];
            primeiro = (primeiro + 1) % capacidade;
            tamanho--;
            return elementoRemovido;
        } else {
            System.out.println("Fila vazia, não é possível remover um elemento.");
            return -1;
        }
    }

    public int peek() {
        if (!vazia()) {
            return dados[primeiro];
        } else {
            System.out.println("Fila vazia, não há elemento para visualizar.");
            return -1;
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void imprime() {
        if (!vazia()) {
            int index = primeiro;
            for (int i = 0; i < tamanho; i++) {
                System.out.print(dados[index] + " ");
                index = (index + 1) % capacidade;
            }
            System.out.println();
        } else {
            System.out.println("Fila vazia.");
        }
    }
}

class Merge {
    public static Fila mergeFilas(Fila filaA, Fila filaB) {
        Fila filaC = new Fila();
        filaC.inicializa(filaA.getTamanho() + filaB.getTamanho());

        while (!filaA.vazia() && !filaB.vazia()) {
            if (filaA.peek() <= filaB.peek()) {
                filaC.insere(filaA.remove());
            } else {
                filaC.insere(filaB.remove());
            }
        }
        while (!filaA.vazia()) {
            filaC.insere(filaA.remove());
        }
        while (!filaB.vazia()) {
            filaC.insere(filaB.remove());
        }
        return filaC;
    }
}