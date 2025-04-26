package com.badev.mynote;

import com.badev.mynote.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class MynoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MynoteApplication.class, args);
	}

}
