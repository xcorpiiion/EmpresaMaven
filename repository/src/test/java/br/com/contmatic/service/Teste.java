package br.com.contmatic.service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class Teste {

    private static final String DATABASE_NAME = "embedded";

    private MongodExecutable mongodExe;

    private MongodProcess mongod;

    private MongoClient mongo;

    @Before
    public void beforeEach() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        String bindIp = "localhost";
        int port = 12345;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();
        this.mongodExe = starter.prepare(mongodConfig);
        this.mongod = mongodExe.start();
        this.mongo = new MongoClient(bindIp, port);
    }

    @Test
    public void deve_criar_um_novo_objeto_embedded_mongo_db() {
        MongoDatabase db = mongo.getDatabase(DATABASE_NAME);
        db.createCollection("testCollection");
        MongoCollection<BasicDBObject> col = db.getCollection("testCollection", BasicDBObject.class);
        col.insertOne(new BasicDBObject("testDoc", new Date()));
        Assert.assertEquals(1L, col.countDocuments());
    }

    @After
    public void afterEach() throws Exception {
        if (this.mongod != null) {
            this.mongod.stop();
            this.mongodExe.stop();
        }
    }


}
