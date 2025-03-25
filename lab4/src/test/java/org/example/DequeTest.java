package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DequeTest {
    private DequeImpl<Integer> deque;

    @BeforeEach
    public void setUp() {
        deque = new DequeImpl<>();
    }

    @Test
    public void testAddFirst() {
        deque.addFirst(10);
        assertEquals(1, deque.size());
        assertEquals(10, deque.getFirst());
        assertEquals(10, deque.getLast());
    }

    @Test
    public void testAddFirstNull() {
        deque.addFirst(null);
        assertEquals(1, deque.size());
        assertNull(deque.getFirst());
    }

    @Test
    public void testAddLast() {
        deque.addLast(11);
        assertEquals(1, deque.size());
        assertEquals(11, deque.getFirst());
        assertEquals(11, deque.getLast());
    }

    @Test
    public void testAddLastNull() {
        deque.addLast(null);
        assertEquals(1, deque.size());
        assertNull(deque.getLast());
    }

    @Test
    public void testRemoveFirst() {
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(2, deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(1, deque.getFirst());
        assertEquals(1, deque.getLast());
    }

    @Test
    public void testRemoveFirstEmpty() {
        assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(2, deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals(1, deque.getLast());
    }

    @Test
    public void testRemoveLastEmpty() {
        assertThrows(NoSuchElementException.class, () -> deque.removeLast());
    }

    @Test
    public void testGetFirstEmpty() {
        assertThrows(NoSuchElementException.class, () -> deque.getFirst());
    }

    @Test
    public void testGetLastEmpty() {
        assertThrows(NoSuchElementException.class, () -> deque.getLast());
    }

    @Test
    public void testAddAll() {
        assertTrue(deque.addAll(Arrays.asList(1, 2, 3)));
        assertEquals(3, deque.size());
        assertEquals(1, deque.getFirst());
        assertEquals(3, deque.getLast());
    }

    @Test
    public void testAddAllEmpty() {
        assertFalse(deque.addAll(Collections.emptyList()));
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddAllWithNulls() {
        deque.addAll(Arrays.asList(1, null, 3));
        assertEquals(3, deque.size());
        assertTrue(deque.contains(null));
    }

    @Test
    public void testRemove() {
        deque.addAll(Arrays.asList(1, 2, 3, 4));
        assertTrue(deque.remove(2));
        assertEquals(3, deque.size());
        assertFalse(deque.contains(2));
    }

    @Test
    public void testRemoveWithDuplicates() {
        deque.addAll(Arrays.asList(1, 2, 3, 2, 4));
        assertTrue(deque.remove(2));
        assertTrue(deque.contains(2));
        deque.remove(2);
        assertFalse(deque.contains(2));
    }

    @Test
    public void testRemoveFromEmptyDeque() {
        assertFalse(deque.remove(1));
    }

    @Test
    public void testRemoveNull() {
        deque.addAll(Arrays.asList(1, null, 3));
        assertTrue(deque.remove(null));
        assertEquals(2, deque.size());
        assertFalse(deque.contains(null));
    }

    @Test
    public void testRemoveNonExistent() {
        deque.addAll(Arrays.asList(1, 2, 3));
        assertFalse(deque.remove(5));
        assertEquals(3, deque.size());
    }

    @Test
    public void testContains() {
        deque.addAll(Arrays.asList(1, 2, 3));
        assertTrue(deque.contains(2));
        assertFalse(deque.contains(5));
    }

    @Test
    public void testContainsNull() {
        deque.addAll(Arrays.asList(1, null, 3));
        assertTrue(deque.contains(null));
    }

    @Test
    public void testSize() {
        assertEquals(0, deque.size());
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    public void testMultipleOperations() {
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        assertEquals(3, deque.size());
        assertEquals(0, deque.getFirst());
        assertEquals(2, deque.getLast());
    }
}
