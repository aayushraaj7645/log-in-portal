package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.CachePojo;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface configCacheRepository extends MongoRepository <CachePojo, ObjectId>{
}
