package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDate;

public class OneTimeTask extends Task implements Repeatable{
    public OneTimeTask(String title, String description, TaskType taskType, LocalDate taskTime) throws IllegalParemetrtException {
        super(title, description, taskType, taskTime);
    }

    @Override
    public LocalDate getRepeatTime(LocalDate dateTime) {
        return null;
    }
}
