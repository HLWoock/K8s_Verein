package de.woock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class VereinApp {

	
	public static void main(String[] args) {
		SpringApplication.run(VereinApp.class, args);
		log.debug("Application 'Verein'started");
	}
	
//	@Bean
//	ApplicationListener<ApplicationReadyEvent> ready() {
//		return event -> {
//			
//			WebClient wc = WebClient.builder().baseUrl("http://localhost:7654/user/12345").build();
//			HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(wc)).build();
//			GreetingsClient client = factory.createClient(GreetingsClient.class);
//			
//			System.out.println(client.greet("Krissi"));
//		};
//	}

}
