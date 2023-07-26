package careerservice.saga;

import careerservice.enrollment.model.EnrollCommand;
import careerservice.enrollment.service.EnrollmentService;
import careerservice.enrollment.view.EnrollmentView;
import careerservice.gatway.EnrollRequest;
import careerservice.gatway.KafkaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnrollSaga {

    private EnrollmentService enrollmentService;

    private KafkaGateway kafkaGateway;

    public EnrollmentView enroll(EnrollCommand command) {
        var enrollment = enrollmentService.enrollToCourse(command);
        var request = new EnrollRequest(command.getEmployeeId(), command.getCourseId());
        kafkaGateway.enroll(request);
        return enrollment;
    }
}
