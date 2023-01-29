package Tasks;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Task {
    /*В ежедневник можно заносить задачи, можно удалять их, можно получать список задач на предстоящий день.

Каждая задача обязательно имеет заголовок. У каждой задачи может быть поле для описания.
Также все задачи обязательно нужно делить по типу: личные или рабочие задачи. У каждой задачи есть дата и время, которые были присвоены при создании.*/
    private String title;
    private final boolean isItPrivateTask;
    private final String creationDate;
    private final String creationTime;
    private String description;
    private final int id;
    private static int counter;

    private repetitionRate repetitionRate;

    public Task(String title, boolean isItPrivateTask, String description, Task.repetitionRate repetitionRate) {
        this.title = title;
        this.isItPrivateTask = isItPrivateTask;
        this.description = description;
        this.creationDate = String.valueOf(LocalDate.now(Clock.systemDefaultZone()));//возможно есть опции для объединения времени и даты, нужно посмотреть ещё
        this.creationTime = String.valueOf(LocalTime.now(Clock.systemDefaultZone()));
        this.repetitionRate = repetitionRate;
        this.id = counter;
        counter++;
    }
    //создать метод дабавить таск и удалить таск
    //добавить интерфейс, чтобы переопределить значения для того что у меня в енамах и можно было поведение выставить

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isItPrivateTask() {
        return isItPrivateTask;
    }

    public String showCreationDateAndTime() {//чекнуть метод возможно работает некорректно
        return creationDate + creationTime;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public Task.repetitionRate getRepetitionRate() {
        return repetitionRate;
    }

    public void setRepetitionRate(Task.repetitionRate repetitionRate) {
        this.repetitionRate = repetitionRate;
    }

    //оверрайд ту стинг (тегулярная булин на печать для типов ворк или прайвет таск)


    enum repetitionRate {

        ONCE("однократная"),
        EVERYDAY("ежедневная"),
        EVERY_WEEK("еженедельная"),
        EVERY_MONTH("ежемесячная"),
        EVERY_YEAR("ежегодная");
        private final String repeatability;
        repetitionRate(String repeatability) {
            this.repeatability = repeatability;
        }

        public String getRepeatability() {
            return repeatability;
        }

        @Override
        public String toString() {
            return " частота повторений " + repeatability + ", ";
        }
    }
}
