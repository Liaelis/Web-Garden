package com.elisrs.garden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   @Autowired
    private ImplementsUserDatailsService userDetailsService;
    //ativa suporte de controle de sessão
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    //Cria a sessão quando precisar, se a aplicação criar o security cuidara dela
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        //controla numero de sessões por usuarios e garante que a sessao seja destrida
        http.sessionManagement().maximumSessions(1);
        //session fixation
        http.sessionManagement().sessionFixation().newSession();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET,SecurityConstants.HOME).permitAll()
                .mvcMatchers(HttpMethod.GET,SecurityConstants.LOGIN).permitAll()
                .mvcMatchers(HttpMethod.POST,SecurityConstants.CADASTRAR).permitAll()
                .mvcMatchers(HttpMethod.GET,SecurityConstants.CADASTRO).permitAll()
                .mvcMatchers(HttpMethod.GET,SecurityConstants.RECUPERACAO).permitAll()
                .mvcMatchers(HttpMethod.POST,SecurityConstants.REDEFINIR).permitAll()
                .mvcMatchers(HttpMethod.GET,SecurityConstants.SOBRE).permitAll()
                .mvcMatchers(HttpMethod.GET,SecurityConstants.VERIFICA).permitAll()
                .mvcMatchers(HttpMethod.POST,SecurityConstants.VERIFICA).permitAll()
                .mvcMatchers(HttpMethod.GET,SecurityConstants.TOKENRECUPERACAO).permitAll()
                .mvcMatchers(HttpMethod.POST,SecurityConstants.LOGIN).permitAll()
                .mvcMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").successForwardUrl("/testauser").failureForwardUrl("/")
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
        .and().csrf().ignoringAntMatchers("/logout");
       // http.securityContext().securityContextRepository(new NullSecurityContextRepository());


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }



}
