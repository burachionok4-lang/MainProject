import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Car.*;
import IO.*;
import Sorter.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                                //TODO: заглянуть в билдер и продумать валидацию получше
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
                    //Sorter.sort(filledCars, strategy);


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