package com.hjk.custom.utils.algorithms.etc.datastructure;

public class HashMap<E> {

    private final int size;

    private final LinkedList<E>[] map;

    public HashMap(int size) {
        map = new LinkedList[size];
        for(int i = 0; i < size; i++) {
            map[i] = new LinkedList<E>();
        }
        this.size = size;
    }

    public int hashing(int key) {
        int hash = key % size;
        if(hash < 0) {
            hash += size;
        }
        return hash;
    }

    public void insertHash(int key, E value) {
        int hash = hashing(key);
        map[hash].insert(value);
    }

    public void deleteHash(int key, E value) {
        int hash = hashing(key);
        map[hash].delete(value);
    }

    public void print() {
        for(int i = 0; i < size; i++) {
            map[i].print();
        }
    }
}
