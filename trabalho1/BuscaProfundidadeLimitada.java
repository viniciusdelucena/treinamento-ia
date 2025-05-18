import java.util.*;

public class BuscaProfundidadeLimitada {

    // Classe que representa um nó da árvore
    static class No {
        int numeroAtual;
        List<Integer> caminhoPercorrido;
        int nivelProfundidade;

        No(int numeroAtual, List<Integer> caminhoAnterior, int nivelProfundidade) {
            // Cria o novo caminho com o número atual
            this.numeroAtual = numeroAtual;
            this.caminhoPercorrido = new ArrayList<>(caminhoAnterior);
            this.caminhoPercorrido.add(numeroAtual);
            this.nivelProfundidade = nivelProfundidade;
        }
    }

    // Variável global para contar quantos nós foram visitados
    static int totalNosVisitados = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Recebendo os dados do usuário
        System.out.print("Digite o número inicial: ");
        int numeroInicial = scanner.nextInt();

        System.out.print("Digite o número objetivo: ");
        int numeroObjetivo = scanner.nextInt();

        System.out.print("Digite o limite de profundidade: ");
        int limite = scanner.nextInt();

        // Chama a função de busca
        List<Integer> resultado = buscar(numeroInicial, numeroObjetivo, limite);

        // Mostra o resultado
        if (resultado != null) {
            System.out.println("Caminho encontrado: " + resultado);
            System.out.println("Quantidade de nós visitados: " + totalNosVisitados);
            System.out.println("Custo total: " + (resultado.size() - 1));
        } else {
            System.out.println("Não foi possível encontrar o número dentro do limite.");
            System.out.println("Quantidade de nós visitados: " + totalNosVisitados);
        }

        scanner.close();
    }

    // Função que faz a busca em profundidade limitada
    public static List<Integer> buscar(int inicio, int objetivo, int limite) {
        Stack<No> pilha = new Stack<>();

        // Começa a busca a partir do número inicial
        pilha.push(new No(inicio, new ArrayList<>(), 0));

        // Enquanto ainda houverem nós na pilha para visitar
        while (!pilha.isEmpty()) {
            No atual = pilha.pop(); // Pega o nó do topo da pilha
            totalNosVisitados++; // Conta esse nó como visitado

            // Se encontramos o número objetivo, retornamos o caminho até ele
            if (atual.numeroAtual == objetivo) {
                return atual.caminhoPercorrido;
            }

            // Se ainda não chegou no limite de profundidade
            if (atual.nivelProfundidade < limite) {
                // Adiciona as duas ações possíveis: +1 e *2
                pilha.push(new No(atual.numeroAtual + 1, atual.caminhoPercorrido, atual.nivelProfundidade + 1));
                pilha.push(new No(atual.numeroAtual * 2, atual.caminhoPercorrido, atual.nivelProfundidade + 1));
            }
        }

        // Se não achou o número, retorna null
        return null;
    }
}
