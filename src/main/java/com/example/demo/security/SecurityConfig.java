package com.example.demo.security;


import com.example.demo.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        // Disable session
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Disable cors and csrf
        security.csrf().disable()
                .cors().disable();

        security.authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/hello*").permitAll()
                .antMatchers("/hello/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/hello*").permitAll()
                .antMatchers(HttpMethod.POST, "/secured").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return this.encoder;
    }
}
