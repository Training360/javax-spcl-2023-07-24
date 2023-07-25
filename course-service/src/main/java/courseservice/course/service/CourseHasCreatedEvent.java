package courseservice.course.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseHasCreatedEvent {

    private Long id;

    private String name;

    private String description;

    private String syllabus;
}
