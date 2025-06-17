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
    private journalEntryServices journalEntryServices;         // autowired iS defind in PublicControlller
    @Autowired
    private userEntryServices userEntryServices;
    @Autowired
    private userEntryRepository userEntryRepository;


    // GET METHOD TO GET ALL THE ENTITY PRESENT IN THAT PARTICULAR USER, WHOSE USERNAME AND PASSWORD YOU WILL PROVIDE IN BASIC AUTH SECTION
    // IN THE POSTMAN6
    @Transactional
    //  "TRANSACTIONAL"  , THIS IS TO MAKE THE WHOLE METHOD OR OPERATION AS ATOMIC
    @GetMapping
    //THIS METHOD IS USED TO GET ALL THE INPUT RELATED TO THE CERTAIN USER, USERNAME AND PASSWORD YOU WILL PUT IN BASIC AUTH SECTION
    public List<entity> getUsernameEntries()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userEntryServices.getByUsername(username);
        return user.getEntities();
    }

    // THIS METHOD IS TO INPUT THE DATA RELATED TO THE USER, HERE IN THIS CASE IT IS "TITLE" AND "CONTENT".
    @Transactional
    @PostMapping                 // YOU HAVE TO PUT USERNAME AND PASSWORD OF THAT CERTAIN USER
    public void postJournalEntry(@RequestBody entity journalEntry) {
        try
        {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            journalEntryServices.saveEntry(journalEntry, username);
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }
    }

    //THIS METHOD IS USED TO DELETE THE USER DETAIL RELATED TO SOME SPECIFIC ID, ID WILL BE ALLOTTED AUTOMATICALLY WHEN YOU USE "SAVE" OPTION TO SAVE THE INPUT FROM POST OPERATION
    @Transactional
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteBySpecificId(@PathVariable ObjectId myId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userEntryServices.getByUsername(username);
        journalEntryServices.deleteJournalEntryByIdAndUsername(myId, username);

        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @Transactional
    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateEntityById(@RequestBody entity journalEntry, @PathVariable ObjectId myId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userEntryServices.getByUsername(username);
        List<entity> entities = user.getEntities().stream().filter(e -> e.getId().equals(myId)).collect(Collectors.toList());
        if (!entities.isEmpty())
        {
            Optional<entity> entity = journalEntryServices.getById(myId);
            if (entity.isPresent())
            {
                entity old = entity.get();
                old.setTitle(journalEntry.getTitle());
                old.setContent(journalEntry.getContent());
                journalEntryServices.saveEntry(old, username);
                return new ResponseEntity<>("updated" + old, HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("NOT FOUND", HttpStatus.NO_CONTENT);
    }





    }
