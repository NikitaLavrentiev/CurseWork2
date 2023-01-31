package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatable {
    public MonthlyTask(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        super(title, description, taskType, taskTime);

    }

    @Override
    public LocalDate getRepeatTime(LocalDate dateTime) {
        return dateTime.plusMonths(1);
    }
}
