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

    @Autowired              //this autowire statement helps us to bring the methods from the java class mentioned for our use
    private userEntryServices userEntryServices;

    // This POST method is used to create new users , (and this is used without authentication ).
    @PostMapping("/createUser")
    public ResponseEntity<?> createNewUser(@RequestBody User newUser){   // RequestBody is used to take the entries from the body box from the postman
        try {
            User user = userEntryServices.saveUserEntry(newUser);
            return new ResponseEntity<>(user , HttpStatus.ACCEPTED);   //ResponseEntity is used to sent the status of the operation like it is OK or NOT_DONE or  NO_CONTENT.
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("POST OPERATION WORKED  ");
        }
    }





}
