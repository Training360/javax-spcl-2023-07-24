package frontendservice.service;

import frontendservice.coursegateway.CourseDetailsView;
import frontendservice.coursegateway.CourseView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoursesService {

    private CourseClient courseClient;

    private EmployeesService employeesService;

    private CourseMapper courseMapper;

    public List<CourseView> findAllCourses() {
        return courseClient.findAllCourses();
    }

    public CourseDetailsView findCourseById(long id) {
        return courseClient.findCourseById(id);
    }
}
