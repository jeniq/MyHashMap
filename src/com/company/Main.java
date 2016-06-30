package com.company;

public class Main {

    public static void main(String[] args) {
	    MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();

        hashMap.put(14, 10);
        hashMap.put(142, 1230);
        hashMap.put(14, 10);
        hashMap.put(2, 234);
        hashMap.put(22, 12);
        hashMap.put(54, 20);

        System.out.println(hashMap.size());
        System.out.println(hashMap.get(14));
        System.out.println(hashMap.get(22));
        System.out.println(hashMap.get(54));

        hashMap.remove(2);
        System.out.println(hashMap.size());
        System.out.println(hashMap.get(2));

    }
}
