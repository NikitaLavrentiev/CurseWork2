package Tasks;

import java.time.LocalDate;

public interface Repeatable {

    LocalDate getRepeatTime(LocalDate dateTime);
}
