package br.com.contmatic.service;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.repository.IEmpresaRespository;
import br.com.contmatic.telefone.Telefone;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import br.com.contmatic.telefone.Telefone.*;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.util.*;

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
        CodecRegistry codecRegistry = createCodecRegistry();
        if (findById(empresa.getCnpj()) != null) {
            throw new IllegalArgumentException("Empresa já cadastrada");
        }
        Document document = new Document("empresa", empresa);
        collection.withCodecRegistry(codecRegistry).insertOne(document);
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
        collection.withCodecRegistry(codecRegistry).replaceOne(findByCnpj, new Document("empresa", empresa));
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
