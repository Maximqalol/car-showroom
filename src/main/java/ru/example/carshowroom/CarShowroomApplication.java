package ru.example.carshowroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarShowroomApplication {
	static final Logger logger = LoggerFactory.getLogger(CarShowroomApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarShowroomApplication.class, args);
		logger.debug("Starting CarShowroomApplication in debug with {} arguments.", args.length);
		logger.info("Starting CarShowroomApplication with {} arguments.", args.length);
	}

}
