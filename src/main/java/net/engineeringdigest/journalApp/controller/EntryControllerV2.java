package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.entity;
import net.engineeringdigest.journalApp.repository.userEntryRepository;
import net.engineeringdigest.journalApp.services.journalEntryServices;
import net.engineeringdigest.journalApp.services.userEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class EntryControllerV2 {

    @Autowired
    private journalEntryServices journalEntryServices;
    @Autowired
    private userEntryServices userEntryServices;
    @Autowired
    private userEntryRepository userEntryRepository;

//    @GetMapping
//    public List<entity> getAllEntries() {
//        return journalEntryServices.getAll();
//    }

//    @GetMapping
//    public List<entity> getUsernameEntries() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        User user = userEntryServices.getByUsername(username);
//        return user.getEntities();
//    }


    @PostMapping
    public void postJournalEntry(@RequestBody entity journalEntry) {
        try{
            String username= SecurityContextHolder.getContext().getAuthentication().getName();
        journalEntryServices.saveEntry(journalEntry,username);
           }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }


    }
//    @GetMapping("id/{myId}")
//    public ResponseEntity<?> getSpecificId(@PathVariable ObjectId myId){
//       String username = SecurityContextHolder.getContext().getAuthentication().getName();
//       User user = userEntryServices.getByUsername(username);
//       List <entity> entities = user.getEntities().stream().filter(e -> e.getId().equals(myId)).collect(Collectors.toList());
//       if(!entities.isEmpty()){
//           return new ResponseEntity<>(entities.get(0),HttpStatus.OK);
//       }
//    else{
//    return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
//
//       }}

@Transactional
    @DeleteMapping("id/{myId}")
        public ResponseEntity<?> deleteBySpecificId(@PathVariable ObjectId myId){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userEntryServices.getByUsername(username);
            List <entity> entities = user.getEntities().stream().filter(e -> e.getId().equals(myId)).collect(Collectors.toList());
            if(!entities.isEmpty()){
           journalEntryServices.deleteJournalEntryByIdAndUsername(myId,username);
            //  userEntryServices.saveNewUserEntry(user);
//              user.setEntities(entities);
              return new ResponseEntity<>("deleted",HttpStatus.OK);
            }
            else{


    }









    //@Transactional
//    @PostMapping("{username}")
//    public boolean postJournalEntry(@RequestBody entity journalEntry, @PathVariable String username) {
//        entity Entity = journalEntryServices.saveEntry(journalEntry, username);
//        User user = userEntryServices.getByUsername(username);
//        user.getEntities().add(Entity);
//        userEntryServices.saveUserEntry(user);
//        return true;
//
//    }


//  @GetMapping("/id/{Myid}")
//    public ResponseEntity<entity> getSpecificId(@PathVariable ObjectId Myid)
//  { Optional <entity> entity = journalEntryServices.getById(Myid);
//      if(entity.isPresent())
//      {
//          return new ResponseEntity<>(entity.get(),HttpStatus.OK);
//      }
//      else
//      {
//          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//      }
//
//
//  }
//
//    @DeleteMapping("/{username}/{Myid}")
//    public ResponseEntity<?> deleteSpecificId(@PathVariable ObjectId Myid, @PathVariable String username) {
//        if (journalEntryServices.deleteEntry(Myid, username)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @PutMapping("/{username}/{Myid}")
//    public ResponseEntity<?> updateSpecificId(@RequestBody entity journalEntry, @PathVariable String username, @PathVariable ObjectId Myid) {
//        User user = userEntryServices.getByUsername(username);
//
//        if (journalEntryServices.getById(Myid).isPresent())
//        {
//              journalEntry.setId(Myid);
//            journalEntryServices.saveEntry(journalEntry, username);
//            user.getEntities().add(journalEntry);
//          //  user.getEntities().removeIf(e -> e.getId().equals(Myid));
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }






    }
