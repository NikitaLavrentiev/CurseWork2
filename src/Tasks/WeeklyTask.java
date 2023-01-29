package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable{
    public WeeklyTask(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        super(title, description, taskType, taskTime);
    }

    @Override
    public LocalDateTime getRepeatTime(LocalDateTime dateTime) {
        return dateTime.plusWeeks(1);
    }
}
