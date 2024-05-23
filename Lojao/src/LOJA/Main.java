package LOJA;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criar produtos
        ArrayList<Produto> produtos = new ArrayList<>();
        Produto prod1 = new Produto("Shampoo", "456789", "sdfrg456", "p/ cabelo", 12.34, 0.500);
        Produto prod2 = new Produto("Condicionador", "55555", "ajj555", "p/ cabelo", 12.45, 0.500);
        produtos.add(prod1);
        produtos.add(prod2);

        // Criar lista de fretes
        ArrayList<Frete> fretes = new ArrayList<>();
        fretes.add(new Frete(1, 20000, 29999, 10.00));
        fretes.add(new Frete(2, 10000, 19999, 11.00));
        fretes.add(new Frete(3, 30000, 39999, 9.00));
        fretes.add(new Frete(4, 40000, 49999, 8.00));
        fretes.add(new Frete(5, 50000, 59999, 7.00));
        fretes.add(new Frete(6, 60000, 69999, 6.00));
        fretes.add(new Frete(7, 70000, 79999, 5.00));
        fretes.add(new Frete(8, 80000, 89999, 4.00));
        fretes.add(new Frete(9, 90000, 99999, 3.00));
        fretes.add(new Frete(10, 100000, 109999, 2.00));
        fretes.add(new Frete(11, 110000, 119999, 1.50));
        fretes.add(new Frete(12, 120000, 129999, 1.00));
        fretes.add(new Frete(13, 130000, 139999, 0.80));
        fretes.add(new Frete(14, 140000, 149999, 0.70));
        fretes.add(new Frete(15, 150000, 159999, 0.60));


        // Criar carrinho
        Carrinho carrinho = new Carrinho();
        carrinho.setId(1);

        // Criar itens do carrinho
        ArrayList<ItensCarrinho> itens = new ArrayList<>();
        ItensCarrinho item1 = new ItensCarrinho(1, 1, "456789", 12.34, 2);
        ItensCarrinho item2 = new ItensCarrinho(2, 1, "55555", 12.45, 3);
        itens.add(item1);
        itens.add(item2);

        // Criar objeto Menu e processar
        Menu menu = new Menu();
        menu.Processamento();

        // Calcular frete
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CEP de entrega: ");
        int cepEntrega = sc.nextInt();

        Frete freteUtilizado = null;
        for (Frete frete : fretes) {
            if (cepEntrega >= frete.getCepInicial() && cepEntrega <= frete.getCepFinal()) {
                freteUtilizado = frete;
                break;
            }
        }

        if (freteUtilizado != null) {
            double freteFinal = freteUtilizado.calcularFrete(carrinho, itens, produtos, cepEntrega);
            System.out.println("O valor do frete é: R$" + freteFinal);
        } else {
            System.out.println("CEP de entrega não encontrado na lista de fretes.");
        }
    }
}
