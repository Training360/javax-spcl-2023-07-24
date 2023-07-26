package courseservice.course.kafkagateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollRequest {

    long employeeId;

    long courseId;
}
