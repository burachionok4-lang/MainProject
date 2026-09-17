package Collection;

import Car.Car;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    @Test
    void newListIsEmpty() {
        CustomArrayList<String> list = new CustomArrayList<>();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void addIncreasesSize() {
        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("a");
        list.add("b");

        assertEquals(2, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
    }

    @Test
    void listGrowsWhenArrayIsFull() {
        CustomArrayList<String> list = new CustomArrayList<>(2);

        list.add("a");
        list.add("b");
        list.add("c");

        assertEquals(3, list.size());
        assertEquals("c", list.get(2));
    }

    @Test
    void negativeCapacityIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new CustomArrayList<String>(-1));
    }

    @Test
    void addByIndexShiftsOtherElements() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");
        list.add("c");

        list.add(1, "b");

        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    void invalidIndexIsRejected() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void setReplacesElementAndReturnsOldOne() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");

        String old = list.set(0, "b");

        assertEquals("a", old);
        assertEquals("b", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void removeByIndexShiftsTailLeft() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        String removed = list.remove(0);

        assertEquals("a", removed);
        assertEquals(2, list.size());
        assertEquals("b", list.get(0));
        assertEquals("c", list.get(1));
    }

    @Test
    void removeByValueComparesByEquals() {
        CustomArrayList<Car> list = new CustomArrayList<>();
        list.add(Car.of("Toyota", 150.0, 2010));

        boolean removed = list.remove(Car.of("Toyota", 150.0, 2010));

        assertTrue(removed);
        assertEquals(0, list.size());
    }

    @Test
    void containsFindsElement() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");

        assertTrue(list.contains("a"));
        assertFalse(list.contains("b"));
        assertEquals(0, list.indexOf("a"));
        assertEquals(-1, list.indexOf("b"));
    }

    @Test
    void clearRemovesAllElements() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");
        list.add("b");

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void forEachGoesThroughAllElements() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        String result = "";
        for (String item : list) {
            result = result + item;
        }

        assertEquals("abc", result);
    }

    @Test
    void streamFiltersElements() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> even = list.stream().filter(number -> number % 2 == 0).toList();

        assertEquals(2, even.size());
        assertEquals(2, even.get(0));
        assertEquals(4, even.get(1));
    }
}
