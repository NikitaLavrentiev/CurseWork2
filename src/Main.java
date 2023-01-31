import Service.TaskManager;
import Tasks.*;
import exeptions.IllegalParemetrtException;
import exeptions.IncorrectBooleanException;
import exeptions.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                            addToArchive(scanner);
                            break;
                        case 3:
                            printAllByDate(scanner);
                            break;
                        case 4:
                            printAllArchived();
                            break;
                        case 5:
                            changeTitle(scanner, scanner);
                            break;
                        case 6:
                            changeDescription(scanner, scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (TaskNotFoundException | IncorrectBooleanException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Добавить задачу" + "\n2. Удалить задачу" + "\n3. Получить задачу на указанный день" +
                "\n4. Получить список удалённых задач" + "\n5. Изменить название задачи " +
                "\n.6 Изменить описание задачи" + "\n0. Выход");
    }

    private static void createTask(String title, String description, TaskType type, LocalDateTime taskTime, Integer repeatability) {
        Task task = null;
        try {
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

            }
        } catch (IllegalParemetrtException e) {
            throw new RuntimeException(e);
        }
        if (task != null) {
            TASK_MANAGER.add(task);
            System.out.println("Задача добавлена");
        } else {
            System.out.println("Введены некорректные значения");
        }
    }

    private static void printAllByDate(Scanner scanner) {
        System.out.print("Введите дату и время в формате dd.mm.yyyy ");

        if (scanner.hasNext(DATE_PATTERN)) {
            String dateTime = scanner.next(DATE_PATTERN);
            LocalDate inputDate = LocalDate.parse(dateTime, DATE_FORMATTER);

            Collection<Task> tasksByDay = TASK_MANAGER.getAllByDate(inputDate);
            for (Task task : tasksByDay
            ) {
                System.out.println(task);
            }
        } else {
            System.out.println("Введите дату и время в формате dd.mm.yyyy hh:mm ");

        }
    }

    private static void printAllArchived() throws IncorrectBooleanException {
        System.out.println("Вывожу архивные задачи");
        Collection<Task> deleted = TASK_MANAGER.getAllDeleted();//подумать над проверками
        for (Task task : deleted
        ) {
            System.out.println(task);

        }
    }

    private static void changeTitle(Scanner scanner1, Scanner scanner2) throws TaskNotFoundException, IllegalParemetrtException { //сделать проверки и передавать параметры
        System.out.println("Введите ID задачи");
        Integer ID = scanner1.nextInt();
        System.out.println("Введите новый заголовок");
        String newTitle = scanner2.nextLine();

        TASK_MANAGER.changeTitle(ID, newTitle);
        System.out.println("Заголовок задачи с ID " + ID + " изменён на " + newTitle);

    }

    private static void changeDescription(Scanner scanner1, Scanner scanner2) throws TaskNotFoundException, IllegalParemetrtException { //сделать проверки и передавать параметры
        System.out.println("Введите ID задачи");
        Integer ID = scanner1.nextInt();
        System.out.println("Введите новое описание");
        String newDescription = scanner2.nextLine();

        TASK_MANAGER.changeDescription(ID, newDescription);
        System.out.println("Заголовок задачи с ID " + ID + " изменён на " + newDescription);

    }

    private static String inputTaskTitle(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String title = scanner.next();

        if (title.isBlank()) {
            System.out.print("Не указано название задачи");

        }
        return title;

    }

    private static String inputTaskDescription(Scanner scanner) {
        System.out.print("Введите описание задачи: ");
        String description = scanner.next();

        if (description.isBlank()) {
            System.out.print("Не указано описание задачи");

        }
        return description;
    }

    private static TaskType inputTaskType(Scanner scanner) {
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

        }
        return type;
    }

    private static LocalDateTime inputTaskTime(Scanner scanner) {
        System.out.print("Введите дату и время создания задачи в формате dd.mm.yyyy hh:mm ");

        LocalDateTime taskTime = null;
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            taskTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Введите дату и время создания задачи в формате dd.mm.yyyy hh:mm ");

        }

        if (taskTime == null) {
            System.out.println("Введите дату и время создания задачи в формате dd.mm.yyyy hh:mm ");

        }

        return taskTime;
    }

    private static Integer inputRepeatability(Scanner scanner) {
        System.out.print("Введите повторяемость задачи(1 - однократно, 2 - ежедневно, 3 - еженедельно, 4 - ежемесячно, 5 - ежегодно): ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Введены число, из списка");
        }
        return null;
    }

    private static void addToArchive(Scanner scanner) {
        System.out.println("Введите ID задачи для архивации");
        try {
            Integer ID = scanner.nextInt();
            TASK_MANAGER.toArchive(ID);
            System.out.println("Задача номер " + ID + " перемещена в архив");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void inputTask(Scanner scanner) {
        scanner.useDelimiter("\n");

        String title = inputTaskTitle(scanner);
        String description = inputTaskDescription(scanner);
        TaskType type = inputTaskType(scanner);
        LocalDateTime taskTime = inputTaskTime(scanner);
        Integer repeatability = inputRepeatability(scanner);
        createTask(title, description, type, taskTime, repeatability);
    }

}

