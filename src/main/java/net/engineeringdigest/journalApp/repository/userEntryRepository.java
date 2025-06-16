package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.entity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userEntryRepository extends MongoRepository <User, ObjectId>{
    User findByUsername(String username);

    void deleteByUsername(String username);
}
