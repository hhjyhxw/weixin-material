package com.icloud.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 */
@Configuration 
public class InterceptorStack {

 
	@Configuration
	static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
		public void addInterceptors(InterceptorRegistry registry) {
//			registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/phone/*");
		    registry.addInterceptor(new PermissionsInterceptor()).addPathPatterns("/admin/*"); 
//		    registry.addInterceptor(new AddTokenInterceptor()).addPathPatterns("/phone/preOrder")
//		    .addPathPatterns("/phone/buyNow"); 
//		    registry.addInterceptor(new RemoveTokenInterceptor()).addPathPatterns("/phone/checkToken");
		}
	}
}
