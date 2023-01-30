package Service;

import Tasks.Task;
import exeptions.IllegalParemetrtException;
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
        this.taskMap.put(task.getID(), task);
    }

    public void toArchive(Integer ID) throws TaskNotFoundException {
        if (taskMap.containsKey(ID)) {
            this.taskMap.get(ID).setDeleted(true);// переносит в архив таск;
        } else {
            throw new TaskNotFoundException(ID);
        }
    }
    public void changeTitle(Integer ID, String title) throws TaskNotFoundException, IllegalParemetrtException {
        if (taskMap.containsKey(ID)) {
            if (title != null && !title.isBlank()) {
                this.taskMap.get(ID).setTitle(title);// меняет заголовок;
            } else {
                throw new IllegalParemetrtException(title);
            }
        } else {
            throw new TaskNotFoundException(ID);
        }
    }

    public void changeDescription(Integer ID, String description) throws TaskNotFoundException, IllegalParemetrtException {
        if (taskMap.containsKey(ID)) {
            if (description != null && !description.isBlank()) {
                this.taskMap.get(ID).setTitle(description);// меняет описание;
            } else {
                throw new IllegalParemetrtException(description);
            }
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

    public Collection<Task> getAllDeleted() {
        Collection<Task> deleted = new ArrayList<>();

        for (Task task :
                taskMap.values()) {
            boolean b = task.isDeleted();

            if (b == true) {
                deleted.add(task);
            }
        }
        return deleted;
    }
}
