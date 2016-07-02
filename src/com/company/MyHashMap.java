package com.company;

public class MyHashMap<K, V> {
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private Entry<K, V>[] table;
    private int capacity = 16;
    private float loadFactor = 0.75f;

    class Entry<K, V>{
        private K key;
        private V value;
        private int hash;
        private Entry<K, V> next;

        public Entry(K key, V value, int hash, Entry<K, V> next){
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

    }

    public MyHashMap(){
        table = new Entry[capacity];
    }

    public MyHashMap(int capacity){
        if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY){
            throw new IllegalArgumentException("Wrong capacity!");
        }
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public void put(K newKey, V data){
        if (newKey == null){
            return;
        }
        if ((float)size()/capacity > loadFactor){ // set new capacity
            growCapacity();
        }
        int hash = hash(newKey);
        int index = hash % capacity; // position in table
        Entry<K, V> newEntry = new Entry<K,V>(newKey, data, hash, null);

        if (table[index] == null){
            table[index] = newEntry;
        }else{
            Entry<K, V> previous = null;
            Entry<K, V> current = table[index];

            while (current != null){
                if (current.key.equals(newKey)){
                    if (previous == null){ // if first element in bucket
                        newEntry.next = current.next;
                        table[index] = newEntry;
                        return;
                    }else{ // override existing same key
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    public V get(K key){
        int index = hash(key) % capacity;
        if (table[index] == null){
            return null;
        }else{
            Entry<K, V> temp = table[index];
            while (temp != null){
                if (temp.key.equals(key)){
                    return temp.value;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    public boolean remove(K deleteKey){
        int index = hash(deleteKey) % capacity;

        if (table[index] == null){
            return false;
        }else{
            Entry<K, V> previous = null;
            Entry<K, V> current = table[index];

            while (current != null){
                if (current.key.equals(deleteKey)){
                    if (previous == null){
                        table[index] = table[index].next;
                        return true;
                    }else{
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }
    }

    public int size(){
        int size = 0;
        for (Entry<K, V> e : table){
            if (e != null){
                size++;
                Entry<K, V> current = e.next;
                while (current != null){
                    current = current.next;
                    size++;
                }
            }
        }
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    private int hash(K key){
        return 31 * key.hashCode();
    }

    private void growCapacity(){
        Entry<K, V>[] temp = table;
        capacity = capacity + (int)(capacity*loadFactor);
        System.out.println("Capacity grew");
        table = new Entry[capacity];

        for (Entry<K, V> e : temp){
            if (e != null) {
                Entry<K, V> tempEntry = e.next;
                put(e.key, e.value);
                while (tempEntry != null) {
                    put(tempEntry.key, tempEntry.value);
                    tempEntry = tempEntry.next;
                }
            }
        }

    }
}
// TODO: variable size