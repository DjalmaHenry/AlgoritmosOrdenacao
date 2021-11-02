import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int quantidade = 10;
        int op;
        int[] vetor = new int[quantidade];
        Scanner in = new Scanner(System.in);

        clutter(vetor);

        do {
            menu();
            op = in.nextInt();
            switch (op) {
            case 1:
                // Insertion Sort
                System.out.println("Método Insertion Sort:");
                insertionSort(vetor);
                printVector(vetor);
                break;
            case 2:
                // Selection Sort,
                System.out.println("Método Selection Sort:");
                selectionSort(vetor);
                printVector(vetor);
                break;
            case 3:
                // Bubble Sort
                System.out.println("Método Bubble Sort:");
                bubbleSort(vetor);
                printVector(vetor);
                break;
            case 4:
                // Shell Sort
                System.out.println("Método Shell Sort:");
                shellSort(vetor);
                printVector(vetor);
                break;
            case 5:
                // Quick Sort
                System.out.println("Método Quick Sort:");
                quickSort(vetor, 0, quantidade - 1);
                printVector(vetor);
                break;
            case 6:
                // Merge Sort
                System.out.println("Método Merge Sort:");
                mergeSort(vetor, 0, quantidade - 1);
                printVector(vetor);
                break;
            case 7:
                // Heap Sort
                System.out.println("Método Heap Sort:");
                heapSort(vetor);
                printVector(vetor);
                break;
            case 8:
                // Desordena vetor
                clutter(vetor);
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    public static void menu() {
        System.out.println("MENU:");
        System.out.println("1 - Insertion Sort");
        System.out.println("2 - Selection Sort");
        System.out.println("3 - Bubble Sort");
        System.out.println("4 - Shell Sort");
        System.out.println("5 - Quick Sort");
        System.out.println("6 - Merge Sort");
        System.out.println("7 - Heap Sort");
        System.out.println("8 - Desordenar vetor");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    static void printVector(int vetor[]) {
        int n = vetor.length;
        System.out.println("Vetor Ordenado: ");
        for (int i = 0; i < n; ++i)
            System.out.print(vetor[i] + " ");
        System.out.println();
    }

    public static void clutter(int[] vetor) {
        int raio = 100;
        System.out.println("Vetor Desordenado:");

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = (int) (Math.random() * raio);
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] vetor) {
        int j;
        int key;
        int i;

        for (j = 1; j < vetor.length; j++) {
            key = vetor[j];
            for (i = j - 1; (i >= 0) && (vetor[i] > key); i--) {
                vetor[i + 1] = vetor[i];
            }
            vetor[i + 1] = key;
        }
    }

    public static void selectionSort(int[] vetor) {
        for (int fixo = 0; fixo < vetor.length - 1; fixo++) {
            int menor = fixo;

            for (int i = menor + 1; i < vetor.length; i++) {
                if (vetor[i] < vetor[menor]) {
                    menor = i;
                }
            }
            if (menor != fixo) {
                int t = vetor[fixo];
                vetor[fixo] = vetor[menor];
                vetor[menor] = t;
            }
        }
    }

    public static void bubbleSort(int vetor[]) {
        boolean troca = true;
        int aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < vetor.length - 1; i++) {
                if (vetor[i] > vetor[i + 1]) {
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                    troca = true;
                }
            }
        }
    }

    public static void shellSort(int vetor[]) {
        int n = vetor.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = vetor[i];

                int j;
                for (j = i; j >= gap && vetor[j - gap] > temp; j -= gap)
                    vetor[j] = vetor[j - gap];

                vetor[j] = temp;
            }
        }
    }

    private static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo)
                i++;
            else if (pivo < vetor[f])
                f--;
            else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }

    public static void mergeSort(int vetor[], int inicio, int fim) {
        if (inicio < fim) {
            int m = inicio + (fim - inicio) / 2;

            mergeSort(vetor, inicio, m);
            mergeSort(vetor, m + 1, fim);
            merge(vetor, inicio, m, fim);
        }
    }

    public static void merge(int vetor[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = vetor[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = vetor[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                vetor[k] = L[i];
                i++;
            } else {
                vetor[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            vetor[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            vetor[k] = R[j];
            j++;
            k++;
        }
    }

    public static void heapSort(int vetor[]) {
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(vetor, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;
            heapify(vetor, i, 0);
        }
    }

    private static void heapify(int vetor[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && vetor[l] > vetor[largest])
            largest = l;

        if (r < n && vetor[r] > vetor[largest])
            largest = r;

        if (largest != i) {
            int swap = vetor[i];
            vetor[i] = vetor[largest];
            vetor[largest] = swap;

            heapify(vetor, n, largest);
        }
    }
}
