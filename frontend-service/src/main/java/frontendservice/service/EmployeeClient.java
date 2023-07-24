package frontendservice.service;


import frontendservice.employeegateway.CreateEmployeeRequest;
import frontendservice.employeegateway.EmployeeDto;
import frontendservice.employeegateway.RoleDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface EmployeeClient {

    @GetExchange("/api/employees")
    List<EmployeeDto> employees();

    @GetExchange("/api/roles")
    List<RoleDto> roles();

    @PostExchange("/api/employees")
    EmployeeDto createEmployee(@RequestBody CreateEmployeeRequest request);

}
