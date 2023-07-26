package courseservice.course.kafkagateway;

import courseservice.course.dto.EnrollCommand;
import courseservice.course.service.CourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

@Configuration
public class CourseRequestHandler {

    @Bean
    public Function<EnrollRequest, EnrollResponse> handleEnrollRequest(CourseService courseService) {
        return enrollRequest -> {
            try {
                var command = new EnrollCommand(enrollRequest.getCourseId(), List.of(enrollRequest.getEmployeeId()));
                courseService.enroll(command);
                return new EnrollResponse(enrollRequest.getEmployeeId(), enrollRequest.getCourseId(), EnrollResponse.EnrollResult.SUCCESS);
            } catch (IllegalArgumentException exception) {
                return new EnrollResponse(enrollRequest.getEmployeeId(), enrollRequest.getCourseId(), EnrollResponse.EnrollResult.FULL);
            }
        };
    }
}
