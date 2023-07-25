package training.balatonstormsignal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StormSignalEvent {

    private long sensorId;

    private String groupId;

    private String name;

    private int level;
}
