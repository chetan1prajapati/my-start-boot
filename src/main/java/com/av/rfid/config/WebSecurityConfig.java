package com.av.rfid.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.av.rfid.config.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	SpringDataUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/home","/forgotPassword").permitAll().anyRequest().authenticated().and()
			.formLogin().loginPage("/login").permitAll()
			.defaultSuccessUrl("/hello").successHandler(new LoginSuccessHandler()).and()
			.logout().permitAll().logoutSuccessUrl("/login?logout")
			.and().rememberMe()
			.rememberMeParameter("rememberme")
			.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(172800)
	        .and().csrf()
	        .and().exceptionHandling().accessDeniedPage("/Access_Denied")
			;
		 	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
				.password("s3cr3t4AV!").roles("ADMIN");
		auth.authenticationProvider(authProvider());
		// auth.userDetailsService(userDetailsService); //don't need auth
		// provider or encryption
		// jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
		// .usersByUsernameQuery("SELECT username, password, enabled FROM
		// appuser where username = ?")
		// .authoritiesByUsernameQuery("select username,role as authority from
		// user_role where username = ?");
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

	//
	// @Bean
	// public SavedRequestAwareAuthenticationSuccessHandler
	// savedRequestAwareAuthenticationSuccessHandler() {
	//
	// SavedRequestAwareAuthenticationSuccessHandler auth = new
	// SavedRequestAwareAuthenticationSuccessHandler();
	// auth.setTargetUrlParameter("targetUrl");
	// return auth;
	// }
	//
	@Bean
	public SpringDataUserDetailsService springDataUserDetailsService() {
		return new SpringDataUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetailsService userDetailsServiceBean() {
		return userDetailsService;
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public MailSender mailSender(){
		return new JavaMailSenderImpl();
	}

}
