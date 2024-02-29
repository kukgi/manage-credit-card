package br.com.e2dp.mscreditvalidator;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class MsCreditValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCreditValidatorApplication.class, args);
	}

}
