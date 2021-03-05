package br.com.douglasffilho.orderstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OrderStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderStatusApplication.class, args);
	}

}
