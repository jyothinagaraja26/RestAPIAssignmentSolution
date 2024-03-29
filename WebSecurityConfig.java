package com.greatlearning.employee.security;

import com.greatlearning.employee.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService getMyUserDetailsService(){
        return new UserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder getPassWordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(getMyUserDetailsService()).passwordEncoder(getPassWordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http.authorizeRequests()

              .antMatchers(HttpMethod.POST, "/user", "/role").hasAuthority("ADMIN")
               .antMatchers(HttpMethod.GET, "/employeesList/*").hasAnyAuthority("USER","ADMIN")
               .antMatchers(HttpMethod.POST, "/employees/add/*").hasAuthority("ADMIN")
               .antMatchers(HttpMethod.PUT, "/employees/update/*").hasAuthority("ADMIN")
               .antMatchers(HttpMethod.DELETE, "/employees/delete/*").hasAuthority("ADMIN")
               .antMatchers(HttpMethod.GET, "/employees/search/*").hasAuthority("ADMIN")
               .anyRequest().authenticated().and().httpBasic()
               .and().cors().and().csrf().disable();
    }
}

