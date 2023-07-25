package frontendservice.contoller;

import frontendservice.service.Course;
import frontendservice.service.CourseDetails;
import frontendservice.service.CoursesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class CoursesController {

    private CoursesService coursesService;

    @QueryMapping
    public List<Course> courses() {
        return coursesService.findAllCourses();
    }

    @SchemaMapping
    public CourseDetails courseDetails(Course course) {
        return coursesService.findById(course.getId());
    }
}
