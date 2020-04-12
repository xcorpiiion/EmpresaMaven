package br.com.contmatic.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Conection {

    private static final String HOST = "localhost"; // endereço ip do banco de dados, no nosso caso local

    private static final String DB_NAME = "etapa3";

    private static MongoClient mongoClient;
    
    private MongoDatabase database;

    public void openDatabaseConection() {
        mongoClient = new MongoClient(HOST);
        database = mongoClient.getDatabase(DB_NAME);
    }

    public static void main(String[] args) {
        MongoCollection<Document> collection = database.getCollection("loja");
        FindIterable<Document> find = collection.find();
		MongoCursor<Document> cursor =  find.cursor();
		database.createCollection("a");
        int i = 1;
        List<String> topics = new ArrayList<>();
        topics.add("Business");
        topics.add("Technology");
        topics.add("Sports");
        topics.add("Career");
        collection.insertOne(new Document("nome", topics));
//        while (cursor.hasNext()) { 
//            System.out.println("Documento inserido: " + i); 
//            System.out.println(cursor.next()); 
//            i++;
//         }
    }
    
    public void createCollection() {
    	database.createCollection("a");
    }
    
    
}
