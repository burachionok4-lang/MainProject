package Sorter;

import Car.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class CarSorter {

    private CarSorter() {};

    //Strategy 1
    public static class SortByField<Car, R extends Comparable<R>> implements Comparator<Car> {
        Function<Car, R> fieldGetter;

        public SortByField(Function<Car, R> fieldGetter) {
            this.fieldGetter = fieldGetter;
        }

        private R extract(Car targetObject) {
            return fieldGetter.apply(targetObject);
        }

        @Override
        public int compare(Car o1, Car o2) {
            R val1 = extract(o1);
            R val2 = extract(o2);

            return val1.compareTo(val2);
        }

    }

    //Strategy 2
    public static class SortByAllFields implements Comparator<Car> {

        private static final Comparator<Car> ALL_FIELDS_COMPARATOR = Comparator
                .comparing(Car::getModel)
                .thenComparingDouble(Car::getPower)
                .thenComparingInt(Car::getYear);

        @Override
        public int compare(Car o1, Car o2) {
            return ALL_FIELDS_COMPARATOR.compare(o1, o2);
        }

    }

    //quicksort
    public static void sort(List<Car> list, Comparator<Car> strategy) {

        if (list == null || list.size() <= 1) {
            return;
        }

        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        stack.push(list.size() - 1);

        while (!stack.isEmpty()) {
            int high = stack.pop();
            int low = stack.pop();

            if (low >= high) {
                continue;
            }

            Car pivot = list.get(low + (high - low) / 2);
            int i = low;
            int j = high;

            while (i <= j) {
                while (strategy.compare(list.get(i), pivot) < 0) {
                    i++;
                }
                while (strategy.compare(list.get(j), pivot) > 0) {
                    j--;
                }
                if (i <= j) {
                    Collections.swap(list, i, j);
                    i++;
                    j--;
                }
            }

            if (low < j) {
                stack.push(low);
                stack.push(j);
            }

            if (i < high) {
                stack.push(i);
                stack.push(high);
            }
        }
    }

}


// public class CarSorter {
//     SortCarStrategy strategy;
//     public CarSorter(SortCarStrategy strategy){
//         this.strategy = strategy;
//     }
//     public void sortCars(List<Car> cars) {
//         strategy.sortCars(cars);
//     }
// }
