package frontendservice.service;

import frontendservice.coursegateway.CourseDetailsView;
import frontendservice.coursegateway.CourseView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface CourseClient {

    @GetExchange("/api/courses")
    List<CourseView> findAllCourses();

    @GetExchange("/api/courses/{id}")
    CourseDetailsView findCourseById(@PathVariable("id") long id);
}
