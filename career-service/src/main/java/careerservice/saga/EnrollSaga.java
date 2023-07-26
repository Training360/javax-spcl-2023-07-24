package careerservice.saga;

import careerservice.enrollment.model.EnrollCommand;
import careerservice.enrollment.service.EnrollmentService;
import careerservice.enrollment.view.EnrollmentView;
import careerservice.gatway.EnrollRequest;
import careerservice.gatway.EnrollResponse;
import careerservice.gatway.KafkaGateway;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
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

    @EventListener
    public void handleEnrollResponse(EnrollResponse response) {
        if (response.getEnrollResult() == EnrollResponse.EnrollResult.SUCCESS) {
            enrollmentService.enrolled(response.getCourseId(), response.getEmployeeId());
        }
        else {
            enrollmentService.cancel(response.getCourseId(), response.getEmployeeId());
        }
    }
}
