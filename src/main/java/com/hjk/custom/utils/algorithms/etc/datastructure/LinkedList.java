package com.hjk.custom.utils.algorithms.etc.datastructure;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<E> {

    public static void main(String... args) {
        var list = new LinkedList<Integer>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(2);
        list.print();
    }

    public Node<E> first;

    public LinkedList() {
        first = null;
    }

    public void insert(E value) {
        if (isEmpty()) {
            first = new Node<E>(value);
        } else {
            var end = findEnd();
            end.next = new Node<E>(value);
        }
    }

    public void delete(E value) {
        if (first.value == value) {
            first = first.next;
        }
        var current = first;
        while (current.next != null) {
            if (current.next.value == value) {
                var newNode = current.next.next;
                current.next.next = null;
                current.next = newNode;
                return;
            } else {
                current = current.next;
            }
        }
    }

    public void deleteAll(E value) {
        while (findValue(value) != null) {
            delete(value);
        }
    }

    public void clear() {
        if (first.next == null) {
            first = null;
            return;
        }
        while (first.next != null) {
            var destory = first;
            first = first.next;
            destory = null;
        }
        first = null;
    }

    public List<Integer> findIndex(E value) {
        int count = 0;
        var current = first;
        List<Integer> list = new ArrayList<>();
        if (current.value == value) {
            list.add(count);
        }
        while (current.next != null) {
            current = current.next;
            count++;
            if (current.value == value) {
                list.add(count);
            }
        }
        return list;
    }

    public Node<E> findValue(E value) {
        var current = first;
        if (current.value == value) {
            return first;
        } else {
            while (current.next != null) {
                current = current.next;
                if (current.value == value) {
                    return current;
                }
            }
        }
        return null;
    }

    public Node<E> findEnd() {
        var current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }


    public boolean isEmpty() {
        return first == null;
    }

    public void print() {
        print(first);
    }

    public void print(Node<E> node) {
        if (node == null) {
            return;
        } else {
            System.out.println(node.value);
            print(node.next);
        }
    }
}
