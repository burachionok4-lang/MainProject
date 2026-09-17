package Car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarParserJsonTest {

    @TempDir
    Path tempDir;

    @Test
    void writesAndReadsBackSameCars() {
        Path file = tempDir.resolve("cars.json");
        List<Car> written = new ArrayList<>();
        written.add(Car.of("Toyota", 150.0, 2010));
        written.add(Car.of("BMW", 300.0, 2020));

        try {
            CarParser.writeJSON(file, written);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        List<Car> read = new ArrayList<>();

        try {
            CarParser.readJSON(file, read, true);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }


        assertEquals(written, read);
    }

    @Test
    void readWithoutClearAddsToExistingCars() {
        Path file = tempDir.resolve("cars.json");
        List<Car> written = new ArrayList<>();
        written.add(Car.of("Toyota", 150.0, 2010));

        try {
            CarParser.writeJSON(file, written);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        List<Car> destination = new ArrayList<>();
        destination.add(Car.of("Audi", 300.0, 2005));

        try {
            CarParser.readJSON(file, destination, false);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        assertEquals(2, destination.size());
        assertEquals("Audi", destination.get(0).getModel());
        assertEquals("Toyota", destination.get(1).getModel());
    }

    @Test
    void readingMissingFileIsRejected() {
        Path missing = tempDir.resolve("no-such-file.json");
        List<Car> destination = new ArrayList<>();

        assertThrows(IOException.class, () -> CarParser.readJSON(missing, destination, false));
    }
}
