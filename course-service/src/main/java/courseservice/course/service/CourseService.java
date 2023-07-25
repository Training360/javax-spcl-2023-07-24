package courseservice.course.service;

import courseservice.course.dto.CourseDetailsView;
import courseservice.course.dto.CourseView;
import courseservice.course.dto.AnnounceCourseCommand;
import courseservice.course.dto.EnrollCommand;
import courseservice.course.model.Course;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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

    private ApplicationEventPublisher applicationEventPublisher;

    public CourseView createCourse(AnnounceCourseCommand command) {
        var course = Course.announceCourse(command);
        courseRepository.save(course);

        var event = new CourseHasCreatedEvent(course.getId(), course.getName(), course.getDescription(), course.getSyllabus());
        applicationEventPublisher.publishEvent(event);

        return courseMapper.toView(course);
    }

    @Transactional(readOnly = true)
    public CourseDetailsView findCourseById(long id) {
        var course = courseRepository.findById(id)
                .orElseThrow();
        return courseMapper.toDetailsView(course);

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
