package frontendservice.service;

import frontendservice.coursegateway.CourseDetailsView;
import frontendservice.employeegateway.EmployeeDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CourseMapperDecorator implements CourseMapper {

    @Autowired
    @Qualifier("delegate")
    private CourseMapper delegate;

    @Override
    public CompositeCourseDetailsView toComposite(CourseDetailsView courseById, List<EmployeeDto> employees) {
        var dto = delegate.toComposite(courseById, employees);
        var map = employees.stream().collect(Collectors.toMap(EmployeeDto::getId, Function.identity()));
        dto.setEnrolledEmployees(courseById.getEnrolledEmployees().stream().map(map::get).toList());
        dto.setCompletedEmployees(courseById.getCompletedEmployees().stream().map(map::get).toList());
        return dto;
    }
}
