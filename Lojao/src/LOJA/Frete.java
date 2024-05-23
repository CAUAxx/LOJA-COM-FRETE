package LOJA;

import java.util.ArrayList;

public class Frete {
    private int id;
    private int cepInicial;
    private int cepFinal;
    private double valorPorKilo;
	private Frete[] fretes;

    public Frete(int id, int cepInicial, int cepFinal, double valorPorKilo) {
        this.id = id;
        this.cepInicial = cepInicial;
        this.cepFinal = cepFinal;
        this.valorPorKilo = valorPorKilo;
    }

    public double calcularFrete(Carrinho carrinho, ArrayList<ItensCarrinho> itens, ArrayList<Produto> produtos, int cepEntrega) {
        double pesoTotal = 0.0;

        for (ItensCarrinho item : itens) {
            for (Produto produto : produtos) {
                if (produto.getUpc().equals(item.getUpc())) {
                    pesoTotal += produto.getPeso() * item.getQtde();
                }
            }
        }

        fretes = null;
        
		for (Frete frete : fretes) {
            if (cepEntrega >= frete.getCepInicial() && cepEntrega <= frete.getCepFinal()) {
    
            	double freteFinal = pesoTotal * frete.getValorPorKilo();
                return freteFinal;
            }
        }

        return -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCepInicial() {
        return cepInicial;
    }

    public void setCepInicial(int cepInicial) {
        this.cepInicial = cepInicial;
    }

    public int getCepFinal() {
        return cepFinal;
    }

    public void setCepFinal(int cepFinal) {
        this.cepFinal = cepFinal;
    }

    public double getValorPorKilo() {
        return valorPorKilo;
    }

    public void setValorPorKilo(double valorPorKilo) {
        this.valorPorKilo = valorPorKilo;
    }
}
