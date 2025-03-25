package org.example;

import org.example.interfaces.Deque;
import java.util.Collection;
import java.util.NoSuchElementException;

public class DequeImpl<E> implements Deque<E> {
    private Node<E> head, tail; // first and last elements of list
    private int size;           // number of elements

    private static class Node<E> {
        E data;
        Node<E> prev, next;

        Node(E data) {
            this.data = data;
        }
    }

    public DequeImpl() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;

            /* Example:
               [10 head && tail].addFirst(20)
               [20 head (next:10), 10 tail (prev:20)].addFirst(30)
               [30 head (next:20), 20 (prev:30, next:10), 10 tail (prev:20)]
            */
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;

            /* Example:
               [10 head && tail].addLast(20)
               [10 head (next:20), 20 tail (prev:10)].addLast(30)
               [10 head (next:20), 20 (prev:10, next:30), 30 tail (prev:20)]
            */
        }
        size++;
    }

    @Override
    public E removeFirst() {
        // Deque: [10 head (next:20), 20 tail (prev:10)].removeFirst()

        if (size == 0) {
            throw new NoSuchElementException("Deque is empty");
        }

        E data = head.data; // data = 10
        head = head.next;   // head = 20

        if (head == null) {
            tail = null;
        }  else {
            head.prev = null;
        }

        // Deque: [20 head]

        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Deque is empty");
        }

        E data = tail.data;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        size--;
        return data;
    }

    @Override
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Deque is empty");
        }

        return head.data;
    }

    @Override
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("Deque is empty");
        }

        return tail.data;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean success = false;
        for (E element : c) {
            addLast(element);
            success = true;
        }
        return success;
    }

    @Override
    public boolean remove(Object obj) {
        Node<E> current = head;

        while (current != null) {
            if (isEqual(obj, current.data)) {
                if (current.prev == null){
                    removeFirst();
                } else if (current.next == null){
                    removeLast();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private boolean isEqual(Object a, Object b) {
        return isNull(a, b) || (a != null && a.equals(b));
    }

    private boolean isNull(Object... objects) {
        for (Object obj : objects) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object obj) {
        Node<E> current = head;
        while(current != null) {
            if (isEqual(obj, current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
}
