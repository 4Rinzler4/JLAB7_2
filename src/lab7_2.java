import java.io.*;
import java.util.*;

abstract class Record {
    public abstract String toString();
}

class Writer extends Record {
    public String lastName;
    public String firstName;
    public String language;
    public int bookCount;

    public Writer(String lastName, String firstName, String language, int bookCount) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.language = language;
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "Поет{" +
                "Прізвище='" + lastName + '\'' +
                ", Ім'я='" + firstName + '\'' +
                ", Мова='" + language + '\'' +
                ", Кількість збірок=" + bookCount +
                '}';
    }

    public int getLastNameLength() {
        return lastName.length();
    }
}

class Performance extends Record {
    public String date;
    public String place;
    public int audienceCount;

    public Performance(String date, String place, int audienceCount) {
        this.date = date;
        this.place = place;
        this.audienceCount = audienceCount;
    }

    @Override
    public String toString() {
        return "Виступ{" +
                "Дата='" + date + '\'' +
                ", Місце='" + place + '\'' +
                ", Кількість слухачів=" + audienceCount +
                '}';
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public String getDate() {
        return date;
    }
}

public class lab7_2 {
    private static final String FILE_PATH = "C:\\Users\\email\\IdeaProjects\\JLAB7_2\\src\\input.txt";

    public static void main(String[] args) {
        List<Writer> writers = new ArrayList<>();
        List<Performance> performances = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Додати запис про поета");
            System.out.println("2. Додати запис про виступ");
            System.out.println("3. Відобразити всі записи");
            System.out.println("4. Зберегти дані до файлу");
            System.out.println("5. Вихід");

            System.out.print("Введіть ваш вибір: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addWriter(writers, scanner);
                    break;
                case "2":
                    addPerformance(performances, scanner);
                    break;
                case "3":
                    displayRecords(writers, performances);
                    displayAdditionalInfo(writers, performances);
                    break;
                case "4":
                    saveDataToFile(writers, performances);
                    break;
                case "5":
                    System.out.println("Завершення програми.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некоректний вибір.");
                    break;
            }
        }
    }

    private static void addWriter(List<Writer> writers, Scanner scanner) {
        System.out.print("Прізвище поета: ");
        String lastName = scanner.nextLine();
        System.out.print("Ім'я: ");
        String firstName = scanner.nextLine();
        System.out.print("Мова: ");
        String language = scanner.nextLine();
        System.out.print("Кількість збірок: ");
        int bookCount = Integer.parseInt(scanner.nextLine());

        Writer writer = new Writer(lastName, firstName, language, bookCount);
        writers.add(writer);
        System.out.println("Запис про поета додано.");
    }

    private static void addPerformance(List<Performance> performances, Scanner scanner) {
        System.out.print("Дата виступу: ");
        String date = scanner.nextLine();
        System.out.print("Місце виступу: ");
        String place = scanner.nextLine();
        System.out.print("Кількість слухачів: ");
        int audienceCount = Integer.parseInt(scanner.nextLine());

        Performance performance = new Performance(date, place, audienceCount);
        performances.add(performance);
        System.out.println("Запис про виступ додано.");
    }

    private static void displayRecords(List<Writer> writers, List<Performance> performances) {
        System.out.println("Записи про поетів:");
        for (Writer writer : writers) {
            System.out.println(writer);
        }

        System.out.println("Записи про виступи:");
        for (Performance performance : performances) {
            System.out.println(performance);
        }
    }

    private static void displayAdditionalInfo(List<Writer> writers, List<Performance> performances) {
        System.out.println("Додаткова інформація:");
        System.out.println("Сумарна кількість слухачів: " + calculateTotalAudience(performances));
        System.out.println("День з найбільшою кількістю слухачів: " + findDayWithMaxAudience(performances));
        displayLastNameLengths(writers);
    }

    private static int calculateTotalAudience(List<Performance> performances) {
        int totalAudience = 0;
        for (Performance performance : performances) {
            totalAudience += performance.getAudienceCount();
        }
        return totalAudience;
    }

    private static String findDayWithMaxAudience(List<Performance> performances) {
        String maxDay = "";
        int maxAudience = 0;
        for (Performance performance : performances) {
            if (performance.getAudienceCount() > maxAudience) {
                maxAudience = performance.getAudienceCount();
                maxDay = performance.getDate();
            }
        }
        return maxDay;
    }

    private static void displayLastNameLengths(List<Writer> writers) {
        System.out.println("Довжина прізвищ поетів:");
        for (Writer writer : writers) {
            System.out.println(writer.lastName + ": " + writer.getLastNameLength());
        }
    }

    private static void saveDataToFile(List<Writer> writers, List<Performance> performances) {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            writer.println("Записи про поетів:");
            for (Writer writerRecord : writers) {
                writer.println(writerRecord);
            }
            writer.println();

            writer.println("Записи про виступи:");
            for (Performance performanceRecord : performances) {
                writer.println(performanceRecord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Дані збережено до файлу: " + FILE_PATH);
    }
}
