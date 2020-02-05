package br.com.contmatic.services;

import java.math.BigDecimal;

import br.com.contmatic.empresa.Empresa;

public interface IHasProdutoInList {
    
    public boolean hasProdutoInList(Empresa loja, String nome, BigDecimal preco);
}
