package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDateTime;

public class OneTimeTask extends Task implements Repeatable{
    public OneTimeTask(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        super(title, description, taskType, taskTime);
    }

    @Override
    public LocalDateTime getRepeatTime(LocalDateTime dateTime) {
        return null;
    }
}
