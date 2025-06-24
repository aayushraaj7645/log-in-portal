package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.userEntryRepository;
import net.engineeringdigest.journalApp.services.WeatherService;
import net.engineeringdigest.journalApp.services.journalEntryServices;
import net.engineeringdigest.journalApp.services.userEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userEntryControllerV2 {

    @Autowired
    private userEntryServices userEntryServices;
    @Autowired
    private userEntryRepository userEntryRepository;
    @Autowired
    private journalEntryServices journalEntryServices;
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> greeting(){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      return  new ResponseEntity<>("hi "+ authentication.getName() +weatherService.getWeather(
              "Mumbai").getCurrent().getFeelslike() + weatherService.getWeather("mumbai").getCurrent().getTemperature(),
              HttpStatus.OK);


    }










    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
             User user =   userEntryServices.getByUsername(username);
             if (user != null){
                 return new ResponseEntity<>(user,HttpStatus.OK);
             }
             else{
                 return new ResponseEntity<>("DIDN'T GET THE DATA", HttpStatus.NOT_FOUND);
             }
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createNewUser(@RequestBody User newUser){
        try{
        userEntryServices.saveUserEntry(newUser);
        return new ResponseEntity<>(newUser,HttpStatus.OK);}
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping()
    public ResponseEntity<?> modification(@RequestBody User user){
       Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user1 = userEntryServices.getByUsername(username);
         if (user1 != null) {
             user1.setUsername(user.getUsername());
             user1.setPassword(user.getPassword());
             user1.setRoles(user.getRoles());
             userEntryServices.saveUserEntry(user1);
             return new ResponseEntity<>("success" , HttpStatus.OK);
         }
         else {
             return new ResponseEntity<>("user not found" , HttpStatus.NOT_FOUND);
         }
    }



    }
