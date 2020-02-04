package br.com.contmatic.empresa;

import java.util.Date;

import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CadastroCliente {

    @NotNull(message = "A empresa está nullo")
    private Empresa loja;

    public CadastroCliente(Empresa loja) {
        this.loja = loja;
    }

    public Empresa getLoja() {
        return loja;
    }

    public void setLoja(Empresa loja) {
        this.loja = loja;
    }

    public void cadastrarCliente(String nome, String email, Date dataNascimento, Endereco endereco) {
        if (this.loja.clienteExiste(loja, nome, email)) {
            throw new IllegalArgumentException("O cliente já está cadastrado");
        }
        this.loja.getCliente().add(new Cliente(nome, email, dataNascimento, endereco));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(loja).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CadastroCliente other = (CadastroCliente) obj;
        return new EqualsBuilder().append(loja, other.loja).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("loja: ", loja).toString();
    }

}
