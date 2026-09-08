package IO;

public class ConsoleIO {
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
        System.out.println("Программа завершена. До свидания!");
    }

    public static void printFillSelection(int option) {
        switch (option) {
            case 1 -> {
                System.out.println("Генерация случайных данных...");
            }
            case 2 -> {
                System.out.println("Ручной ввод данных:");

            }
            case 3 -> {
                System.out.print("Введите путь к файлу (например data.txt): ");

            }
            default -> {
                System.out.println("Неверный выбор");
            }
        }
    }
}
