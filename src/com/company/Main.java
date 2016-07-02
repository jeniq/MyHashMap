package com.company;

public class Main {

    public static void main(String[] args) {
	    MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();

        System.out.println("Capacity: " + hashMap.getCapacity());

        hashMap.put(111, 20);
        hashMap.put(222, 10);
        hashMap.put(333, 1230);
        hashMap.put(444, 10);
        hashMap.put(555, 234);
        hashMap.put(666, 12);
        hashMap.put(777, 20);
        hashMap.put(888, 20);
        hashMap.put(999, 20);

        hashMap.put(14, 10);
        hashMap.put(142, 1230);
        hashMap.put(14, 10); // exist
        hashMap.put(2, 234);
        hashMap.put(22, 12);
        hashMap.put(87, 21);

        System.out.println("Size: " + hashMap.size());
        System.out.println("Capacity: " + hashMap.getCapacity());

        System.out.println(hashMap.get(14));
        System.out.println(hashMap.get(22));
        System.out.println(hashMap.get(54));

        hashMap.remove(2);
        hashMap.remove(142);
        System.out.println("Size: " + hashMap.size());
        System.out.println("Capacity: " + hashMap.getCapacity());

        System.out.println(hashMap.get(2));

        //MyHashMap<Integer, Integer> hashMap1 = new MyHashMap<>(-1);
    }
}
