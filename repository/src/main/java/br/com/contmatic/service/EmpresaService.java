package br.com.contmatic.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.repository.IEmpresaRespository;

public class EmpresaService implements IEmpresaRespository {

	private String host = "localhost";

	private String dbName = "etapa3";

	private MongoCollection<Empresa> collection;

	private MongoDatabase getMongoDatabase() {
		MongoClient mongoClient = mongoClient();
		return mongoClient.getDatabase(dbName).withCodecRegistry(createCodecRegistry());
	}

	private MongoClient mongoClient() {
		return new MongoClient(host, MongoClientOptions.builder().codecRegistry(createCodecRegistry()).build());
	}
	
	private void closeMongoConnection() {
		mongoClient().close();
	}

	private void connectAndGetCollection() {
		MongoDatabase database = getMongoDatabase().withCodecRegistry(createCodecRegistry());
		collection = database.getCollection("loja", Empresa.class).withCodecRegistry(createCodecRegistry());
	}

	@Override
	public void save(Empresa empresa) {
		connectAndGetCollection();
		CodecRegistry codecRegistry = createCodecRegistry();
		if (findById(empresa.getCnpj()) != null) {
			throw new IllegalArgumentException("Empresa já cadastrada");
		}
		collection.withCodecRegistry(codecRegistry).insertOne(empresa);
		closeMongoConnection();
	}

	private CodecRegistry createCodecRegistry() {
		return CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	}

	@Override
	public void update(Empresa empresa) {
		connectAndGetCollection();
		CodecRegistry codecRegistry = createCodecRegistry();
		Bson findByCnpj = Filters.eq("cnpj", empresa.getCnpj());
		collection.withCodecRegistry(codecRegistry).replaceOne(findByCnpj, empresa);
		closeMongoConnection();
	}

	@Override
	public void deleteById(String cnpj) {
		connectAndGetCollection();
		if (findById(cnpj) == null) {
			throw new IllegalArgumentException("Empresa não encontrada");
		}
		collection.deleteOne(Filters.eq("cnpj", cnpj));
		closeMongoConnection();
	}

	@Override
	public Empresa findById(String cnpj) {
		connectAndGetCollection();
		Bson findByCnpj = Filters.eq("cnpj", cnpj);
		Empresa empresa = collection.find(findByCnpj).first();
		closeMongoConnection();
		return empresa;
	}

	@Override
	public List<Empresa> findAll() {
		connectAndGetCollection();
		MongoCursor<Empresa> cursor = collection.find().iterator();
		List<Empresa> empresas = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				empresas.add(cursor.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		closeMongoConnection();
		return empresas;
	}

}
