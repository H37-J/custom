package com.hjk.custom.utils.algorithms.etc.datastructure;

import lombok.Getter;

public class Edge<E> {

    private final E from;
    private final E to;
    @Getter
    private final int weight;

    public Edge(E from, E to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}
