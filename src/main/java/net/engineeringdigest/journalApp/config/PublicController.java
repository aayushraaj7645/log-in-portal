package net.engineeringdigest.journalApp.config;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.userEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private userEntryServices userEntryServices;

    @PostMapping("/createUser")
    public ResponseEntity<?> createNewUser(@RequestBody User newUser){
        try {
            User user = userEntryServices.saveUserEntry(newUser);
            return new ResponseEntity<>(user , HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("POST OPERATION WORKED  ");
        }
    }





}
