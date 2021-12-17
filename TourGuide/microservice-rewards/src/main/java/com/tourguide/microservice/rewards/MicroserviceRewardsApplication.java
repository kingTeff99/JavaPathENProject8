package com.tourguide.microservice.rewards;


@SpringBootApplication(scanBasePackages = {"com.tourguide.library.beans", "com.tourguide.microservice.rewards"})
@EnableFeignClients(basePackages = {"com.tourguide.feign_clients"})
public class MicroserviceRewardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRewardsApplication.class, args);
	}

}
