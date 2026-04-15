package com.fairshare;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@Log4j2
@EntityScan("com.fairshare.model")
@SpringBootApplication
public class FairshareBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairshareBackendApplication.class, args);
	}

}
