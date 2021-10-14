package com.dbsun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com/dbsun/**/mapper")// mybatis 包路径 在启动的时候加载所有的mapper
@ServletComponentScan
public class DbSunApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DbSunApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DbSunApplication.class);
    }
	
}
