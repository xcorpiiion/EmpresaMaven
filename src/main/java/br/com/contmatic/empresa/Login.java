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
		if (verificaFuncOuCliente == 2 && loja.getFuncionario().stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
			return true;
		} else {
			return verificaFuncOuCliente == 1 && loja.getCliente().stream().anyMatch(
					clien -> clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email));
		}
	}

	public Cliente clienteThatDoLogin(String nome, String email, Empresa loja) {
		for (Cliente cliente : loja.getCliente()) {
			if (cliente.getNome().equalsIgnoreCase(nome) && cliente.getEmail().equalsIgnoreCase(email)) {
				return cliente;
			}
		}
		throw new IllegalArgumentException("O cliente que fez o login não existe");
	}

	public Funcionario funcionarioThatDoLogin(String nome, String email, Empresa loja) {
		for (Funcionario funccionario : loja.getFuncionario()) {
			if (funccionario.getNome().equalsIgnoreCase(nome) && funccionario.getEmail().equalsIgnoreCase(email)) {
				return funccionario;
			}
		}
		throw new IllegalArgumentException("O funcionario que fez o login não existe");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loja == null) ? 0 : loja.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (loja == null) {
			if (other.loja != null)
				return false;
		} else if (!loja.equals(other.loja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [loja=" + getLoja() + "]";
	}
}
