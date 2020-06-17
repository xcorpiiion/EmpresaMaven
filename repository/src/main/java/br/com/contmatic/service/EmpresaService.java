package br.com.contmatic.service;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.repository.IEmpresaRespository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.lang.reflect.Field;
import java.util.List;

public class EmpresaService implements IEmpresaRespository {

    private final String HOST = "localhost";

    private final String DB_NAME = "etapa3";

    private MongoClient mongoClient;

    private MongoDatabase database;

    private MongoCollection<Empresa> collection;

    private MongoDatabase getMongoDatabase() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(createCodecRegistry())
                .build();
        mongoClient = new MongoClient(HOST, MongoClientOptions.builder().codecRegistry(createCodecRegistry()).build());
        return mongoClient.getDatabase(DB_NAME).withCodecRegistry(createCodecRegistry());
    }

    private void connectAndGetCollection() {
        database = Conection.getMongoDatabase().withCodecRegistry(createCodecRegistry());
        collection = database.getCollection("loja", Empresa.class).withCodecRegistry(createCodecRegistry());
    }

    @Override
    public void save(Empresa empresa) throws IllegalArgumentException {
        connectAndGetCollection();
        CodecRegistry codecRegistry = createCodecRegistry();
        if (findById(empresa.getCnpj()) != null) {
            throw new IllegalArgumentException("Empresa já cadastrada");
        }
        collection.withCodecRegistry(codecRegistry).insertOne(empresa);
    }

    private CodecRegistry createCodecRegistry() {
        return CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    }

    @Override
    public void update(Empresa empresa) {
        connectAndGetCollection();
        CodecRegistry codecRegistry = createCodecRegistry();
        Bson findByCnpj = Filters.eq("empresa", empresa.getCnpj());
        collection.withCodecRegistry(codecRegistry).replaceOne(findByCnpj, empresa);
    }

    @Override
    public void deleteById(String cnpj) throws NullPointerException {
        connectAndGetCollection();
        Bson findByCnpj = Filters.eq("empresa", cnpj);
        if (findByCnpj == null) {
            throw new NullPointerException("Empresa não encontrada");
        }
        collection.deleteOne(Filters.eq("empresa", cnpj));
    }

    @Override
    public Empresa findById(String cnpj) {
        connectAndGetCollection();
        Bson findByCnpj = Filters.eq("cnpj", cnpj);
        return collection.find(findByCnpj).first();
    }

    @Override
    public Empresa findByFilter(Bson filter) {
        connectAndGetCollection();
        return collection.find(filter).first();
    }

    @Override
    public List<Empresa> findAll() {
        connectAndGetCollection();
        FindIterable<Empresa> empresas = collection.find();
        return (List<Empresa>) empresas;
    }

    private Document putValuesInFields(Empresa empresa) {
        Field[] declaredFields = empresa.getClass().getDeclaredFields();
        Document empresaDocument = new Document();
        for (Field campo : declaredFields) {
            try {
                campo.setAccessible(true);
                empresaDocument.append(campo.getName(), campo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return empresaDocument;
    }
}
