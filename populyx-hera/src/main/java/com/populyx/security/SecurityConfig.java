package com.populyx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.populyx.cerbero.service.MyUserService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.populyx*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserService userService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
		// auth
		// .inMemoryAuthentication()
		// .withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").access("hasRole('ROLE_USER')").and().formLogin().loginPage("/login")
				.failureUrl("/login?error").usernameParameter("username").passwordParameter("password").and()
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		// http.authorizeRequests().antMatchers("/**").access("hasRole('ROLE_USER')").and().formLogin()
		// .loginPage("/login").failureUrl("/login?error").usernameParameter("username")
		// .passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout").and()
		// .exceptionHandling().accessDeniedPage("/403").and().csrf();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
