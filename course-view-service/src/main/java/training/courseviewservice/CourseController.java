package training.courseviewservice;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course-documents")
@AllArgsConstructor
public class CourseController {

    private CourseDocumentService courseDocumentService;

    @GetMapping
    public List<CourseDocument> findAllCourseDocument() {
        return courseDocumentService.findAllCourseDocument();
    }

    @GetMapping("/query")
    public List<CourseDocument> findByWord(@RequestParam String word) {
        return courseDocumentService.findByWord(word);
    }
}
