package training.mentoringgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dummy-employees")
public class EmployeeController {

    // Project Reactor
    // Mono - Optional
    // Flux - Stream
    @GetMapping
    public Flux<EmployeeDto> dummyEmployees() {
        return Flux.just(
                new EmployeeDto(1L, "Dummy Employee 1", new RoleDto(1L, "Dummy Role")),
                new EmployeeDto(2L, "Dummy Employee 2", new RoleDto(1L, "Dummy Role")),
                new EmployeeDto(3L, "Dummy Employee 3", new RoleDto(1L, "Dummy Role"))
        );
    }
}
