package LOJA;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private Carrinho carrinho = new Carrinho();
    private ArrayList<ItensCarrinho> itens = new ArrayList<>();
    private ArrayList<Frete> fretes = new ArrayList<>();

    public void Processamento() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        // Adicionando produtos
        produtos.add(new Produto("Shampoo", "456789", "sdfrg456", "p/ cabelo", 12.34, 0.500));
        produtos.add(new Produto("Condicionador", "55555", "ajj555", "p/ cabelo", 12.45, 0.500));

        // Criando carrinho e itens
        carrinho.setId(1);
        itens.add(new ItensCarrinho(1, 1, "456789", 12.34, 2));
        itens.add(new ItensCarrinho(2, 1, "55555", 12.45, 3));

        // Adicionando fretes
        fretes.add(new Frete(1, 20000, 29999, 10.00));
        fretes.add(new Frete(1, 10000, 19999, 11.00));

        do {
            System.out.println("Digite a opção:");
            System.out.println("1- Incluir Produto");
            System.out.println("2- Alterar Produto");
            System.out.println("3- Excluir Produto");
            System.out.println("4- Listar Produtos");
            System.out.println("5- Buscar Produto");
            System.out.println("6- Calcular Frete");
            System.out.println("7- Sair ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    IncluirProduto();
                    break;
                case 2:
                    AlterarProduto();
                    break;
                case 3:
                    ExcluirProduto();
                    break;
                case 4:
                    ListarProduto();
                    break;
                case 5:
                    BuscarProduto();
                    break;
                case 6:
                    CalcularFrete();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }
        } while (opcao != 7);
    }

    private void IncluirProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.println("Digite o UPC do produto: ");
        String upc = sc.nextLine();
        System.out.println("Digite o SKU do produto: ");
        String sku = sc.nextLine();
        System.out.println("Digite a descrição do produto: ");
        String descricao = sc.nextLine();
        System.out.println("Digite o valor do produto: ");
        double valor = sc.nextDouble();
        System.out.println("Digite o peso do produto: ");
        double peso = sc.nextDouble();

        produtos.add(new Produto(nome, upc, sku, descricao, valor, peso));
        System.out.println("Produto incluído com sucesso!");
    }

    private void AlterarProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o UPC do produto a ser alterado: ");
        String upc = sc.nextLine();

        for (Produto produto : produtos) {
            if (produto.getUpc().equals(upc)) {
                System.out.println("Digite o novo valor do produto: ");
                double novoValor = sc.nextDouble();
                produto.setValor(novoValor);
                System.out.println("Produto alterado com sucesso!");
                return;
            }
        }

        System.out.println("Produto não encontrado.");
    }

    private void ExcluirProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o UPC do produto a ser excluído: ");
        String upc = sc.nextLine();

        for (Produto produto : produtos) {
            if (produto.getUpc().equals(upc)) {
                produtos.remove(produto);
                System.out.println("Produto excluído com sucesso!");
                return;
            }
        }

        System.out.println("Produto não encontrado.");
    }

    private void ListarProduto() {
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " - " + produto.getValor());
        }
    }

    private void BuscarProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o UPC do produto a ser buscado: ");
        String upc = sc.nextLine();

        for (Produto produto : produtos) {
            if (produto.getUpc().equals(upc)) {
                System.out.println("Produto encontrado:");
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Valor: " + produto.getValor());
                return;
            }
        }

        System.out.println("Produto não encontrado.");
    }

    private void CalcularFrete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CEP de entrega: ");
        int cepEntrega = sc.nextInt();

        double pesoTotal = 0.0;
        for (ItensCarrinho item : itens) {
            for (Produto produto : produtos) {
                if (produto.getUpc().equals(item.getUpc())) {
                    pesoTotal += produto.getPeso() * item.getQtde();
                }
            }
        }

        Frete freteUtilizado = null;
        for (Frete frete : fretes) {
            if (cepEntrega >= frete.getCepInicial() && cepEntrega <= frete.getCepFinal()) {
                freteUtilizado = frete;
                break;
            }
        }

        if (freteUtilizado != null) {
            double freteFinal = pesoTotal * freteUtilizado.getValorPorKilo();
            System.out.println("O valor do frete é: R$" + freteFinal);
        } else {
            System.out.println("CEP de entrega não encontrado na lista de fretes.");
        }
    }
}
