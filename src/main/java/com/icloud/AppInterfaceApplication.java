package com.icloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
//@EnableAutoConfiguration  
//@ComponentScan   //这两个注解可以使用SpringBootApplication替代  
@MapperScan("com.icloud.dao.*")/** 扫描mybatis mapper接口 */			
@PropertySource({"classpath:config.properties","classpath:jdbc.properties"})
@EnableTransactionManagement/**启用注解事务管理**/
public class AppInterfaceApplication extends SpringBootServletInitializer{	
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		  
	        return application.sources(AppInterfaceApplication.class);
	    }
	public static void main(String[] args) {
		SpringApplication.run(AppInterfaceApplication.class, args);
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
	       return new EmbeddedServletContainerCustomizer() {
				@Override
				public void customize(
						ConfigurableEmbeddedServletContainer container) {
					 container.setSessionTimeout(7200);//单位为S //客户要求2小时
				}
	    };
	    
	}
	
}
