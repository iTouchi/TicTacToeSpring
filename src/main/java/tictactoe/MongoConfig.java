package tictactoe;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
@EnableMongoAuditing
public class MongoConfig extends AbstractMongoConfiguration {

    // Abstract MongoCOnfiguration does work with JAVA version 1.8 but is depricated in 11

    @Override
    protected String getDatabaseName() {
        return "TicTacToeSpring";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient();
    }
}
