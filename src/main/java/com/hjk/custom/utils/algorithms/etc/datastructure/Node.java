package com.hjk.custom.utils.algorithms.etc.datastructure;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Node<T> {

    private final T value;

    private final List<Node<T>> children;

    public Node(final T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(Node<T> node) {
        children.add(node);
    }

}
