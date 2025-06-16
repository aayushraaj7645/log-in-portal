package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.entity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalEntryRepository extends MongoRepository <entity, ObjectId>{


}
