package Service;

import Tasks.Task;
import exeptions.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void add(Task task) {
        this.taskMap.put(task.getID(),task);
    }

    public void remove(Integer ID) throws TaskNotFoundException {
        if (taskMap.containsKey(ID)) {
            this.taskMap.remove(ID);
        } else {
            throw new TaskNotFoundException(ID);
        }
    }

    public Collection<Task> getAllByDate(LocalDate date) {
        Collection<Task> taskByDay = new ArrayList<>();

        for (Task task :
                taskMap.values()) {
            LocalDateTime taskTime = task.getTaskTime();
            LocalDateTime taskNextTime = task.getRepeatTime(taskTime);

            if (taskNextTime == null || taskTime.toLocalDate().equals(date)) {
                taskByDay.add(task);
                continue;
            }
            do {
                if (taskNextTime.toLocalDate().equals(date)) {
                    taskByDay.add(task);
                    break;
                }
                taskNextTime = task.getRepeatTime(taskNextTime);
            } while (taskNextTime.toLocalDate().isBefore(date));
            
        }
        return taskByDay;
    }
}
