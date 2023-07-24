package courseservice.course.model;

import courseservice.course.dto.AnnounceCourseCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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

}
