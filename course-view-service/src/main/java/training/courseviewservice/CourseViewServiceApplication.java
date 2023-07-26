package training.courseviewservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class CourseViewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseViewServiceApplication.class, args);
	}

	@Bean
	public Consumer<CourseHasCreatedEvent> handleEvent(CourseDocumentService service) {
		return courseHasCreatedEvent -> {
			log.error("Event has come: {}", courseHasCreatedEvent);
			service.saveCourseDocument(courseHasCreatedEvent);
		};

	}

}
