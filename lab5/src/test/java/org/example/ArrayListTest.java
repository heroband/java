package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
    private ArrayListImpl<String> list;

    @BeforeEach
    void setUp() {
        list = new ArrayListImpl<>();
    }

    @Test
    void testConstructorEmpty() {
        assertEquals(0, list.size());
        assertEquals(4, list.getCapacity()); // Проверяем начальную емкость
    }

    @Test
    void testConstructorWithCapacity() {
        ArrayListImpl<String> listWithCapacity = new ArrayListImpl<>(10);
        assertEquals(0, listWithCapacity.size());
        assertEquals(10, listWithCapacity.getCapacity()); // Проверяем установленную емкость
    }

    @Test
    void testConstructorNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayListImpl<>(-1));
    }

    @Test
    void testAddSingleElement() {
        list.add("A");
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }

    @Test
    void testAddMultipleElements() {
        list.add("A");
        list.add("B");
        assertEquals(2, list.size());
        assertEquals("B", list.get(1));
    }

    @Test
    void testExpandCapacity() {
        int initialCapacity = list.getCapacity();
        for (int i = 0; i < initialCapacity; i++) {
            list.add("Item " + i);
        }
        assertEquals(initialCapacity, list.size());
        assertEquals(initialCapacity, list.getCapacity());

        // Добавляем еще один элемент, емкость должна увеличиться
        list.add("Extra");
        assertEquals(initialCapacity + 1, list.size());
        assertTrue(list.getCapacity() > initialCapacity); // Проверяем, что емкость увеличилась
    }

    @Test
    void testPutElement() {
        list.add("A");
        list.add("B");
        list.add("C");

        String oldValue = list.put(1, "X");
        assertEquals("B", oldValue);
        assertEquals("X", list.get(1));
    }

    @Test
    void testPutOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.put(0, "X"));
    }

    @Test
    void testRemoveByIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        String removed = list.remove(1);
        assertEquals("B", removed);
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveByIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void testAddAll() {
        Collection<String> collection = new ArrayList<>();
        Collections.addAll(collection, "A", "B", "C");
        assertTrue(list.addAll(collection));
        assertEquals(3, list.size());
    }

    @Test
    void testAddAllEmpty() {
        assertFalse(list.addAll(Collections.emptyList()));
    }

    @Test
    void testAddAllNull() {
        assertFalse(list.addAll(null));
    }

    @Test
    void testRemoveByObject() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertTrue(list.remove("B"));
        assertFalse(list.contains("B"));
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveNonExistentObject() {
        list.add("A");
        assertFalse(list.remove("Z"));
    }

    @Test
    void testRemoveNull() {
        list.add(null);
        assertTrue(list.remove(null));
        assertFalse(list.contains(null));
    }

    @Test
    void testContains() {
        list.add("A");
        list.add("B");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("Z"));
    }

    @Test
    void testContainsNull() {
        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    void testSizeOperations() {
        assertEquals(0, list.size());
        list.add("A");
        assertEquals(1, list.size());
        list.remove("A");
        assertEquals(0, list.size());
    }

    @Test
    void testGetValidIndex() {
        list.add("A");
        assertEquals("A", list.get(0));
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testCapacityAfterResize() {
        ArrayListImpl<Integer> intList = new ArrayListImpl<>(2);
        assertEquals(2, intList.getCapacity());

        intList.add(1);
        intList.add(2);
        assertEquals(2, intList.getCapacity()); // Пока еще не увеличилось

        intList.add(3); // Должен произойти ресайз
        assertTrue(intList.getCapacity() > 2);
    }
}
