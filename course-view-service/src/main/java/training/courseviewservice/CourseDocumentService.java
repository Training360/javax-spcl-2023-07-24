package training.courseviewservice;

import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CourseDocumentService {

    private CourseDocumentRepository courseDocumentRepository;

    public void saveCourseDocument(CourseHasCreatedEvent event) {
        var document = new CourseDocument(event.getId(), event.getName(), event.getDescription(), event.getSyllabus());
        courseDocumentRepository.save(document);
    }


    public List<CourseDocument> findAllCourseDocument() {
        return StreamSupport.stream(
                courseDocumentRepository.findAll().spliterator(), false).toList();
    }

    public List<CourseDocument> findByWord(String word) {
        return courseDocumentRepository.findByWord(word);
    }
}
