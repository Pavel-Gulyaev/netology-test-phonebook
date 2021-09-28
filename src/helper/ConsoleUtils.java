package helper;

import java.util.Scanner;

import domain.ContactType;
import domain.Option;

public class ConsoleUtils {
    private ConsoleUtils() {
        //utilsClass
    }

    public static void printMenu() {
        System.out.println("Выберете опцию:");
        System.out.println("1) Добавить контакт");
        System.out.println("2) Удалить контакт");
        System.out.println("3) Просмотреть все контакты");
        System.out.println("4) Найти контакт");
        System.out.println("5) Завершить работу");
    }

    public static void printTypes() {
        System.out.println("Выберете тип контакта:");
        System.out.println("1) Семья");
        System.out.println("2) Работа");
        System.out.println("3) Друзья");
    }

    public static Option takeOption() {
        int value = takeIntInInterval(1, Option.values().length);
        return Option.values()[value - 1];
    }

    public static int takeIntInInterval(int start, int end) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Введите число от  %d до %d.%n", start, end);
        while (true) {
            String line = sc.nextLine();
            if (isNumber(line)) {
                int value = Integer.parseInt(line);
                if (isInRange(value, start, end)) {
                    return value;
                } else {
                    System.out.printf("Я же просил ввести число от %d до %d. Еще раз...%n", start, end);
                }
            } else {
                System.out.println("Я же просил ввести число. Еще раз...");
            }
        }
    }

    private static boolean isInRange(int value, int from, int to) {
        return value >= from && value <= to;
    }

    private static boolean isNumber(String str) {
        return str.matches("-?\\d+");
    }

    public static ContactType takeType() {
        int value = takeIntInInterval(1, ContactType.values().length);
        return ContactType.values()[value - 1];
    }

    public static String getInputValue(String output) {
        System.out.println(output);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
