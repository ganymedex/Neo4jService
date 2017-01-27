package com;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jane.neo4j.config.MyNeo4jConfiguration;


@Configuration
@EnableAutoConfiguration
@Import(MyNeo4jConfiguration.class)
@SpringBootApplication
//@ImportResource({"classpath:spring/spring-context.xml"})
public class Neo4jServiceApplication {
	
	@Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

	public static void main(String[] args) throws InterruptedException {
		//SpringApplication.run(Neo4jServiceApplication.class, args);
		
		ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(Neo4jServiceApplication.class)
                //.web(false)
                .run(args);
		
		CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
	}
}
