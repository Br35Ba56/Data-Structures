package datastructures;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;

import java.util.List;


public class Graph<T> {

    private ArrayList<Vertex<T>> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    public boolean addVertex(Vertex<T> vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        return true;
    }

    public int size() {
        return vertices.size();
    }

    public Vertex<T> getVertex(int index) {
        return vertices.get(index);
    }

    public ArrayList<Vertex<T>> getVertices() {
        return this.vertices;
    }

    public boolean addEdge(Vertex<T> from, Vertex<T> to) {
        if (vertices.contains(from)) {
            vertices.get(vertices.indexOf(from)).addEdge(to);
        }
        return false;
    }

    //Finds a path using a modified Breadth First Search
    public boolean isPath(Vertex<T> from, Vertex<T> to) {
        if (from == to) {
            return true;
        }
        if (vertices.contains(from) && vertices.contains(to)) {
            ArrayDeque<Vertex<T>> queue = new ArrayDeque<>();
            queue.add(from);
            while(!queue.isEmpty()) {
                queue.getFirst().visited = true;
                System.out.println(queue.getFirst());
                for (int i = 0; i < queue.getFirst().adjacencyList.size(); i++ ) {
                    System.out.println(queue.getFirst().adjacencyList);
                    if (queue.getFirst().adjacencyList.get(i) == to) {
                        resetVisited();
                        return true;
                    }
                    if (queue.getFirst().adjacencyList.get(i).visited != true) {
                        queue.addLast(queue.getFirst().adjacencyList.get(i));
                    }
                }
                queue.removeFirst();
            }
        }
        resetVisited();
        return false;
    }

    private void resetVisited() {
        for (Vertex<T> vertex : vertices) {
            vertex.visited = false;
        }
    }

    static class Vertex<T> {

        private T element;
        private List<Vertex<T>> adjacencyList;
        private boolean visited = false;

        public Vertex(T element){
            this.element = element;
            this.adjacencyList = new ArrayList<>();
        }

        private void addEdge(Vertex<T> edge){
            adjacencyList.add(edge);
        }

        @Override
        public String toString() {
            return element.toString();
        }

        @Override
        public boolean equals(Object object) {
            return object.equals(element);
        }
    }

    public static void main(String... args) {
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>( "B");
        Vertex<String> c = new Vertex<>( "C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");
        Vertex<String> f = new Vertex<>("F");
        Vertex<String> g = new Vertex<>( "G");

        Graph<String> graph = new Graph<>();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);

        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, g);
        graph.addEdge(d, f);
        graph.addEdge(f, d);
        graph.addEdge(d, e);
        graph.addEdge(c, f);
        graph.addEdge(c, g);

        System.out.println("D -> C " + graph.isPath(d, c));
        System.out.println("C -> D " + graph.isPath(c, d));
        System.out.println("A -> B " + graph.isPath(a, b));
        System.out.println("A -> C " + graph.isPath(a, c));
        System.out.println("A -> G " + graph.isPath(a, g));
        System.out.println("A -> F " + graph.isPath(a, f));
        System.out.println("A -> D " + graph.isPath(a, d));
        System.out.println("C -> G " + graph.isPath(c, g));
        System.out.println("B -> A " + graph.isPath(b, a));

    }
}
