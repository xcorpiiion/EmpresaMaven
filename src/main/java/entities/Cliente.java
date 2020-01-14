package entities;

public class Cliente extends Pessoa {
	private Double dinheiroCarteira;
	private Integer carrinho;
	
	public Cliente(String nome, String email, Double dinheiro) {
		super(nome, email);
		this.dinheiroCarteira = dinheiro;
	}

	public Double getDinheiroCarteira() {
		return dinheiroCarteira;
	}

	public Integer getCarrinho() {
		return carrinho;
	}
	
	public Integer addItensCarrinho(Integer itens) {
		return this.carrinho += carrinho;
	}

	public void addDinheiroCarteira(Double dinheiro) {
		this.dinheiroCarteira += dinheiro;
	}
	
}
