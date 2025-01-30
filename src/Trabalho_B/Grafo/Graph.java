package Trabalho_B.Grafo;

import java.util.List;
import java.util.Map;

public interface Graph<T> {

    void loadCSV(String filePath);
    void addVertex(T vertex);
    void addEdge(T source, T destination);
    boolean hasVertex(T vertex);
    int countVertices();
    int countEdges();
    double calculateSparsity();
    List<T> BFSearch(T start, T end);
    Map<T, Integer> DijkstraTraversal(T start);
    Map<T, Integer> degreeCentrality();
    Map<T, Double> closenessCentrality();
    Map<T, Double> betweennessCentrality();
}