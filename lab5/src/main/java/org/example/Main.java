package org.example;

public class Main {
    public static void main(String[] args) {
        ArrayListImpl<String> list = new ArrayListImpl<>();

        System.out.println("Initial capacity: " + list.getCapacity());

        list.add("One");
        list.add("Two");
        list.add("Three");

        System.out.println("Size after adding elements: " + list.size());
        System.out.println("Capacity after adding elements: " + list.getCapacity());

        list.remove(1);
        System.out.println("Size after removing element: " + list.size());

        list.put(1, "New Three");
        System.out.println("Element at index 1: " + list.get(1));

        System.out.println("Contains 'One': " + list.contains("One"));
        System.out.println("Contains 'Two': " + list.contains("Two"));

        System.out.println("Final list size: " + list.size());
        System.out.println("Final capacity: " + list.getCapacity());
    }
}
