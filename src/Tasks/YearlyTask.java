package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatable{
    public YearlyTask(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        super(title, description, taskType, taskTime);
    }

    @Override
    public LocalDateTime repeat(LocalDateTime dateTime) {
        return dateTime.plusYears(1);
    }
}
