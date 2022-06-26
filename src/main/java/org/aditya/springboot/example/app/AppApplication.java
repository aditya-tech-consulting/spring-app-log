package org.aditya.springboot.example.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {
	private static final Logger logger = LoggerFactory.getLogger("AppApplication.class");
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(BusinessRepository repository){
		return args -> {
			Business business = new Business("Chroma Stores","India","888888888");
			business = repository.save(business);
			logger.info("Business "+business+" is saved");
			business = new Business("More Megastores","India","77777777");
			business = repository.save(business);
			logger.info("Business "+business+" is saved");
		};
	}

}
