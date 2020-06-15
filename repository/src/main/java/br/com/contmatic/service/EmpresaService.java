package br.com.contmatic.service;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.repository.IEmpresaRespository;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EmpresaService implements IEmpresaRespository {

    private final String HOST = "localhost";

    private final String DB_NAME = "etapa3";

    private MongoClient mongoClient;

    private MongoDatabase database;

    private MongoCollection<Document> collection;

    private MongoDatabase getMongoDatabase() {
        mongoClient = new MongoClient(HOST);
        return mongoClient.getDatabase(DB_NAME);
    }

    private void connectAndGetCollection() {
        database = Conection.getMongoDatabase();
        collection = database.getCollection("loja");
    }

    @Override
    public void save(Empresa empresa) throws IllegalArgumentException {
        connectAndGetCollection();
        if(findById(empresa.getCnpj()) != null) {
            throw new IllegalArgumentException("Empresa já cadastrada");
        }
        List<String> empresaAtributos = putValuesInFields(empresa);
        Document document = new Document("empresa", empresaAtributos);
        collection.insertOne(document);
    }

    @Override
    public void update(Empresa empresa) {
        connectAndGetCollection();
        List<String> empresaAtributos = putValuesInFields(empresa);
        Bson findByCnpj = Filters.eq("empresa", empresa.getCnpj());
        collection.replaceOne(findByCnpj, new Document("empresa", empresaAtributos));
    }

    private List<String> putValuesInFields(Empresa empresa) {
        Field[] declaredFields = empresa.getClass().getDeclaredFields();
        List<String> empresaAtributos = new ArrayList<>();
        for (Field campo : declaredFields) {
            try {
                campo.setAccessible(true);
                empresaAtributos.add(campo.get(empresa).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return empresaAtributos;
    }

    @Override
    public void deleteById(String cnpj) throws IllegalArgumentException {
        connectAndGetCollection();
        Bson findByCnpj = Filters.eq("empresa", cnpj);
        if(findByCnpj == null) {
            throw new IllegalArgumentException("Empresa não encontrada");
        }
        collection.deleteOne(Filters.eq("empresa", cnpj));
    }

    @Override
    public Document findById(String cnpj) {
        connectAndGetCollection();
        Bson findByCnpj = Filters.eq("empresa", cnpj);
        return collection.find(findByCnpj).first();
    }

    @Override
    public List<Document> findAll() {
        connectAndGetCollection();
        MongoCursor<Document> cursor = collection.find().iterator();
        List<Document> empresas = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                empresas.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return empresas;
    }
}
