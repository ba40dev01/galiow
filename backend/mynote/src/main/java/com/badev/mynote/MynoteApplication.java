package com.badev.mynote;

import com.badev.mynote.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication

@EntityScan("com.badev.mynote.entity")
@EnableJpaRepositories("com.badev.mynote.repository")
public class MynoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MynoteApplication.class, args);
	}

}
