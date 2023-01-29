package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Repeatable {
    private String title;
    private TaskType taskType;
    private LocalDateTime taskTime;
    private String description;
    private final Integer ID;
    private static int counter = 1;

    public Task(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        setTitle(title);
        setDescription(description);
        setTaskType(taskType);
        setTaskTime(taskTime);
        this.ID = counter;
        counter++;
    }

    public void setTaskType(TaskType taskType) throws IllegalParemetrtException {
        if (taskType != null) {
            this.taskType = taskType;
        } else {
            throw new IllegalParemetrtException(" Тип задания ");
        }
    }

    public void setTaskTime(LocalDateTime taskTime) throws IllegalParemetrtException {
        if (taskTime != null) {
            this.taskTime = taskTime;
        } else {
            throw new IllegalParemetrtException(" Время задания ");
        }
    }

    public Integer getID() {
        return ID;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public static void setCounter(Integer counter) {
        Task.counter = counter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalParemetrtException {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalParemetrtException(" Заголовок задания ");
        }
    }

    public static int getCounter() {
        return counter;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalParemetrtException {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        } else {
            throw new IllegalParemetrtException(" Описание задания ");
        }
    }

    @Override
    public String toString() {
        return "Задние номер " + ID +
                " название " + title +
                ", тип " + taskType +
                ", время создания " + taskTime +
                ", описание " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return title.equals(task.title) && taskType == task.taskType && taskTime.equals(task.taskTime) && description.equals(task.description) && ID.equals(task.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskType, taskTime, description, ID);
    }
}
