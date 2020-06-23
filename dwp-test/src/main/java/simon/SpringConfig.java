package simon;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value={"simon"})
public class SpringConfig {
	
	@Bean
	public HttpClient getHttpClient(){
		return  HttpClient.newHttpClient();
	}

}
