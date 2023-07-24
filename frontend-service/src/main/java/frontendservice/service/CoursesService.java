package frontendservice.service;

import frontendservice.coursegateway.CourseDetailsView;
import frontendservice.coursegateway.CourseView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoursesService {

    private EmployeesService employeesService;

    private CourseMapper courseMapper;

    public List<CourseView> findAllCourses() {
        return null;
    }

    public CourseDetailsView findCourseById(long id) {
        return null;
    }
}
