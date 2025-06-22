package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.userEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServicesImplements implements UserDetailsService {



    @Autowired
    private userEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     User user = userEntryRepository.findByUsername(username);
     if(user != null){
        UserDetails userDetail =  org.springframework.security.core.userdetails.User.builder()
                 .username(user.getUsername())
                 .password(user.getPassword())
                 .roles(user.getRoles().toArray(new String[0]))
                 .build();
         return userDetail;
     }
     else {
         throw new UsernameNotFoundException("user not found with this username " + username);

     }

    }

    @Autowired
    public void setUserEntryRepository(userEntryRepository userEntryRepository) {
        this.userEntryRepository = userEntryRepository;
    }
}
