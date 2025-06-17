package net.engineeringdigest.journalApp.config;

import net.engineeringdigest.journalApp.services.UserDetailServicesImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired       // uded is defined in publicController
    private UserDetailServicesImplements userDetailServicesImplements;
 @Override
    protected void configure(HttpSecurity http) throws Exception{
          http.authorizeRequests().antMatchers("/journal/**" , "/user/**").authenticated()    // these are used to tag which of them will require authentication for access
                  .anyRequest().permitAll()                                                       // in this case  (journal and user ) need authentication rest don't
                          .and().
                    httpBasic();
          http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();       // something related to stateless and csrf , need to study further as they are important
}
@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailServicesImplements).passwordEncoder(passwordEncoder());    // to encode the password which we will provide with the username
}


@Bean
    public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
}
}
