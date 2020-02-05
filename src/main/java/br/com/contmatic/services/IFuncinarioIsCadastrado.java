package br.com.contmatic.services;

import br.com.contmatic.empresa.Empresa;

public interface IFuncinarioIsCadastrado {
    public boolean funcinarioIsCadastrado(Empresa loja, String cpf);
}
