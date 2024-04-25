package com.hjk.custom.utils.algorithms.programmers;

import java.util.*;

import static com.hjk.custom.utils.algorithms.programmers.Utils.*;

public class Algos {

    public static void main(String... args) {
        long startTime = System.currentTimeMillis();
        var node = new Node<Integer>(1);
        var newNode1 = new Node<Integer>(2);
        var newNode2 = new Node<Integer>(3);
        newNode1.setChild(Arrays.asList(new Node<Integer>(4), new Node<Integer>(5)));
        node.addChild(newNode1);
        node.addChild(newNode2);
//        bfsPrint(node);
        dfsPrint(node);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + "ms");
    }

    public static <T> Optional<Node<T>> bfs(Node<T> node, Integer value) {
        var queue = new LinkedList<Node<T>>(node.child);
        if(node.value == value) {
           return Optional.of(node);
        }
        while(!queue.isEmpty()) {
            var nextNode = queue.poll();
            if(nextNode.value == value) {
                return Optional.of(nextNode);
            }
            queue.addAll(nextNode.child);
        }
        return Optional.empty();
    }

    public static <T> void bfsPrint(Node<T> node) {
        print(node.value);
        var queue = new LinkedList<>(node.getChild());
        while(!queue.isEmpty()) {
            var nextNode = queue.poll();
            print(nextNode.value);
            queue.addAll(nextNode.child);
        }
    }

    public static <T> void dfsPrint(final Node<T> node) {
        print(node.value);
        node.child.forEach(Algos::dfsPrint);
    }
}

class Node<T> {
    T value;
    List<Node<T>> child;

    public Node(T value) {
        this.value = value;
        child = new ArrayList<>();
    }

    public void addChild(Node<T> newNode) {
        child.add(newNode);
    }
    public void setChild(List<Node<T>> list) {
        child = list;
    }

    public List<Node<T>> getChild() {
        return child;
    }

    public String toString() {
        return value + ":" + child;
    }
}
