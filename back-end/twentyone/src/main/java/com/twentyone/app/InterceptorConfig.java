package com.twentyone.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
					ModelAndView model) throws Exception {
				
			}
		}).addPathPatterns("/**").excludePathPatterns("/rest/**", "/admin/**", "/assets/**");
	}

}
