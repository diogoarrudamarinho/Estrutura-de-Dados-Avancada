package Trabalho_B.Implementation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphImplementation<T> {
    
    private Map<T, List<T>> map;
    private boolean directed;

    public GraphImplementation() {
        this.directed = false;
        this.map = new HashMap<>();
    }

    public void addVertex(T vertex){
        map.put(vertex, new LinkedList<T>());
    }

    public void addEdge(T source, T destination){
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(destination))
            addVertex(destination);

        map.get(source).add(destination);

        if (!this.directed) 
            map.get(destination).add(source); 
    }

    public int getVertexCount(){
        return map.keySet().size();
    }

    public int getEdgesCount(){
        
        int count = 0;

        for (T v : map.keySet()) 
            count += map.get(v).size();
        
        if (!this.directed) 
            count = count / 2;
        
        return count;
    }

    public boolean hasVertex(T vertex){
        return map.containsKey(vertex);
    }

    public boolean hasEdge(T source, T destination){
        if (hasVertex(source))
            return map.get(source).contains(destination);
        return false;
    }

}
