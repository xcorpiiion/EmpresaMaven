package br.com.contmatic.repository;

import java.util.List;

import org.bson.conversions.Bson;

import br.com.contmatic.empresa.Empresa;

public interface IEmpresaRespository {

    public void save(Empresa empresa) throws IllegalAccessException;

    public void update(Empresa empresa);

    public void deleteById(String cnpj) throws IllegalAccessException;

    public Empresa findById(String cnpj);

    public Empresa findByFilter(Bson filter);

    public List<Empresa> findAll();

}
