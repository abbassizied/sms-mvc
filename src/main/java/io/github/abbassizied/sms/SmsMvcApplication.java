package io.github.abbassizied.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.github.abbassizied.sms.configs.FileProperties;
import io.github.abbassizied.sms.configs.StorageProperties;
import io.github.abbassizied.sms.services.StorageService;

@SpringBootApplication
@EnableConfigurationProperties({ StorageProperties.class, FileProperties.class })
public class SmsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsMvcApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			// storageService.deleteAll();
			storageService.init();
		};
	}
}
