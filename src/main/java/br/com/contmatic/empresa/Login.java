package br.com.contmatic.empresa;

import br.com.contmatic.constantes.ValidationNullOrEmpty;

public class Login {
	
	private Empresa loja;

	public Login(Empresa loja) {
		setLoja(loja);
	}

	public Empresa getLoja() {
		return loja;
	}

	public void setLoja(Empresa loja) {
		ValidationNullOrEmpty.lojaIsNull(loja);
		this.loja = loja;
	}

	public boolean verificaLogin(String nome, String email, int verificaFuncOuCliente, Empresa loja) {
		if (verificaFuncOuCliente == 2) {
			if (loja.getFuncionario().stream().anyMatch(
					func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
				return true;
			}
		} else {
			if (loja.getCliente().stream().anyMatch(
					clien -> clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email))) {
				return true;
			}
		}
		return false;
	}

	public Cliente clienteThatDoLogin(String nome, String email, Empresa loja) {
		for (Cliente clien : loja.getCliente()) {
			if (clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email)) {
				return clien;
			}
		}
		throw new IllegalArgumentException("O cliente que fez o login não existe");
	}

	public Funcionario funcionarioThatDoLogin(String nome, String email, Empresa loja) {
		for (Funcionario func : loja.getFuncionario()) {
			if (func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)) {
				return func;
			}
		}
		throw new IllegalArgumentException("O funcionario que fez o login não existe");
	}

	@Override
	public String toString() {
		return "Login [loja=" + getLoja() + "]";
	}
}
