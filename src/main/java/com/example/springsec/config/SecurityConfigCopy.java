package com.example.springsec.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigCopy extends WebSecurityConfigurerAdapter {

//	private final UserDetailsService userDetailsService;
//
//	@Autowired
//	public SecurityConfigCopy(@Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		  .csrf().disable()
//		  .authorizeRequests()
//		  .antMatchers("/").permitAll()
////		  .antMatchers("/get").permitAll() #
////		  .antMatchers("/post").permitAll() #
//		  .anyRequest()
//		  .authenticated()
//		  .and()
////                .httpBasic()
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
//
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
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(daoAuthenticationProvider());
//	}
//
//	@Bean
//	protected PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}
//
//	@Bean
//	protected DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//		return daoAuthenticationProvider;
//	}
}
