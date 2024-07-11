package nursery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NurseryBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(NurseryBotApplication.class, args);
	}

}
