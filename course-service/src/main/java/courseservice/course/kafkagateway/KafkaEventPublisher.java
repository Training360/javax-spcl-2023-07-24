package courseservice.course.kafkagateway;

import courseservice.course.service.CourseHasCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaEventPublisher {

    private StreamBridge streamBridge;

    @EventListener
    public void sendEvent(CourseHasCreatedEvent event) {
        streamBridge.send("course-events-topic", event);
    }
}
