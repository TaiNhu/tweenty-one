package com.twentyone.app;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.twentyone.app.entities.CustomOAuth2User;
import com.twentyone.app.service.UserService;
import com.twentyone.app.service.impl.CustomOAuth2UserService;
import com.twentyone.app.service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	CustomOAuth2UserService oauthUserService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImpl);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// Cac trang yeu cau quyen su dung la Admin hoac Director
		http.authorizeRequests()
		.antMatchers("/animeList/**").hasRole("USER")
		.antMatchers("/admin/**").hasRole("ADMIN").and().oauth2Login().loginPage("/login").userInfoEndpoint()
		.userService(oauthUserService).and().successHandler(new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// TODO Auto-generated method stub

				 response.sendRedirect("/index.html");
			}
		}).and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// TODO Auto-generated method stub
                response.sendRedirect("/");
			}
			
		}).permitAll();

		
		

		

		// Các trang không yêu cầu login		
		http.authorizeRequests().anyRequest().permitAll();

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/404page");

		// Cau hinh cho form login

		// Cau hinh dang xuat khoi chuong trinh
		http.logout()
		.logoutUrl("/logout");
		
		

		// Cau hinh remember me
		http.authorizeRequests().and().rememberMe().tokenValiditySeconds(86400);
	}
	
	 @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}
