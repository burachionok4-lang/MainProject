package Sorter;

import Car.*;

import java.util.Comparator;
import java.util.List;

public class CarSorter {

    private CarSorter() {};

    //Strategy 1
    public static class SortByField implements Comparator<Car> {
        SortByField(String fieldName) {
            //TODO
        }

        @Override
        public int compare(Car o1, Car o2) {
            //TODO
            return 0;
        }

    }

    //Strategy 2
    public static class SortByAllFields implements Comparator<Car> {
        @Override
        public int compare(Car o1, Car o2) {
            //TODO
            return 0;
        }

    }

    public static void sort(List<Car> list, Comparator<Car> strategy) {

    }
}