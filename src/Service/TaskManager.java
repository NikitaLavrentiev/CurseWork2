package Service;

import Tasks.Task;
import exeptions.TaskNotFoundException;

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
}
