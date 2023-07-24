package frontendservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "frontend-service")
public class GatewayProperties {

    private String employeeServiceUrl;

    private String courseServiceUrl;
}
