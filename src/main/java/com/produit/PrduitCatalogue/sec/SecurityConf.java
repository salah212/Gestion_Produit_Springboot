package com.produit.PrduitCatalogue.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder ;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // pour spécifier ou il exist les utilisateurs est ce que existe on baseedonne ou ficher text ou en memoir
        BCryptPasswordEncoder bcpe = getbcpe();
        //System.out.println(bcpe.encode("user"));
       /*
               This is memory authen
        auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("admin")).roles("ADMIN","USER");//noop pour garder le mot de pass en clere n'est pas encoder
        auth.inMemoryAuthentication().withUser("user").password(bcpe.encode("user")).roles("USER");// encode send the password encoder
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
        */
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal , password as credentials , active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal , role as role from users_roles where username=?")
                .rolePrefix("ROLE_").passwordEncoder(getbcpe());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");//
        http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
        http.exceptionHandling().accessDeniedPage("/403");
    }
    @Bean
    BCryptPasswordEncoder getbcpe(){
        return new BCryptPasswordEncoder();
    }

}
