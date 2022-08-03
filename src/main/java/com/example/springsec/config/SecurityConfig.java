package com.example.springsec.config;

import com.example.springsec.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final JwtTokenProvider jwtTokenProvider;

	public SecurityConfig(@Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenProvider = new JwtTokenProvider(this.userDetailsService);
	}

//	@Override   //FormLogin
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		  .csrf().disable()
//		  .authorizeRequests()
//		  .antMatchers("/").permitAll()
//		  .anyRequest()
//		  .authenticated()
//		  .and()
//		  .formLogin()
//		  .loginPage("/auth/login").permitAll()
//		  .defaultSuccessUrl("/auth/success")
//		  .and()
//		  .logout()
//		  .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
//		  .invalidateHttpSession(true)
//		  .clearAuthentication(true)
//		  .deleteCookies("JSESSIONID")
//		  .logoutSuccessUrl("/auth/login");
//	}

	@Override   //REST
	protected void configure(HttpSecurity http) throws Exception {
		http
		  .csrf().disable()
		  .authorizeRequests()
		  .antMatchers("/").permitAll()
		  .anyRequest()
		  .authenticated()
		  .and()
		  .httpBasic();
	}

//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		return new InMemoryUserDetailsManager(
//		  User.builder()
//			.username("admin")
//			.password(passwordEncoder().encode("admin"))
//			.authorities(Role.ADMIN.getAuthorities())
//			.build(),
//		  User.builder()
//			.username("user")
//			.password(passwordEncoder().encode("user"))
//			.authorities(Role.USER.getAuthorities())
//			.build()
//		);
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
}
