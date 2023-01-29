import Service.TaskManager;
import Tasks.TaskType;
import Tasks.Task;
import Tasks.*;
import exeptions.IllegalParemetrtException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final TaskManager taskManager = new TaskManager();
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void main(String[] args) throws IllegalParemetrtException {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) throws IllegalParemetrtException {
        scanner.useDelimiter("\n");

        System.out.print("Введите название задачи: ");
        String title = scanner.next();

        System.out.print("Введите описание задачи: ");
        String description = scanner.next();

        System.out.print("Введите тип задачи(1 - личная, 2 - рабочая): ");
        TaskType type = null;

        int taskTypeSelection = scanner.nextInt();
        switch (taskTypeSelection) {
            case 1:
                type = TaskType.PRIVATE;
                break;
            case 2:
                type = TaskType.WORK;
                break;
            default:
                System.out.println(" Не верно указан тип задачи ");
                scanner.close();
        }

        System.out.print("Введите дату и время создания задачи в формате dd.mm.yyyy hh:mm ");

        LocalDateTime taskTime = null;
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            taskTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } else  {
            System.out.println("Введите дату и время создания задачи в формате dd.mm.yyyy hh:mm ");
            scanner.close();
        }

        if (taskTime == null){
            System.out.println("Введите дату и время создания задачи в формате dd.mm.yyyy hh:mm ");
            scanner.close();
        }

        System.out.print("Введите повторяемость задачи(1 - однократно, 2 - ежедневно, 3 - еженедельно, 4 - ежемесячно, 5 - ежегодно): ");

        Task task = null;
        if (scanner.hasNextInt()) {
            int repeatability = scanner.nextInt();

            switch (repeatability) {
                case 1:
                    task = new OneTimeTask(title, description, type, taskTime);
                    break;
                case 2:
                    task = new DailyTask(title, description, type, taskTime);
                    break;
                case 3:
                    task = new WeeklyTask(title, description, type, taskTime);
                    break;
                case 4:
                    task = new MonthlyTask(title, description, type, taskTime);
                    break;
                case 5:
                    task = new YearlyTask(title, description, type, taskTime);
                    break;
                default:
                    System.out.println("Указана некорректная частота повторения");
                    scanner.close();

            }
        }
        taskManager.add(task);
        System.out.println("Задача добавлена");
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("0. Выход");
    }
}

