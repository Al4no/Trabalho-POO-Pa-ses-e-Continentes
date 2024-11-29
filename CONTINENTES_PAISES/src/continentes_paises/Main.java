package continentes_paises;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Continente continente = new Continente("Default");

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            exibirMenu();

            int opcao = lerOpcao();
            switch (opcao) {
                case 1 -> adicionarPais();
                case 2 -> listarPaises();
                case 3 -> exibirStatusContinente();
                case 4 -> verificarFronteira();
                case 5 -> calcularDensidadePais();
                case 6 -> adicionarContinente();
                case 7 -> {
                    System.out.println("Encerrando o programa. Até logo!");
                    running = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Adicionar País");
        System.out.println("2. Listar Países");
        System.out.println("3. Exibir Status do Continente");
        System.out.println("4. Verificar Fronteira Entre Países");
        System.out.println("5. Calcular Densidade Populacional de um País");
        System.out.println("6. Adicionar Continente");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpa a entrada inválida
            return -1; // Retorna um valor inválido
        }
    }

    // Métodos já existentes permanecem os mesmos
    private static void adicionarPais() {
        System.out.print("Código ISO: ");
        String codigoISO = scanner.next();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Dimensão (Km2): ");
        double dimensao = scanner.nextDouble();
        System.out.print("População: ");
        long populacao = scanner.nextLong();

        Pais pais = new Pais(codigoISO, nome, dimensao);
        pais.setPopulacao(populacao);
        continente.adicionarPais(pais);

        System.out.println("País adicionado com sucesso!");
    }

    private static void listarPaises() {
        System.out.println("\nPaíses no continente " + continente.getNome() + ":");
        if (continente.getPaises().isEmpty()) {
            System.out.println("Nenhum país cadastrado.");
        } else {
            for (Pais pais : continente.getPaises()) {
                System.out.printf("- %s (%s)\n", pais.getNome(), pais.getCodigoISO());
            }
        }
    }

    private static void exibirStatusContinente() {
        System.out.println("\nStatus do Continente " + continente.getNome() + ":");

        if (continente.getPaises().isEmpty()) {
            System.out.println("O continente não possui países cadastrados.");
            return;
        }

        System.out.println("População Total: " + continente.populacaoTotal());
        System.out.println("Dimensão Total: " + continente.dimensaoTotal());
        System.out.println("Densidade Populacional: " + continente.densidadePopulacional());

        Pais maiorPopulacao = continente.paisMaiorPopulacao();
        Pais menorPopulacao = continente.paisMenorPopulacao();
        Pais maiorDimensao = continente.paisMaiorDimensao();
        Pais menorDimensao = continente.paisMenorDimensao();

        System.out.println("País Maior População: " + (maiorPopulacao != null ? maiorPopulacao.getNome() : "N/A"));
        System.out.println("País Menor População: " + (menorPopulacao != null ? menorPopulacao.getNome() : "N/A"));
        System.out.println("País Maior Dimensão: " + (maiorDimensao != null ? maiorDimensao.getNome() : "N/A"));
        System.out.println("País Menor Dimensão: " + (menorDimensao != null ? menorDimensao.getNome() : "N/A"));

        double razaoTerritorial = continente.razaoTerritorial();
        System.out.println("Razão Territorial (Maior/Menor): " + (razaoTerritorial > 0 ? razaoTerritorial : "N/A"));
    }

    private static void verificarFronteira() {
        System.out.print("Digite o código ISO do primeiro país: ");
        Pais p1 = encontrarPais(scanner.next());
        System.out.print("Digite o código ISO do segundo país: ");
        Pais p2 = encontrarPais(scanner.next());

        if (p1 != null && p2 != null) {
            System.out.println("Fronteira entre " + p1.getNome() + " e " + p2.getNome() + ": " +
                    (p1.eLimitrofe(p2) ? "Sim" : "Não"));
        } else {
            System.out.println("Um ou ambos os países não foram encontrados.");
        }
    }

    private static void calcularDensidadePais() {
        System.out.print("Digite o código ISO do país: ");
        Pais pais = encontrarPais(scanner.next());

        if (pais != null) {
            System.out.println("Densidade Populacional de " + pais.getNome() + ": " + pais.densidadePopulacional());
        } else {
            System.out.println("País não encontrado.");
        }
    }

    private static void adicionarContinente() {
        System.out.print("Digite o nome do novo continente: ");
        continente = new Continente(scanner.next());
        System.out.println("Continente criado!");
    }

    private static Pais encontrarPais(String codigoISO) {
        return continente.getPaises().stream()
                .filter(pais -> pais.getCodigoISO().equalsIgnoreCase(codigoISO))
                .findFirst()
                .orElse(null);
    }
}

