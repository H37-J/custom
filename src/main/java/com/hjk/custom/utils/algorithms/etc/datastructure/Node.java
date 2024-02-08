package com.hjk.custom.utils.algorithms.etc.datastructure;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Node<E> {

    E value;
    Node<E> next;

    public Node() {
    }

    public Node(E value) {
        this.value = value;
        next = null;
    }

    public String toString() {
        return "value:" + value;
    }
}
