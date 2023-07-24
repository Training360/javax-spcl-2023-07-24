package frontendservice.service;


import frontendservice.employeegateway.EmployeeDto;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface EmployeeClient {

    @GetExchange("/api/employees")
    List<EmployeeDto> employees();
}
