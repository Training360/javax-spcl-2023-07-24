package courseservice.course.dto;

import lombok.Data;

@Data
public class AnnounceCourseCommand {

    private String name;

    private String description;

    private String syllabus;

    private int limit;

}
