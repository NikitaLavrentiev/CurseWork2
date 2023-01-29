package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDateTime;

public class DailyTask extends Task{

    public DailyTask(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        super(title, description, taskType, taskTime);
    }

    @Override
    public LocalDateTime repeat(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }
}
