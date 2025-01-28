package Trabalho_A;

import Trabalho_A.Arvores.ArvAVL;
import Trabalho_A.Arvores.ArvBinBusca;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExperimentoEmpirico {
    public static void main(String[] args) {

        int experimentos = 100; 
        String fileName = "Experimento_Altura_Empirica.csv"; 
        String resumoFileName = "Resumo_Altura_Empirica.csv";
        
        try (FileWriter writer = new FileWriter(fileName);
            FileWriter resumoWriter = new FileWriter(resumoFileName))
        {
            writer.write("Tipo;Qtd Chaves;Altura;Altura Esperada\n");
            resumoWriter.write("Tipo;Media;Desvio Padrao\n");
            
            experimentoAVL(writer, experimentos, resumoWriter);
            experimentoBST(writer, experimentos, resumoWriter);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } 
    }

    private static void experimentoAVL(FileWriter writer, int experimentos, FileWriter resumoWriter) throws IOException {
        
        for(int i = 0; i < experimentos; i++)
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 2)),
                                         (int) (Math.pow(10,4))); 

            int alturaEsperadaAVL = ArvAVL.alturaMedia(qtdChaves);

            List<Integer> alturas = new ArrayList<>();

            for (int k = 0; k < experimentos; k++) 
            {
                ArvAVL<Integer> arvAVL = new ArvAVL<>();

                for (int j = 0; j < qtdChaves; j++)
                    arvAVL.insere(new Random()
                                    .nextInt(1, qtdChaves * 1000),
                                            j);
            
                writer.write(String.format("AVL;%d;%d;%d\n", 
                                    qtdChaves, arvAVL.getAltura(), alturaEsperadaAVL));

                alturas.add(arvAVL.getAltura());
            }
            
            double media = calculaMedia(alturas);
            double desvioPadrao = calculaDesvioPadrao(alturas, media);

            resumoWriter.write(String.format("AVL;%f;%f\n", media, desvioPadrao));
        }
    }

    private static void experimentoBST(FileWriter writer, int experimentos, FileWriter resumoWriter) throws IOException {

        for(int i = 0; i < 100; i++)
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 2)),
                                         (int) (Math.pow(10, 4)));  

            int alturaEsperadaBST = ArvBinBusca.alturaMedia(qtdChaves); 
            List<Integer> alturas = new ArrayList<>();

            for (int k = 0; k < experimentos; k++) 
            {
                ArvBinBusca<Integer> arvBusca = new ArvBinBusca<>();

                for (int j = 0; j < qtdChaves; j++)
                    arvBusca.insere(new Random()
                                    .nextInt(1, qtdChaves * 1000),
                                        j);
                
                writer.write(String.format("BST;%d;%d;%d\n", qtdChaves, arvBusca.getAltura(), alturaEsperadaBST));

                alturas.add(arvBusca.getAltura());
            }
            
            double media = calculaMedia(alturas);
            double desvioPadrao = calculaDesvioPadrao(alturas, media);

            resumoWriter.write(String.format("BST;%f;%f\n", media, desvioPadrao));
        }
    }

    private static double calculaMedia(List<Integer> valores) {
        double soma = 0.0;
        
        for (int valor : valores) 
            soma += valor;
        
        return soma / valores.size();
    }
    
    private static double calculaDesvioPadrao(List<Integer> valores, double media) {
        double somaQuadrados = 0.0;
        for (int valor : valores) {
            somaQuadrados += Math.pow(valor - media, 2);
        }
        return Math.sqrt(somaQuadrados / valores.size());
    }
}
