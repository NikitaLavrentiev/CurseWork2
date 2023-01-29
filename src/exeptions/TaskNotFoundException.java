package exeptions;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(Integer ID) {
        super("Задача с номером " + ID + " не найдена");
    }
}
