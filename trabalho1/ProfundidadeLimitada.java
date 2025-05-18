import java.util.*;

public class ProfundidadeLimitada {

    static class Node {
        int estado;
        List<Integer> caminho;
        int profundidade;

        Node(int estado, List<Integer> caminho, int profundidade) {
            this.estado = estado;
            this.caminho = new ArrayList<>(caminho);
            this.caminho.add(estado);
            this.profundidade = profundidade;
        }
    }

    static int nosVisitados = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o estado inicial: ");
        int estadoInicial = scanner.nextInt();

        System.out.print("Digite o estado objetivo: ");
        int estadoObjetivo = scanner.nextInt();

        System.out.print("Digite o limite de profundidade: ");
        int limite = scanner.nextInt();

        List<Integer> resultado = buscaProfundidadeLimitada(estadoInicial, estadoObjetivo, limite);

        if (resultado != null) {
            System.out.println("Caminho percorrido: " + resultado);
            System.out.println("Quantidade de nós visitados: " + nosVisitados);
            System.out.println("Custo total: " + (resultado.size() - 1));
        } else {
            System.out.println("Estado objetivo não encontrado dentro do limite de profundidade.");
            System.out.println("Quantidade de nós visitados: " + nosVisitados);
        }

        scanner.close();
    }

    public static List<Integer> buscaProfundidadeLimitada(int inicial, int objetivo, int limite) {
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(inicial, new ArrayList<>(), 0));

        while (!stack.isEmpty()) {
            Node atual = stack.pop();
            nosVisitados++;

            if (atual.estado == objetivo) {
                return atual.caminho;
            }

            if (atual.profundidade < limite) {
                // Ações permitidas: +1 e *2
                stack.push(new Node(atual.estado + 1, atual.caminho, atual.profundidade + 1));
                stack.push(new Node(atual.estado * 2, atual.caminho, atual.profundidade + 1));
            }
        }

        return null;
    }
}
