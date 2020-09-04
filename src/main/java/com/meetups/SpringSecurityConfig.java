package com.meetups;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.meetups.security.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
@PropertySource("classpath:application.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//	@Autowired
//	private LoginSuccessHandler successHandler;

//	@Autowired
//	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
//
//		PasswordEncoder encoder = passwordEncoder();
//		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
//
//		build.inMemoryAuthentication()
//		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
//		.withUser(users.username("ignacio").password("12345").roles("USER"));
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar", "/weather", "/login").permitAll()
//				.antMatchers("/ver/**").hasAnyRole("USER")
//				.antMatchers("/uploads/**").hasAnyRole("USER")
//				.antMatchers("/form/**").hasAnyRole("ADMIN")
//				.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
//				.antMatchers("/factura/**").hasAnyRole("ADMIN")
//				.anyRequest().authenticated().and()
//				.formLogin()
//				.successHandler(successHandler).permitAll()
//				.and()
//				.logout().permitAll()
//				.and()
//				.exceptionHandling().accessDeniedPage("/error_403");
//
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers(HttpMethod.POST, "/cliente").permitAll().anyRequest().authenticated();
//		http.headers().httpStrictTransportSecurity().disable();

	}
}
