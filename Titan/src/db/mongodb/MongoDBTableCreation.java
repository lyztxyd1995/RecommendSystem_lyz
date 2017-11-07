package db.mongodb;

import java.text.ParseException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

// Create tables for MongoDB (all pipelines).
public class MongoDBTableCreation {
  // Run as Java application to create MongoDB tables
  public static void main(String[] args) throws ParseException {
    MongoClient mongoClient = new MongoClient();
    MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);//get connection to MongoDB

    // remove old tables if exists
    db.getCollection("users").drop();
    db.getCollection("items").drop();

    //  create new tables, populate data and create index.
    //users table stores the users information
    db.getCollection("users")
        .insertOne(new Document().append("first_name", "John").append("last_name", "Smith")
            .append("password", "3229c1097c00d497a0fd282d586be050").append("user_id", "1111"));
    // make sure user_id is unique.
    IndexOptions indexOptions = new IndexOptions().unique(true);

    db.getCollection("users").createIndex(new Document("user_id", 1), indexOptions);

    // indexOptions make sure item_id is unique.
    db.getCollection("items").createIndex(new Document("item_id", 1), indexOptions);

    mongoClient.close();
    System.out.println("Import is done successfully.");
  }
}

