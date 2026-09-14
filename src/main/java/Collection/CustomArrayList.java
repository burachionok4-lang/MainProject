package Collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Начальная ёмкость не может быть отрицательной: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public void add(T item) {
        ensureCapacity(size + 1);
        elements[size] = item;
        size++;
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне диапазона вставки [0, " + size + "]");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    public T set(int index, T item) {
        checkIndex(index);
        T previous = elementAt(index);
        elements[index] = item;
        return previous;
    }

    public T remove(int index) {
        checkIndex(index);
        T removed = elementAt(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removed;
    }

    public boolean remove(T item) {
        int index = indexOf(item);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliterator(elements, 0, size, Spliterator.ORDERED);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(elements[i]);
        }
        return builder.append("]").toString();
    }

    private void ensureCapacity(int required) {
        if (required <= elements.length) {
            return;
        }
        int newCapacity = elements.length == 0 ? DEFAULT_CAPACITY : elements.length * 2;
        if (newCapacity < required) {
            newCapacity = required;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне диапазона [0, " + (size - 1) + "]");
        }
    }

    @SuppressWarnings("unchecked")
    private T elementAt(int index) {
        return (T) elements[index];
    }

    private class CustomIterator implements Iterator<T> {

        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементы коллекции закончились");
            }
            return elementAt(cursor++);
        }
    }
}
