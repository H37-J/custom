package com.hjk.custom.utils.algorithms.etc.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Vertex<E extends Comparable<E>> {

    E data;
    List<Vertex<E>> adjacentVertices;

    public Vertex(E data) {
        this.data = data;
        adjacentVertices = new ArrayList<>();
    }

    public boolean addAdjacentVertex(Vertex<E> to) {
        for(var vertex : adjacentVertices) {
            if(vertex.data.compareTo(to.data) == 0) {
                return false;
            }
        }
        return adjacentVertices.add(to);
    }

    public boolean removeAdjacentVertex(Vertex<E> to) {
        for(var vertex : adjacentVertices) {
            if(vertex.data.compareTo(to.data) == 0) {
                return adjacentVertices.remove(to);
            }
        }
        return false;
    }
}
