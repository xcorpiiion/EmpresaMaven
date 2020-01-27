package br.com.contmatic.empresa;

import java.util.Date;

import br.com.contmatic.constantes.ValidationNullOrEmpty;

public class CadastroCliente {
	
	private Empresa loja;

	public CadastroCliente(Empresa loja) {
		setLoja(loja);
	}

	public Empresa getLoja() {
		return loja;
	}

	public void setLoja(Empresa loja) {
		ValidationNullOrEmpty.lojaIsNull(loja);
		this.loja = loja;
	}
	
	public void cadastrarCliente(String nome, String email, Date dataNascimento,
			Endereco endereco) {
		ValidationNullOrEmpty.lojaIsNull(this.loja);
		if (this.loja.clienteExiste(loja, nome, email)) {
			throw new IllegalArgumentException("O cliente já está cadastrado");
		}
		this.loja.getCliente().add(new Cliente(nome, email, dataNascimento, endereco));
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
		CadastroCliente other = (CadastroCliente) obj;
		if (loja == null) {
			if (other.loja != null)
				return false;
		} else if (!loja.equals(other.loja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CadastroCliente [loja=" + getLoja() + "]";
	}
	
}
