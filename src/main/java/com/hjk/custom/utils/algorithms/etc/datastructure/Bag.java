package com.hjk.custom.utils.algorithms.etc.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<E> implements Iterable<E> {

    public static void main(String... args) {
        var bag = new Bag<Integer>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.print();
    }

    private Node<E> firstElement;

    private int size;

    public Bag() {
        firstElement = null;
        size = 0;
    }

    public boolean isEmpty() {
        return firstElement == null;
    }

    public int size() {
        return this.size;
    }

    public void add(E element) {
        var oldNode = firstElement;
        firstElement = new Node<E>(element);
        firstElement.next = oldNode;
        size++;
    }

    public void print() {
        for (var node : this) {
            System.out.println(node);
        }
    }

    public Iterator<E> iterator() {
        return new ListIterator<>(firstElement);
    }

    private class ListIterator<E> implements Iterator<E> {

        private Node<E> currentNode;

        public ListIterator(Node<E> firstElement) {
            currentNode = firstElement;
        }

        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = currentNode.value;
            currentNode = currentNode.next;
            return value;
        }
    }
}
