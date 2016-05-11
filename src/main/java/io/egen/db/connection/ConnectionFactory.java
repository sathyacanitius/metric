package io.egen.db.connection;

import io.egen.config.PropertiesConfig;
import io.egen.dto.WeightDTO;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

@Component
public class ConnectionFactory {
	
	private MongoClient mongoClient;
	
	@Autowired
	private PropertiesConfig propertiesConfig;
	
	
	public Datastore getDataSource(){
		mongoClient = new MongoClient(propertiesConfig.getProperty("HOST_NAME"));
		Morphia morphia = new Morphia();
		morphia.map(WeightDTO.class);
		Datastore ds = morphia.createDatastore(mongoClient, propertiesConfig.getProperty("DB_NAME"));
		return ds;
	}

}
