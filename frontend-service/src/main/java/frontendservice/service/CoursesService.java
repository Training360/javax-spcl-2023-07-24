package frontendservice.service;

import frontendservice.coursegateway.Course;
import frontendservice.coursegateway.CourseClient;
import frontendservice.coursegateway.CourseDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoursesService {

    private CourseClient courseClient;

    private EmployeesService employeesService;

    private CourseMapper courseMapper;

    public List<Course> findAllCourses() {
        return courseClient.findAllCourses();
    }

    public CourseDetailsDto findCourseById(long id) {
    }
}
