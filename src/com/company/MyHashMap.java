package com.company;

public class MyHashMap<K, V> {
    private Entry<K, V>[] table;
    private int capacity = 16;
    private double loadFactor = 0.75;

    class Entry<K, V>{
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap(){
        table = new Entry[capacity];
    }

    public MyHashMap(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public void put(K newKey, V data){
        if (newKey == null){
            return;
        }
        if ((double)size()/capacity > loadFactor){ // set new capacity
            growCapacity(table, (int)(capacity*loadFactor));
        }
        int hash = hash(newKey); // position in table
        Entry<K, V> newEntry = new Entry<K,V>(newKey, data, null);

        if (table[hash] == null){
            table[hash] = newEntry;
        }else{
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null){
                if (current.key.equals(newKey)){
                    if (previous == null){
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return;
                    }else{
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
        int hash = hash(key);
        if (table[hash] == null){
            return null;
        }else{
            Entry<K, V> temp = table[hash];
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
        int hash = hash(deleteKey);

        if (table[hash] == null){
            return false;
        }else{
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null){
                if (current.key.equals(deleteKey)){
                    if (previous == null){
                        table[hash] = table[hash].next;
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

    private int hash(K key){
        return 31 * key.hashCode() % capacity;
    }

    private int size(){
        int size = 0;
        for (Entry e : table){
            if (e != null){
                size++;
            }
        }
        return size;
    }

    private void growCapacity(Entry[] table, int capacity){
        Entry<K, V>[] temp = table;
        table = new Entry[capacity];
        System.arraycopy(temp, 0, table, 0, temp.length);
    }
}
