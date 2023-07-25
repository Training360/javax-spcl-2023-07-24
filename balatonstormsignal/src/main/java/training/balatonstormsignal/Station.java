package training.balatonstormsignal;

import lombok.Data;

@Data
public class Station {

    private long hwId;

    private String groupId;

    private String name;

    private int level;
}
