import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Car.*;
import static Car.Car.of;
import IO.*;
import Sorter.*;
import Test.CarParserTest;

class Main {
    public static void main(String[] args) {

        final boolean TEST_MODE = true;

        if(TEST_MODE)
        {
            System.out.println("Режим тестирования");

            IO.println("writeReadJSON: " + CarParserTest.writeReadJSON());

            return;
        }

        Scanner scanner = new Scanner(System.in);

        List<Car> cars = new ArrayList<>();     //Общий список на все виды ввода
        while (true) {
            ConsoleIO.printMainMenu();

            //TODO: валидировать, мб заменить на метод
            int mainSelectionInt = scanner.nextInt();
            scanner.nextLine();

            MainSelectionOption mainSelectionOption = MainSelectionOption.fromInt(mainSelectionInt);

            switch (mainSelectionOption) {
                case FILL_AND_SORT -> {
                    ConsoleIO.printFillOptions();

                    List<Car> filledCars = new ArrayList<>();

                    while (true) {
                        //TODO: валидировать, мб заменить на метод
                        int fillSelectionInt = scanner.nextInt();
                        scanner.nextLine();

                        FillSelectionOption fillSelectionOption = FillSelectionOption.fromInt(fillSelectionInt);

                        ConsoleIO.printFillSelection(fillSelectionOption);

                        switch (fillSelectionOption) {
                            case RANDOM -> {
                                //TODO
                                ConsoleIO.randomFill();
                            }
                            case MANUAL_INPUT -> {
                                //TODO

                                System.out.println("Введите количество машин:");

                                int count = scanner.nextInt();
                                scanner.nextLine();

                                CarParser.readFromConsole(count, filledCars, false);
                            }
                            case FROM_FILE -> {
                                String filePath = scanner.nextLine();

                                CarParser.readJSON(Paths.get(filePath), filledCars, false);
                            }
                        }

                        if (filledCars.isEmpty()) {
                            System.out.println("Список пуст.");
                            continue;
                        }

                        ConsoleIO.printCarList(filledCars, ConsoleIO.DEFAULT_PRINT_LIST_LIMIT);

                        break;
                    }

                    ConsoleIO.printSortingOptions();

                    //TODO
                    //CarSorter.sort(filledCars, strategy);

                    scanner.nextLine();
                }
                case EXIT -> {
                    ConsoleIO.printExit();

                    scanner.close();

                    return;
                }
                default -> System.out.println("Нет такого варианта");

            }
        }
    }
}