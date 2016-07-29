package com.cafeManager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Martha on 7/29/2016.
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
public class SecurityConfigrations extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/", "/test").permitAll()
                .antMatchers("/waiter").hasRole("WAITER")
                .antMatchers("/manager").hasRole("MANAGER")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).invalidSessionUrl("/")
                .and().rememberMe().tokenRepository(tokenRepository()).tokenValiditySeconds(3000)
                .and().formLogin().loginPage("/login").successHandler(new SecuritySuccessHandler())
                .usernameParameter("username").passwordParameter("password")
                .and().exceptionHandling()
                .and().csrf();
    }

    private PersistentTokenRepository tokenRepository(){
        return null;
    }
}
