package com.hjk.custom.utils.algorithms.etc.search;


import lombok.Getter;

import java.util.*;

public class BreadthFirstSearch<E> {

    private final List<Node<E>> visited = new ArrayList<>();

    public static void main(String... args) {

    }

    public Optional<Node<E>> search(Node<E> node, E value) {
        if(node == null) {
            return Optional.empty();
        }

        if(node.getValue().equals(value)) {
            return Optional.of(node);
        }

        Queue<Node<E>> queue = new ArrayDeque<>(node.getChildren());

        while(!queue.isEmpty()) {
            final Node<E> current = queue.poll();

            if(current.getValue().equals(value)) {
                return Optional.of(current);
            }
            queue.addAll(current.getChildren());
        }


        return Optional.empty();
    }

    @Getter
    public static class Node<E> {
        private final E value;
        private final List<Node<E>> children;

        public Node(final E value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public void add(Node<E> node) {
            children.add(node);
        }
    }
}

