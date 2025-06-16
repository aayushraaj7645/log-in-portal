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
    @Autowired
    private UserDetailServicesImplements userDetailServicesImplements;
 @Override
    protected void configure(HttpSecurity http) throws Exception{
          http.authorizeRequests().antMatchers("/journal/**" , "/user/**").authenticated()
                  .anyRequest().permitAll()
                          .and().
                    httpBasic();
          http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
}
@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailServicesImplements).passwordEncoder(passwordEncoder());
}


@Bean
    public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
}
}
