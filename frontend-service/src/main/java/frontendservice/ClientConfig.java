package frontendservice;

import frontendservice.GatewayProperties;
import frontendservice.service.CourseClient;
import frontendservice.service.EmployeeClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(GatewayProperties.class)
public class ClientConfig {

    @Bean
    public EmployeeClient employeeClient(WebClient.Builder builder, GatewayProperties gatewayProperties) {
        var client = builder
                .baseUrl(gatewayProperties.getEmployeeServiceUrl())
                .build();
        var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(EmployeeClient.class);
    }

    @Bean
    public CourseClient courseClient(WebClient.Builder builder, GatewayProperties gatewayProperties) {
        var client = builder
                .baseUrl(gatewayProperties.getCourseServiceUrl())
                .build();
        var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(CourseClient.class);
    }
}
