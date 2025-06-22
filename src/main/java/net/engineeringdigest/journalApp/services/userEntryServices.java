package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.userEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class userEntryServices {
    @Autowired
    private userEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


//    public List<User> saveUserEntry(User myUser){
//        User user = userEntryRepository.save(myUser);
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        return users;
//    }

    public User saveUserEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("user"));
        return userEntryRepository.save(user);
    }

    public  User saveNewUserEntry(User user){
        return  userEntryRepository.save(user);

    }
    public User saveAdminEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("user", "admin"));
        return userEntryRepository.save(user);
    }



    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return userEntryRepository.findById(id);
    }

    public boolean deleteEntry(ObjectId id) {
        userEntryRepository.deleteById(id);
        return false;
    }

    public User getByUsername(String Username) {
        return userEntryRepository.findByUsername(Username);
    }

    public boolean deleteUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userEntryRepository.deleteByUsername(username);
        return true;


    }
}