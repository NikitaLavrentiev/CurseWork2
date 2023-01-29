package Tasks;

import exeptions.IllegalParemetrtException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    /*В ежедневник можно заносить задачи, можно удалять их, можно получать список задач на предстоящий день.

Каждая задача обязательно имеет заголовок. У каждой задачи может быть поле для описания.
Также все задачи обязательно нужно делить по типу: личные или рабочие задачи. У каждой задачи есть дата и время, которые были присвоены при создании.*/
    private String title;
    private final TaskType taskType;
    private final LocalDateTime taskTime;
    private String description;
    private final int id;
    private static int counter = 1;

    public Task(String title, String description, TaskType taskType, LocalDateTime taskTime) throws IllegalParemetrtException {
        setTitle(title);
        setDescription(description);
        if (taskType != null) {
            this.taskType = taskType;
        } else {
            throw new IllegalParemetrtException(" Тип задания ");
        }
        if (taskTime != null) {
            this.taskTime = taskTime;
        } else {
            throw new IllegalParemetrtException(" Время задания ");
        }

        this.id = counter;
        counter++;
    }
    //создать метод дабавить таск и удалить таск
    //добавить интерфейс, чтобы переопределить значения для того что у меня в енамах и можно было поведение выставить

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public static void setCounter(int counter) {
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

    public int getId() {
        return id;
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

    //оверрайд ту стинг (тегулярная булин на печать для типов ворк или прайвет таск)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && taskType == task.taskType && Objects.equals(taskTime, task.taskTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskType, taskTime, description, id);
    }
/*    enum RepetitionRate {

        ONCE("однократная"),
        EVERYDAY("ежедневная"),
        EVERY_WEEK("еженедельная"),
        EVERY_MONTH("ежемесячная"),
        EVERY_YEAR("ежегодная");
        private final String repeatability;

        RepetitionRate(String repeatability) {
            this.repeatability = repeatability;
        }

        public String getRepeatability() {
            return repeatability;
        }

        @Override
        public String toString() {
            return " частота повторений " + repeatability + ", ";
        }
    }*/
}
