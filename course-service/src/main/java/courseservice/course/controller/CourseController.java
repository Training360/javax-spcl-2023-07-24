package courseservice.course.controller;

import courseservice.course.dto.CourseDetailsView;
import courseservice.course.dto.AnnounceCourseCommand;
import courseservice.course.dto.CourseView;
import courseservice.course.dto.EnrollCommand;
import courseservice.course.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;

    @GetMapping
    public List<CourseView> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDetailsView findCourseById(@PathVariable("id") long id) {
        return courseService.findCourseById(id);
    }

    @PostMapping // nem idempotens
    @ResponseStatus(HttpStatus.CREATED)
    public CourseView create(@RequestBody AnnounceCourseCommand command) {
        return courseService.createCourse(command);
    }

    @PostMapping("/{courseId}/enrollments")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseView enroll(@PathVariable long courseId, @RequestBody EnrollCommand command) {
        if (courseId != command.getCourseId()) {
            throw new IllegalArgumentException("Course ids differ");
        }
        return courseService.enroll(command);
    }

}
