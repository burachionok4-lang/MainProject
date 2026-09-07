import java.util.Scanner;
class Main {
    private static void runSortingProcess(Scanner scanner) {
        // Шаг 1: Выбор заполнения
        System.out.println("\n--- Выберите способ заполнения массива ---");
        System.out.println("1. Случайная генерация (рандом)");
        System.out.println("2. Ручной ввод с клавиатуры");
        System.out.println("3. Загрузка из файла");
        System.out.print("Ваш выбор: ");
        int fillChoice = scanner.nextInt();
        scanner.nextLine();

        // Запрашиваем длину (для рандома и ручного ввода, для файла можно игнорировать)
            System.out.print("Введите длину массива: ");
            int length = scanner.nextInt();
            scanner.nextLine();
        switch (fillChoice) {
            case 1 -> {
                System.out.println("Генерация случайных данных...");
//                array = RandomFiller.fill(length);
            }
            case 2 -> {
                System.out.println("Ручной ввод данных:");
//                array = ManualFiller.fill(length, scanner);
            }
            case 3 -> {
                System.out.print("Введите путь к файлу (например, data.txt): ");
                String filePath = scanner.nextLine();
//                array = FileFiller.fill(filePath);
            }
            default -> {
                System.out.println("Неверный выбор. Возврат в главное меню.");
                return;
            }
//            if (array == null || array.length == 0) {
//                System.out.println("Не удалось заполнить массив.");
//                return;
//            }

            // Вызываем выбор поля и сортировку...
//            chooseSorting(scanner, array);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=========================================\n" +
                    "         ПРИЛОЖЕНИЕ СОРТИРОВКИ \n" +
                    "=========================================");
            System.out.println("1. Заполнить массив и выполнить сортировку");
            System.out.println("2. Выход из программы");
            System.out.print("Выберите действие (1 или 2): ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();
            switch (mainChoice) {
                case 1:
                    runSortingProcess(scanner);
                case 2: {
                    System.out.println("Программа завершена. До свидания!");
                    scanner.close();
                    return;
                }
                default:
                    System.out.println("Ошибка: введите 1 или 2.");
            }
        }
    }
}