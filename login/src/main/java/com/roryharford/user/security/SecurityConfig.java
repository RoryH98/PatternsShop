package com.roryharford.user.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.roryharford.user.User;
import com.roryharford.user.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;

	@Autowired
	private DataSource datasource;

	private final String USERS_QUERY = "select email, password, active from user where email=?";
	// look back at this
	private final String ROLES_QUERY = "select u.email, r.role from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id = r.role_id) where u.email=?";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(USERS_QUERY).authoritiesByUsernameQuery(ROLES_QUERY)
				.dataSource(datasource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register", "/homepage", "/about", "/login", "/css/**", "/webjars/**").permitAll()
		.antMatchers("/Admin").hasAnyRole("USER,ADMIN")
		//.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
//		.antMatchers("/css/**").permitAll()
//		.antMatchers("/Admin").hasAuthority("ADMIN").anyRequest().authenticated()
		.and().csrf().disable()
		.formLogin().loginPage("/login").failureUrl("/login?error=true")
		.defaultSuccessUrl("/homepage")
		.usernameParameter("email")
		.passwordParameter("password");
//		.and().logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/")
//		.and().rememberMe()
//		.tokenRepository(persistentTokenRepository())
//		.tokenValiditySeconds(60 * 60)
//		.and().exceptionHandling().accessDeniedPage("/access_denied");
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(datasource);

		return db;
	}

	
}
//// start of jordos

//	--------------security config----------------
//	package org.finalyearproject.config;
//
//	import javax.sql.DataSource;
//
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.context.annotation.Bean;
//	import org.springframework.context.annotation.Configuration;
//	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//	import org.springframework.security.crypto.password.PasswordEncoder;
//
//	@Configuration
//	@EnableWebSecurity
//	public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private DataSource dataSource;
//
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.jdbcAuthentication().dataSource(dataSource)
//	.usersByUsernameQuery("select email as principal, password as credentails, true from user where email=?")
//	.authoritiesByUsernameQuery("select user_email as principal, role_name as role from user_roles where user_email=?")
//	.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");  
//
//	}
//	  
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	// TODO Auto-generated method stub
//	return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//	http.authorizeRequests().antMatchers("/register", "/", "/about", "/login", "/css/**", "/webjars/**").permitAll()
//	.antMatchers("/profile").hasAnyRole("USER,ADMIN")
//	.antMatchers("/users","/addTrip").hasAnyRole("USER,ADMIN")
//	.and().formLogin().loginPage("/login").permitAll()
//	.defaultSuccessUrl("/profile").and().logout().logoutSuccessUrl("/login");
//	}
//
//	}
//	---------------main method-----------
//	@Override
//	public void run(String... args) throws Exception {
//	// TODO Auto-generated method stub
//	 {
//	   	 User newAdmin = new User("admin@mail.com", "Admin", "123456", "headquaters");
//	     userService.createAdmin(newAdmin); 
//	   	 }
//	}
//	
//}