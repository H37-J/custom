package com.hjk.custom.utils.algorithms;

import java.util.*;

public class DataStructures {
    public static <T> void main(String... args) {
//        var node = new Node<>(1);
//        var node1 = new Node<>(2);
//        var node2 = new Node<>(3);
//        node1.setNext(node2);
//
//        node.setNext(node1);
//
//        System.out.println(node);

//        var node = new NodeList<Integer>(1);
//        node.add(2);
//        node.add(3);
//        node.insert(1, 4);
//        node.print();

        var tree = new Tree<Integer>(1);

//        tree.setLeft(2);
//        tree.left.setLeft(4);
//        tree.left.setRight(5);
//        tree.setRight(3);
//        tree.right.setLeft(6);
//        tree.right.setRight(7);
//        tree.bfSearch();

        var queue = new QueueNode<>();
        queue.enqueue(1);
        queue.enqueue(2);
//        queue.print();

        var graphs = new Graphs<Integer>();
        graphs.addEdge(1, 2);
        graphs.addEdge(1, 3);
        var arr = new int[]{1, 3, 5, 4, 2};
        var arr2 = new int[]{1, 3, 5};

        var temp = Arrays.stream(arr).filter(val -> val % 2 == 0).toArray();
        var temp1 = Arrays.stream(arr).filter(val1 -> {
            for (int i = 0; i < arr2.length; i++) {
                if (val1 == arr[i]) {
                    return true;
                }
            }
            return false;
        }).toArray();

        var ranking = Arrays.stream(arr).map(val1 -> {
            int count = 1;
            for (var val2 : arr) {
                if (val1 < val2) {
                    count += 1;
                }
            }
            return count;
        }).toArray();

        var user = User.builder().name("hjk").age(10);

        var el1 = new HeapElement(2, 1);
        var el2 = new HeapElement(3, 2);
        var el3 = new HeapElement(1, 3);
        ArrayList<HeapElement> list = new ArrayList<>();
        list.add(el1);
        list.add(el2);
        list.add(el3);

        var heap = new Heap<Integer>();
        heap.add(2);
        heap.add(3);
        heap.add(1);

        heap.print();
        heap.remove();
        heap.print();

        var str = "test";
        var ar1 = new int[]{1, 2};
        var ar2 = Arrays.copyOfRange(arr, 0,2);


        var li = new ArrayList<>();
        li.add(0);
        li.add(1);
        li.add(1);

        li.forEach(l -> {
            var index = li.indexOf(l);
        });
    }
}

class User {
    String name;
    Integer age;

    public User() {

    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User age(Integer age) {
        this.age = age;
        return this;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public static User builder() {
        return new User();
    }


}

class Node<T> {
    T value;
    Node<T> next;

    public Node() {

    }

    public Node(T value) {
        this.value = value;
    }

    public String toString() {
        return "value:" + this.value +
                ", next=>" + this.next;
    }
}

class NodeList<T> implements Iterable<T> {
    Node<T> head;

    Node<T> tail;
    int size;

    public NodeList(T value) {
        head = new Node<T>(value);
        tail = this.head;
        size = 1;
    }

    public void add(T value) {
        var newNode = new Node<T>(value);
        if (this.tail != null) {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }

    public void prepend(T value) {
        var newNode = new Node<T>(value);
        if (this.head != null) {
            newNode.next = this.head.next;
            this.head = newNode;
        } else {
            this.head = newNode;
            this.tail = newNode;
        }
    }

    public void insert(int index, T value) {
        if (index == 0) {
            this.prepend(value);
        }
        var newNode = new Node<T>(value);
        var currentNode = this.head;
        for (int i = 1; i < index; i++) {
            currentNode = currentNode.next;
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
    }

    public Node<T> find(T value) {
        var currentNode = this.head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            if (currentNode.value == value) {
                return currentNode;
            }
        }
        return null;
    }

    public String toString() {
        var builder = new StringBuilder();
        var currentNode = this.head;
        builder.append(currentNode.value);
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            builder.append(currentNode.value);
        }
        return builder.toString();
    }

    public void print() {
        for (var element : this) {
            System.out.println(element);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeListIterator();
    }

    private class NodeListIterator implements Iterator<T> {
        private Node<T> current;

        NodeListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final var value = current.value;
            current = current.next;
            return value;
        }


    }
}


class Tree<T> {
    T data;
    Tree<T> parent;
    Tree<T> left;
    Tree<T> right;

    public Tree(T data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public void setLeft(T value) {
        var newTree = new Tree<T>(value);
        this.left = newTree;
        newTree.parent = this;
    }

    public void setRight(T value) {
        var newTree = new Tree<T>(value);
        this.right = newTree;
        newTree.parent = this;
    }

    public void bfSearch() {

        Queue<Tree<T>> queue = new LinkedList<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            var currentTree = queue.poll();

            System.out.println(currentTree.data);

            if (currentTree.left != null) {
                queue.offer(currentTree.left);
            }

            if (currentTree.right != null) {
                queue.offer(currentTree.right);
            }
        }
    }
}

class QueueNode<T> {

    Node<T> front;


    public QueueNode() {
        front = new Node<>();
    }

    public void enqueue(T data) {
        var newNode = new Node<>(data);
        if (front == null) {
            front = newNode;
        } else {
            while (front.next != null) {
                front = front.next;
            }
            front.next = newNode;
        }
    }

    public void dequeue() {
        front = front.next;
    }

    public void print() {
        var currentNode = this.front;
        while ((currentNode = currentNode.next) != null) {
            System.out.println(currentNode.value);
        }
    }
}

class Vertex<E extends Comparable<E>> {

    E data;
    ArrayList<Vertex<E>> adjacentVertices;

    public Vertex(E data) {
        this.data = data;
        this.adjacentVertices = new ArrayList<>();
    }

    public boolean addAdjacentVertex(Vertex<E> to) {
        for (Vertex<E> v : adjacentVertices) {
            if (v.data.compareTo(to.data) == 0) {
                return false;
            }
        }
        return adjacentVertices.add(to);
    }

    public boolean removeAdjacentVertex(E to) {
        for (int i = 0; i < adjacentVertices.size(); i++) {
            if (adjacentVertices.get(i).data.compareTo(to) == 0) {
                adjacentVertices.remove(i);
                return true;
            }
        }
        return false;
    }
}

class Graphs<E extends Comparable<E>> {
    ArrayList<Vertex<E>> vertices;

    public Graphs() {
        vertices = new ArrayList<>();
    }

    public Boolean addEdge(E from, E to) {
        Vertex<E> fromV = null;
        Vertex<E> toV = null;
        for (var vertex : vertices) {
            if (vertex.data.compareTo(from) == 0) {
                fromV = vertex;
            }
            if (vertex.data.compareTo(to) == 0) {
                toV = vertex;
            }
            if (fromV != null && toV != null) {
                break;
            }
        }

        if (fromV == null) {
            fromV = new Vertex<E>(from);
            vertices.add(fromV);
        }

        if (toV == null) {
            toV = new Vertex<E>(to);
            vertices.add(toV);
        }
        return fromV.addAdjacentVertex(toV);
    }

    public boolean removeEdge(E from, E to) {
        Vertex<E> fromV = null;
        for (var v : vertices) {
            if (from.compareTo(v.data) == 0) {
                fromV = v;
                break;
            }
        }

        if (fromV == null) {
            return false;
        }
        return fromV.removeAdjacentVertex(to);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent vertices: ");
            for (var v2 : v.adjacentVertices) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

class UnionNode<T> {
    public int rank;
    public UnionNode<T> parent;
    public T data;

    public UnionNode(final T data) {
        this.data = data;
        parent = this;
    }
}

class Union<T> {
    public UnionNode<T> makeSet(final T data) {
        return new UnionNode<T>(data);
    }

    public UnionNode<T> findSet(UnionNode<T> node) {
        while(node != node.parent) {
            node = node.parent;
        }
        return node;
    }

    public void unionSets(final UnionNode<T> x, final UnionNode<T> y) {
        UnionNode<T> node1 = findSet(x);
        UnionNode<T> node2 = findSet(y);

        if(node1 == node2) {
            return;
        }

        if(node1.rank > node2.rank) {
            node1.parent = node2;
        } else {
            node2.parent = node1;
        }
        node2.parent = node1;
        node1.rank++;
    }
}

class HeapElement {
    final double key;
    final Object element;

    public HeapElement(double key, Object element) {
        this.key = key;
        this.element = element;
    }

    public Double getKey() {
        return this.key;
    }
}

class MinHeap {
    private final List<HeapElement> minHeap;

    public MinHeap(List<HeapElement> listElements) {
        minHeap = new ArrayList<>();
        for(var element : listElements) {
            insert(element);
        }
    }

    public void insert(HeapElement element) {
        minHeap.add(element);
        toggleUp(minHeap.size());
    }

    public void toggleUp(int index) {
        double key = minHeap.get(index - 1).getKey();
        System.out.println(getElementKey((int) Math.floor(index / 2.0) + 1) + "," + key);
        while(getElementKey((int) Math.floor(index / 2.0) + 1) > key) {
            swap(index, (int) Math.floor(index / 2.0));
            index = (int) Math.floor(index / 2.0);
        }
    }

    private double getElementKey(int index) {
        if(index <= 0 || index > minHeap.size()) {
            throw new IndexOutOfBoundsException("Index out");
        }
        return minHeap.get(index - 1).getKey();
    }

    private void swap(int index1, int index2) {
        HeapElement element = minHeap.get(index1 - 1);
        minHeap.set(index1 - 1, minHeap.get(index2 - 1));
        minHeap.set(index2, element);
    }

    public void print() {
        minHeap.forEach(element -> System.out.println(element.element));
    }
}

class Heap<T extends Comparable<T>> {
    ArrayList<T> data = new ArrayList<>();
    HashMap<T, Integer> map = new HashMap<>();

    public void add(T item) {
        this.data.add(item);
        map.put(item, this.data.size() - 1);
        upHeapify(this.data.size() - 1);
    }

    public int size() {
        return this.data.size();
    }

    public T remove() {
        this.swap(0, this.size() - 1);
        this.print();
        T rv = this.data.remove(this.size() - 1);
        map.remove(rv);
        return rv;
    }

    private void downHeapify(int pi) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int mini = pi;
        if (lci < this.size() && isLarger(this.data.get(lci), this.data.get(mini)) < 0) {
            mini = lci;
        }
        if (rci < this.size() && isLarger(this.data.get(rci), this.data.get(mini)) < 0) {
            mini = rci;
        }
        if (mini != pi) {
            this.swap(pi, mini);
            downHeapify(mini);
        }
    }

    private void upHeapify(int child) {
        int parent = (child - 1) / 2;
        if(isLarger(this.data.get(child), this.data.get(parent)) < 0) {
            swap(parent, child);
            upHeapify(child);
        }
    }

    public void swap(int index1, int index2) {
        T data1 = this.data.get(index1);
        T data2 = this.data.get(index2);
        this.data.set(index1, data2);
        this.data.set(index2, data1);
        map.put(data1, index2);
        map.put(data2, index1);
    }

    private int isLarger(T val1, T val2) {
        return val1.compareTo(val2);
    }

    public void print() {
        var join = new StringJoiner(", ");
        data.forEach(val -> {
            join.add(String.valueOf(val));
        });
        System.out.println(join.toString());
    }

    public void print(T t)  {
        System.out.println(t);
    }

}