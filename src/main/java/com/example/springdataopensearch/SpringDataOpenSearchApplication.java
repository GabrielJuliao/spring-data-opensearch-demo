package com.example.springdataopensearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class SpringDataOpenSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataOpenSearchApplication.class, args);
	}

}
