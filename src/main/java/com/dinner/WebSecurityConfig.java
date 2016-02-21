package com.dinner;


import com.dinner.filter.StatelessAuthenticationFilter;
import com.dinner.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private StatelessAuthenticationFilter statelessAuthenticationFilter;

    /*@Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, TokenAuthenticationService tokenAuthenticationService) {
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable()
                .authorizeRequests()
                    .antMatchers("/", "/login", "/home", "/auth").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .addFilterBefore(statelessAuthenticationFilter,
                     UsernamePasswordAuthenticationFilter.class);
                /*.csrf()
                    .csrfTokenRepository(csrfTokenRepository()).and()
                    .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/app/**","/node_modules/**","/resources/**","/resources/static/**","/static/**","/templates/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }
}
