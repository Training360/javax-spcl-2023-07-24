package careerservice.gatway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollResponse {

    public enum EnrollResult {SUCCESS, FULL}

    private long employeeId;

    private long courseId;

    private EnrollResult enrollResult;

}
