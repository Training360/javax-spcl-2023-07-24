package frontendservice.service;

import ch.qos.logback.core.model.ComponentModel;
import frontendservice.coursegateway.CourseDetailsView;
import frontendservice.employeegateway.EmployeeDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
@DecoratedWith(CourseMapperDecorator.class)
public interface CourseMapper {


    @Mapping(target = "enrolledEmployees", ignore = true)
    @Mapping(target = "completedEmployees", ignore = true)
    CompositeCourseDetailsView toComposite(CourseDetailsView courseById, List<EmployeeDto> employees);
}
