package br.com.contmatic.repository;

import br.com.contmatic.empresa.Empresa;
import org.bson.Document;

import java.util.List;

public interface IEmpresaRespository {

    public void save(Empresa empresa) throws IllegalAccessException;

    public void update(Empresa empresa);

    public void deleteById(String cnpj) throws IllegalAccessException;

    public Document findById(String cnpj);

    public List<Document> findAll();

}
