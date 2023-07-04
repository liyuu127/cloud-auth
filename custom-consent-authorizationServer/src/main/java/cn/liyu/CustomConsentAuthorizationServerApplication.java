package cn.liyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CustomConsentAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomConsentAuthorizationServerApplication.class, args);
	}

}
