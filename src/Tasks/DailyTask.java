package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{

    public DailyTask(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        super(title, description, taskType, taskTime);
    }

    @Override
    public LocalDate getRepeatTime(LocalDate dateTime) {
        return dateTime.plusDays(1);
    }

}
