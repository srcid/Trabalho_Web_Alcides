package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(HttpMethod.GET, "/cliente/formulario").permitAll()
			.antMatchers(HttpMethod.POST, "/cliente/formulario").permitAll()
			.antMatchers(HttpMethod.GET, "/cliente/cadastro").permitAll()
			.antMatchers(HttpMethod.POST, "/cliente/cadastro").permitAll()
			.antMatchers(HttpMethod.GET, "/cliente/listar").permitAll()
			.antMatchers(HttpMethod.POST, "/cliente/listar").permitAll()
			.antMatchers(HttpMethod.GET, "/prato/listar").permitAll()
			.antMatchers(HttpMethod.POST, "/prato/listar").permitAll()
			.antMatchers(HttpMethod.GET, "/prato/formulario").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/prato/formulario").hasRole("ADMIN")
			.anyRequest().authenticated()
			
			.and()
            .formLogin()
            .loginPage("/logar")
            .permitAll()
            .defaultSuccessUrl("/")

            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutUrl("/logout")
            .logoutSuccessUrl("/logar")
            .permitAll();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/js/**", "/img/**", "/images/**");
    }
}
