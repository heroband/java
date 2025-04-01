package org.example;

public class Main {
    public static void main(String[] args) {
        DequeImpl<Integer> deque = new DequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        System.out.println("First: " + deque.getFirst());
        System.out.println("Last: " + deque.getLast());
        System.out.println("Size: " + deque.size());
    }
}