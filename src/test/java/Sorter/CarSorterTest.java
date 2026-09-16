package Sorter;

import Car.Car;
import Collection.CustomArrayList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarSorterTest {

    private List<Car> threeCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.of("Toyota", 150.0, 2010));
        cars.add(Car.of("Audi", 300.0, 2005));
        cars.add(Car.of("BMW", 90.0, 2020));
        return cars;
    }

    @Test
    void sortsByYear() {
        List<Car> cars = threeCars();

        CarSorter.sort(cars, new CarSorter.SortByField<>(Car::getYear));

        assertEquals(2005, cars.get(0).getYear());
        assertEquals(2010, cars.get(1).getYear());
        assertEquals(2020, cars.get(2).getYear());
    }

    @Test
    void sortsByModel() {
        List<Car> cars = threeCars();

        CarSorter.sort(cars, new CarSorter.SortByField<>(Car::getModel));

        assertEquals("Audi", cars.get(0).getModel());
        assertEquals("BMW", cars.get(1).getModel());
        assertEquals("Toyota", cars.get(2).getModel());
    }

    @Test
    void sortsByPower() {
        List<Car> cars = threeCars();

        CarSorter.sort(cars, new CarSorter.SortByField<>(Car::getPower));

        assertEquals(90.0, cars.get(0).getPower());
        assertEquals(150.0, cars.get(1).getPower());
        assertEquals(300.0, cars.get(2).getPower());
    }

    @Test
    void sortsCustomArrayList() {
        CustomArrayList<Car> cars = new CustomArrayList<>();
        cars.add(Car.of("Toyota", 150.0, 2010));
        cars.add(Car.of("Audi", 300.0, 2005));

        CarSorter.sort(cars, new CarSorter.SortByField<>(Car::getModel));

        assertEquals("Audi", cars.get(0).getModel());
        assertEquals("Toyota", cars.get(1).getModel());
    }

    @Test
    void emptyAndSingleListsAreNotBroken() {
        List<Car> empty = new ArrayList<>();
        List<Car> single = new ArrayList<>();
        single.add(Car.of("Toyota", 150.0, 2010));

        CarSorter.sort(empty, new CarSorter.SortByField<>(Car::getYear));
        CarSorter.sort(single, new CarSorter.SortByField<>(Car::getYear));

        assertEquals(0, empty.size());
        assertEquals(1, single.size());
        assertEquals("Toyota", single.get(0).getModel());
    }

    @Test
    void keepsDuplicatesAfterSorting() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.of("Toyota", 150.0, 2010));
        cars.add(Car.of("Audi", 300.0, 2005));
        cars.add(Car.of("Toyota", 150.0, 2010));

        CarSorter.sort(cars, new CarSorter.SortByField<>(Car::getModel));

        assertEquals(3, cars.size());
        assertEquals("Audi", cars.get(0).getModel());
        assertEquals("Toyota", cars.get(1).getModel());
        assertEquals("Toyota", cars.get(2).getModel());
    }

    @Test
    void alreadySortedListStaysSorted() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.of("Audi", 90.0, 2005));
        cars.add(Car.of("BMW", 150.0, 2010));
        cars.add(Car.of("Toyota", 300.0, 2020));

        CarSorter.sort(cars, new CarSorter.SortByField<>(Car::getYear));

        assertEquals(2005, cars.get(0).getYear());
        assertEquals(2010, cars.get(1).getYear());
        assertEquals(2020, cars.get(2).getYear());
    }
}
