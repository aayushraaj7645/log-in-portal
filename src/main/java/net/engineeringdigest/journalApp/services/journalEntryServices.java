package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.entity;
import net.engineeringdigest.journalApp.repository.journalEntryRepository;
import net.engineeringdigest.journalApp.repository.userEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class journalEntryServices {
    @Autowired
    private journalEntryRepository journalEntryRepository;
    @Autowired
    private userEntryServices userEntryServices;
    @Autowired
    private userEntryRepository userEntryRepository;

@Transactional
    public void saveEntry(entity journalEntry, String username){
       User user = userEntryServices.getByUsername(username);
       entity entity =  journalEntryRepository.save(journalEntry);
        user.getEntities().add(entity);
       userEntryRepository.save(user);
       //return journalEntry;



   }


    public List<entity> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<entity> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }



    @Transactional
    public boolean deleteJournalEntryByIdAndUsername(ObjectId id, String username){
       User user = userEntryServices.getByUsername(username);
        boolean removed = user.getEntities().removeIf(e -> e.getId().equals(id));
 if (removed) {
     userEntryRepository.save(user);
        journalEntryRepository.deleteById(id);
        return true;}
 else {
     return false;
 }
    }

}
