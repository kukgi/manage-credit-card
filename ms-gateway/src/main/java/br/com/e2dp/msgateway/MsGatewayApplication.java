package br.com.e2dp.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsGatewayApplication.class, args);
    }


    /**
     * Faz o reteamento
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                    .route(r -> r.path("/credit-service/api/v1/client/**").uri("lb://ms-client"))
                    .route(r -> r.path("/credit-service/api/v1/card/**").uri("lb://ms-cards"))
                    .route(r -> r.path("/credit-service/api/v1/credit-validator/**").uri("lb://ms-creditcard-validator"))
                .build();
    }
}
