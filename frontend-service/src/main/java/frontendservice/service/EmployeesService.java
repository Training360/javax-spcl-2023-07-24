package frontendservice.service;

import frontendservice.employeegateway.CreateEmployeeRequest;
import frontendservice.employeegateway.EmployeeDto;
import frontendservice.employeegateway.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeeClient employeeClient;

    public List<EmployeeDto> listEmployees() {
        return employeeClient.employees();
    }

    public List<RoleDto> listRoles() {
        return employeeClient.roles();
    }

    public void createEmployee(CreateEmployeeRequest request) {
        employeeClient.createEmployee(request);
    }

}
