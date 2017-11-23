package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	//userのログイン処理
	@Configuration
	@Order(1)
	public static class LoginUserConfiguration extends WebSecurityConfigurerAdapter{
		@Autowired
		private PasswordEncoder passwordEncoder;
		@Autowired
		@Qualifier("user")
		private UserDetailsService loginUserDetailsService;
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth
				.userDetailsService(loginUserDetailsService)
				.passwordEncoder(passwordEncoder);
		}
		
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/webjars/**","/css/**");
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/loginForm","/registUserForm","/create").permitAll()
				.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.formLogin()
			.loginProcessingUrl("/login") //loginの処理を受けるパス
				.loginPage("/loginForm") //loginフォームのパス
				.failureUrl("/loginForm?error")
				.defaultSuccessUrl("/top",true)
				.usernameParameter("mail").passwordParameter("password")
			.and()
			.logout()
				.logoutSuccessUrl("/loginForm");
		}
	}
	//販売者側のログイン処理
	@Configuration
	@Order(2)
	public static class SellerUserConfiguration extends WebSecurityConfigurerAdapter{
		@Autowired
		private PasswordEncoder passwordEncoder;
		@Autowired
		@Qualifier("seller")
		private UserDetailsService sellerUserDetailsService;
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth
				.userDetailsService(sellerUserDetailsService)
				.passwordEncoder(passwordEncoder);
		}
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/webjars/**","/css/**");
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.authorizeRequests()
				.antMatchers("").permitAll();
		}
	}
	
	//パスワードのハッシュ化
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}