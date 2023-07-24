package courseservice.course.model;

import courseservice.course.dto.AnnounceCourseCommand;
import courseservice.course.dto.EnrollCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    @Lob
    private String syllabus;

    @Column(name = "attendee_limit")
    private int limit;

    @ElementCollection
    List<Long> enrolledEmployees;

    @ElementCollection
    List<Long> completedEmployees;

    private Course(String name, String description, String syllabus, int limit) {
        this.name = name;
        this.description = description;
        this.syllabus = syllabus;
        this.limit = limit;
    }


    public static Course announceCourse(AnnounceCourseCommand command) {
        if (command.getLimit() <= 0) {
            throw new IllegalArgumentException("Limit must be positive");
        }
        return new Course(command.getName(), command.getDescription(), command.getSyllabus(), command.getLimit());
    }

    public void enroll(EnrollCommand command) {
        List<Long> employeeIds = command.getEmployeeIds();

        if (limit < employeeIds.size() + enrolledEmployees.size()) {
            throw new IllegalArgumentException("Limit exceeded");
        }
        List<Long> enrollingEmployeeIds = employeeIds.stream()
                .filter(employeeId -> !this.enrolledEmployees.contains(employeeId))
                .toList();
        this.enrolledEmployees.addAll(enrollingEmployeeIds);
    }

}
