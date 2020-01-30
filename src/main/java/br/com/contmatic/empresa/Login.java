package br.com.contmatic.empresa;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.constantes.ValidationNullEmptyStringRule;

public class Login {

	private Empresa loja;

	public Login(Empresa loja) {
		setLoja(loja);
	}

	public Empresa getLoja() {
		return loja;
	}

	public void setLoja(Empresa loja) {
		ValidationNullEmptyStringRule.lojaIsNull(loja);
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
		return new HashCodeBuilder().append(loja).toHashCode();
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
		return new EqualsBuilder().append(loja, other.loja).isEquals();
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Login: ").append(getLoja()).toString();
	}
}
