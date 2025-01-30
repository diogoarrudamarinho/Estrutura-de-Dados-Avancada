package Trabalho_B;

import java.util.Collections;
import java.util.Map;

import Trabalho_B.Grafo.Implementation.GraphImplementation;

public class Experimento {
    public static void main(String[] args) {
        
        GraphImplementation<String> grafo = new GraphImplementation<>();
        grafo.setDirected(true);

        try {
            grafo.loadCSV("D:\\Faculdade\\ED2\\src\\Trabalho_B\\JC-202412-citibike-tripdata.csv");
        } catch (RuntimeException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
        }

        System.out.println("Número de vértices: " + grafo.countVertices());
        System.out.println("Número de arestas: " + grafo.countEdges());
        System.out.println("Esparsidade: " + grafo.calculateSparsity());

        Map<String, Integer> degreeCentrality = grafo.degreeCentrality();
        Map<String, Double> closenessCentrality = grafo.closenessCentrality();
        Map<String, Double> betweennessCentrality = grafo.betweennessCentrality();

        String maiorDegree = encontrarMaior(degreeCentrality);
        String maiorCloseness = encontrarMaior(closenessCentrality);
        String maiorBetweenness = encontrarMaior(betweennessCentrality);

        System.out.println("Maior Grau de Centralidade: " + maiorDegree);
        System.out.println("Maior Centralidade de Proximidade: " + maiorCloseness);
        System.out.println("Maior Centralidade de Intermediação: " + maiorBetweenness);
    }

    public static <K, V extends Comparable<V>> K encontrarMaior(Map<K, V> mapa) {
        return Collections.max(
                                mapa.entrySet(), 
                                Map.Entry.comparingByValue())
                                .getKey();
    }
}
