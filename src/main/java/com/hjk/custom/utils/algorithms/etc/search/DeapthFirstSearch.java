package com.hjk.custom.utils.algorithms.etc.search;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeapthFirstSearch<E> {

    public static void main(String... args) {

    }

    public Optional<Node<E>> search(Node<E> node, E value)  {
        if(node == null) {
            return Optional.empty();
        }

        if(node.getValue().equals(value)) {
            return Optional.of(node);
        }

        return node.getChildren().stream()
                .map(v -> search(v, value))
                .flatMap(Optional::stream)
                .findAny();
    }


    @Getter
    public static class Node<E> {
        private final E value;
        private final List<Node<E>> children;

        public Node(E value) {
            this.value = value;
            children = new ArrayList<>();
        }

        public void add(Node<E> node) {
            children.add(node);
        }
    }
}
