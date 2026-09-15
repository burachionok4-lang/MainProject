package Car;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void builderCreatesCar() {
        Car car = new Car.Builder()
                .setModel("Toyota Corolla")
                .setPower(132.0)
                .setYear(2015)
                .build();

        assertEquals("Toyota Corolla", car.getModel());
        assertEquals(132.0, car.getPower());
        assertEquals(2015, car.getYear());
    }

    @Test
    void emptyModelIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Car.Builder().setModel(null));
        assertThrows(IllegalArgumentException.class, () -> new Car.Builder().setModel(""));
    }

    @Test
    void wrongYearIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Car.Builder().setYear(1800));
        assertThrows(IllegalArgumentException.class, () -> new Car.Builder().setYear(3000));
    }

    @Test
    void wrongPowerIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Car.Builder().setPower(0));
        assertThrows(IllegalArgumentException.class, () -> new Car.Builder().setPower(-100));
    }

    @Test
    void builderWithoutFieldsIsRejected() {
        assertThrows(IllegalStateException.class, () -> new Car.Builder().build());
    }

    @Test
    void carsAreComparedByFields() {
        Car car = Car.of("Toyota", 150.0, 2010);
        Car same = Car.of("Toyota", 150.0, 2010);
        Car other = Car.of("Toyota", 150.0, 2011);

        assertEquals(car, same);
        assertNotEquals(car, other);
    }
}
