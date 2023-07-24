package courseservice.course.service;

import courseservice.course.dto.CourseDetailsView;
import courseservice.course.dto.CourseView;
import courseservice.course.dto.AnnounceCourseCommand;
import courseservice.course.dto.EnrollCommand;
import courseservice.course.model.Course;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    public CourseView createCourse(AnnounceCourseCommand command) {
        var course = Course.announceCourse(command);
        courseRepository.save(course);
        return courseMapper.toView(course);
    }

    public CourseDetailsView findCourseById(long id) {
        return null;
    }

    public List<CourseView> findAllCourses() {
        return courseRepository.findAllView();
    }

    @Transactional
    public CourseView enroll(EnrollCommand command) {
        // Clean architecture: üzleti logika az entitásba
        // 1. ha már jelentkezett ne jelentkezzen még egyszer
        // 2. limitnél nem jelentkezhet több alkalmazott

        var course = courseRepository.findById(command.getCourseId())
                .orElseThrow();

        course.enroll(command);
        return courseMapper.toView(course);
    }
}
