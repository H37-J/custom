package com.hjk.custom.utils.algorithms.etc.search;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BinaryNode<E extends Comparable<E>> {

    public static void main(String... args) {
        var binaryNode = new BinaryNode<Integer>(2);
        binaryNode.root.add(binaryNode.root, 1);
        binaryNode.root.add(binaryNode.root, 3);
        binaryNode.postPrint(binaryNode.root);
    }

    Node<E> root;


    public BinaryNode(E value) {
        this.root = new Node<E>(value);
    }

    public void prePrint(Node<E> root) {
        System.out.println(root.value);
        if (root.getLeftChild() != null) {
            prePrint(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            prePrint(root.getRightChild());
        }
    }

    public void inPrint(Node<E> root) {
        if (root.getLeftChild() != null) {
            inPrint(root.getLeftChild());
        }
        System.out.println(root.value);
        if (root.getRightChild() != null) {
            inPrint(root.getRightChild());
        }
    }

    public void postPrint(Node<E> root) {
        if (root.getLeftChild() != null) {
            postPrint(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            postPrint(root.getRightChild());
        }
        System.out.println(root.value);
    }

    @Getter
    @Setter
    public static class Node<E extends Comparable<E>> {
        E value;
        Node<E> parent;
        Node<E> leftChild;
        Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public void add(Node<E> root, E value) {
            if (root.getLeftChild() != null && root.value.compareTo(value) > 0) {
                add(root.getLeftChild(), value);
            } else if (root.getRightChild() != null && root.value.compareTo(value) < 0) {
                add(root.getRightChild(), value);
            }
            if (root.getLeftChild() == null && root.value.compareTo(value) > 0) {
                root.setLeftChild(new Node<E>(value));
                root.getLeftChild().setParent(root);
            } else if (root.getRightChild() == null && root.value.compareTo(value) < 0) {
                root.setRightChild(new Node<E>(value));
                root.getRightChild().setParent(root);
            }
        }

        public void addParent(E value) {
            var node = new Node<E>(value);
            this.setParent(node);
        }

        public String toString() {
            return this.value + "," + this.getLeftChild().value + "," + this.getRightChild().value;
        }

    }
}

