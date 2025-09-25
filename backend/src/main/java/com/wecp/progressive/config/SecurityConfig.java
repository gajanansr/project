package com.wecp.progressive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wecp.progressive.jwt.JwtRequestFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService , JwtRequestFilter jwtRequestFilter , PasswordEncoder passwordEncoder)
    {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/register" , "/user/login").permitAll()
                .antMatchers(HttpMethod.GET , "/ipl/team/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/ipl/team/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT , "/ipl/team/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE , "/ipl/team/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET , "/ipl/cricketer/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/ipl/cricketer/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT , "/ipl/cricketer/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE , "/ipl/cricketer/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET , "/ipl/match/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/ipl/match/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT , "/ipl/match/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE , "/ipl/match/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET , "/ipl/ticket/**").hasAnyAuthority("USER" , "ADMIN")
                .antMatchers(HttpMethod.POST , "/ipl/ticket/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE , "/ipl/ticket/**").hasAuthority("USER")
                .antMatchers(HttpMethod.GET , "/ipl/vote/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/ipl/vote/**").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                http.addFilterBefore(jwtRequestFilter , UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}