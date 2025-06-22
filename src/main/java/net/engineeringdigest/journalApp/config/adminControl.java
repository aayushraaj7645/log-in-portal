package net.engineeringdigest.journalApp.config;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.journalEntryRepository;
import net.engineeringdigest.journalApp.repository.userEntryRepository;
import net.engineeringdigest.journalApp.services.journalEntryServices;
import net.engineeringdigest.journalApp.services.userEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminControl {
    @Autowired
    private userEntryRepository userEntryRepository;

    @Autowired
    private journalEntryRepository journalEntryRepository;

    @Autowired
    private userEntryServices userEntryServices;

    @Autowired
    private journalEntryServices journalEntryServices;


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userEntryRepository.findByUsername(username);
        List<String> role = user.getRoles();
        if (role.contains("admin")) {
            List<User> user1 = userEntryServices.getAll();
            return new ResponseEntity<>(user1, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

       @PostMapping("/createAdmin")
       public ResponseEntity<?> createNewAdmin(@RequestBody User user){
            String username= SecurityContextHolder.getContext().getAuthentication().getName();
            User user1 = userEntryRepository.findByUsername(username);
            try {
                if (user1.getRoles().contains("admin")) {
                    userEntryServices.saveAdminEntry(user);
                    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }
            catch (RuntimeException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }




    }

}
