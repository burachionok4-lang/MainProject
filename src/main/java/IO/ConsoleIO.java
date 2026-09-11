package IO;

import Car.Car;

import java.util.List;
import java.util.Scanner;

public class ConsoleIO {
    static public final int DEFAULT_PRINT_LIST_LIMIT = 10;;

    public static void printMainMenu() {
        System.out.println("=========================================\n" +
                "         ПРИЛОЖЕНИЕ СОРТИРОВКИ \n" +
                "=========================================");

        System.out.println("1. Заполнить массив и выполнить сортировку");
        System.out.println("2. Выход из программы");

        System.out.print("Выберите действие (1 или 2): ");
    }

    public static void printFillOptions() {
        System.out.println("\n--- Выберите способ заполнения массива ---");

        System.out.println("1. Случайная генерация (рандом)");
        System.out.println("2. Ручной ввод с клавиатуры");
        System.out.println("3. Загрузка из файла");

        System.out.print("Ваш выбор: ");
    }

    public static void printExit() {
        System.out.println("\nПрограмма завершена. До свидания!");
    }

    public static void printFillSelection(FillSelectionOption selection) {
        switch (selection) {
            case RANDOM -> {
                System.out.println("Случайное заполнение");
            }
            case MANUAL_INPUT -> {
                System.out.println("Ручной ввод данных:");
            }
            case FROM_FILE -> {
                System.out.print("Введите путь к файлу (например data.txt): ");
            }
            default -> {
                System.out.println("Неверный выбор");
            }
        }
    }

    //@param limit how many to print, 0 is unlimited
    public static void printCarList(List<Car> carList, int limit) {
        System.out.println("\nСписок машин:");

        var stream = carList.stream();

        if (limit > 0) {
            stream = stream.limit(limit);
        }

        stream.forEach(System.out::println);

        if (carList.size() > limit) {
            System.out.println("и еще " + (carList.size() - 1 - limit) + " элементов...");
        }
    }

    public static void printSortingOptions() {
        System.out.println("\n--- Сортировка: ---");

        System.out.println("1. Мощность");
        System.out.println("2. Модель");
        System.out.println("3. Год");
        System.out.println("4. По всем полям");
    }

    public static void randomFill()
    {
        System.out.print("\nВведите количество: ");

        Scanner scanner = new Scanner(System.in);

        int amount = scanner.nextInt();

        //TODO
    }

}
