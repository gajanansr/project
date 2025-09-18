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
                .antMatchers(HttpMethod.GET , "/team/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/team/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT , "/team/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE , "/team/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET , "/cricketer/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/cricketer/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT , "/cricketer/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE , "/cricketer/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET , "/match/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/match/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT , "/match/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE , "/match/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET , "/ticket/**").hasAnyAuthority("USER" , "ADMIN")
                .antMatchers(HttpMethod.POST , "/ticket/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE , "/ticket/**").hasAuthority("USER")
                .antMatchers(HttpMethod.GET , "/vote/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST , "/vote/**").hasAuthority("USER")
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