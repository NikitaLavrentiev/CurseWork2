package Tasks;

import java.time.LocalDateTime;

public interface Repeatable {

    LocalDateTime getRepeatTime(LocalDateTime dateTime);
}
