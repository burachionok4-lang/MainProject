package Car;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomCarGeneratorTest {

    @Test
    void generatedCarIsAlwaysValid() {
        for (int i = 0; i < 100; i++) {
            Car car = RandomCarGenerator.generate();

            assertFalse(car.getModel().isBlank());
            assertTrue(car.getYear() >= Car.MIN_YEAR);
            assertTrue(car.getYear() <= Car.MAX_YEAR);
            assertTrue(car.getPower() >= Car.MIN_POWER);
            assertTrue(car.getPower() <= Car.MAX_POWER);
        }
    }

    @Test
    void generatesDifferentCars() {
        Car first = RandomCarGenerator.generate();

        boolean foundDifferent = false;
        for (int i = 0; i < 100; i++) {
            if (!RandomCarGenerator.generate().equals(first)) {
                foundDifferent = true;
                break;
            }
        }

        assertTrue(foundDifferent);
    }

    @Test
    void fillAddsRequestedNumberOfCars() {
        List<Car> cars = new ArrayList<>();

        RandomCarGenerator.fill(cars, 5, false);

        assertEquals(5, cars.size());
    }

    @Test
    void fillWithClearRemovesOldCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.of("Toyota", 150.0, 2010));

        RandomCarGenerator.fill(cars, 3, true);

        assertEquals(3, cars.size());
    }

    @Test
    void fillWithoutClearKeepsOldCars() {
        List<Car> cars = new ArrayList<>();
        Car toyota = Car.of("Toyota", 150.0, 2010);
        cars.add(toyota);

        RandomCarGenerator.fill(cars, 2, false);

        assertEquals(3, cars.size());
        assertEquals(toyota, cars.get(0));
    }

    @Test
    void negativeCountIsRejected() {
        List<Car> cars = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> RandomCarGenerator.fill(cars, -1, false));
    }
}
