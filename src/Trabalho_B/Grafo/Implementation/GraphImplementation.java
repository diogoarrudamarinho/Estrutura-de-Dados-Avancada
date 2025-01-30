package Trabalho_B.Grafo.Implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import Trabalho_B.Grafo.Graph;

public class GraphImplementation<T> implements Graph<T> {
    
    private final Map<T, List<T>> map;
    private boolean directed;

    public GraphImplementation() {
        this.map = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadCSV(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            //Ignora header
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                List<String> values = getRecordFromLine(scanner.nextLine());
                this.addEdge((T)values.get(4), (T)values.get(6));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        return values;
    }

    @Override
    public void addVertex(T vertex){
        map.put(vertex, new LinkedList<>());
    }

    @Override
    public void addEdge(T source, T destination){
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(destination))
            addVertex(destination);

        if(!map.get(source).contains(destination)) 
            map.get(source).add(destination);
            
        if (!this.directed) 
            if (!map.get(destination).contains(source)) 
                map.get(destination).add(source);              
    }

    @Override
    public int countVertices(){
        return map.keySet().size();
    }

    @Override
    public int countEdges(){
        
        int count = 0;

        for (T v : map.keySet()) 
            count += map.get(v).size();
        
        if (!this.directed) 
            count = count / 2;
        
        return count;
    }

    @Override
    public boolean hasVertex(T vertex){
        return map.containsKey(vertex);
    }

    public boolean hasEdge(T source, T destination){
        if (hasVertex(source))
            return map.get(source).contains(destination);
        return false;
    }

    @Override
    public double calculateSparsity() {
        int vertexCount = countVertices();
        int edgeCount = countEdges();
        int maxEdges = directed ? vertexCount * (vertexCount - 1) : 
                                vertexCount * (vertexCount - 1) / 2;

        return maxEdges == 0 ?  0.0 : 
                                (double) edgeCount / maxEdges; 
    }

    @Override
    public List<T> BFSearch(T start, T end) {

        if (!hasVertex(start) || !hasVertex(end)) 
            return null;

        Map<T, T> previous = new HashMap<>();
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();

        previous.put(start, null);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();

            if (current.equals(end)) 
                return constructPath(previous, end);

            for (T neighbor : map.get(current)) 
            {
                if (!visited.contains(neighbor)) 
                {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<T> constructPath(Map<T, T> previous, T end) {

        List<T> path = new LinkedList<>();
        
        if (!previous.containsKey(end)) 
            return new ArrayList<>();
    
        for (T vertex = end; vertex != null; vertex = previous.get(vertex)) 
            path.add(vertex);
        
        Collections.reverse(path);
        return path;
    }

    @Override
    public Map<T, Integer> DijkstraTraversal(T start) {

        if (!hasVertex(start)) 
            return new HashMap<>();
    
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<T> processed = new HashSet<>();
    
        for (T vertex : map.keySet()) 
            distances.put(vertex, Integer.MAX_VALUE);
        
        distances.put(start, 0);
        queue.add(start);
    
        while (!queue.isEmpty()) {

            T current = queue.poll();
    
            if (processed.contains(current)) 
                continue; 
            
            processed.add(current);
    
            for (T neighbor : map.get(current)) 
            {
                int weight = 1; 
                int newDist = distances.get(current) + weight;
    
                if (newDist < distances.get(neighbor)) 
                {
                    distances.put(neighbor, newDist);
                    queue.add(neighbor); 
                }
            }
        }
    
        return distances;
    }

    @Override
    public Map<T, Integer> degreeCentrality() {

        Map<T, Integer> degreeMap = new HashMap<>();

        for (T vertex : map.keySet()) 
            degreeMap.put(vertex, map.get(vertex).size());
        
        return degreeMap;
    }

    @Override
    public Map<T, Double> closenessCentrality() {

        Map<T, Double> closenessMap = new HashMap<>();

        for (T vertex : map.keySet()) 
        {
            Map<T, Integer> distances = DijkstraTraversal(vertex);
            int totalDistance = 0;
            int reachableNodes = 0;
    
            for (Map.Entry<T, Integer> entry : distances.entrySet()) 
            {
                if (entry.getKey().equals(vertex)) continue; 

                if (entry.getValue() != Integer.MAX_VALUE) 
                {
                    totalDistance += entry.getValue();
                    reachableNodes++;
                }
            }
    
            if (reachableNodes == 0 || totalDistance == 0) 
                closenessMap.put(vertex, 0.0);
            else 
                closenessMap.put(vertex, (double) reachableNodes / totalDistance);
        }

        return closenessMap;
    }

    @Override
    public Map<T, Double> betweennessCentrality() {
        Map<T, Double> centrality = new HashMap<>();

        for (T vertex : map.keySet()) 
            centrality.put(vertex, 0.0);

        for (T source : map.keySet()) 
        {
            for (T target : map.keySet()) 
            {
                if (!source.equals(target)) 
                {
                    List<T> shortestPath = BFSearch(source, target);
                    if (shortestPath.size() > 2) 
                    { 
                        for (int i = 1; i < shortestPath.size() - 1; i++)
                        {
                            T intermediate = shortestPath.get(i);
                            centrality.put(intermediate, centrality.get(intermediate) + 1.0);
                        }
                    }
                }
            }
        }
        return centrality;
    }
    
    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

}
