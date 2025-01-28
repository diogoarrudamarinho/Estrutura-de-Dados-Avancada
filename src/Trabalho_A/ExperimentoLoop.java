package Trabalho_A;
import Trabalho_A.Arvores.ArvAVL;
import Trabalho_A.Arvores.ArvBinBusca;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExperimentoLoop {
    public static void main(String[] args) {

        String fileName = "Experimento_Altura_Sucessor.csv"; 
        String resumoFileName = "Resumo_Altura_Sucessor.csv";

        try (FileWriter writer = new FileWriter(fileName);
            FileWriter resumoWriter = new FileWriter(resumoFileName)) 
        {
            writer.write("Tipo_Sucessor;Qtd Chaves;Altura;Altura Esperada\n");
            resumoWriter.write("Tipo_Sucessor;Media;Desvio Padrao\n");
            
            experimentoAVL(writer, resumoWriter);
            experimentoBST(writer, resumoWriter);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String fileNameAntecessor = "Experimento_Altura_Antecessor.csv";
        String resumoFileNameAntecessor = "Resumo_Altura_Antecessor.csv";

        try (FileWriter writer = new FileWriter(fileNameAntecessor);
            FileWriter resumoWriter = new FileWriter(resumoFileNameAntecessor)) 
        {
            writer.write("Tipo_Antecessor;Qtd Chaves;Altura;Altura Esperada\n");
            resumoWriter.write("Tipo_Antecessor;Media;Desvio Padrao\n");
            
            experimentoAntecessorAVL(writer, resumoWriter);
            experimentoAntecessorBST(writer, resumoWriter);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void experimentoAntecessorBST(FileWriter writer, FileWriter resumoWriter) throws IOException {

        for (int i = 0; i < 100; i++) 
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 2)),
                                         (int) (Math.pow(10,3)));

            List<Integer> alturas = new ArrayList<>();

            for(int z = 0; z < 100; z++)
            {
                ArvBinBusca<Integer> arvBusca = new ArvBinBusca<>();
                int alturaEsperadaBST = ArvBinBusca.alturaMedia(qtdChaves); 
                List<Integer> chaves = new ArrayList<>();

                for (int j = 0; j < qtdChaves; j++)
                {
                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvBusca.insere(chave, j);
                    chaves.add(chave);
                }
                
                int qtdLoop = (int) Math.pow(qtdChaves, 2);
                
                for (int k = 0; k < qtdLoop; k++)
                { 
                    if(new Random().nextBoolean())
                        arvBusca.remove(chaves.remove(
                                        new Random()
                                        .nextInt(chaves.size())));
                    else
                        arvBusca.removeAntecessor(chaves.remove(
                                        new Random()
                                        .nextInt(chaves.size())));

                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvBusca.insere(chave, k);
                    chaves.add(chave);
                }
                
                writer.write(String.format("BST;%d;%d;%d\n", 
                                        qtdChaves, arvBusca.getAltura(), alturaEsperadaBST));

                alturas.add(arvBusca.getAltura()); 
            }

            double media = calculaMedia(alturas);
            double desvioPadrao = calculaDesvioPadrao(alturas, media);

            resumoWriter.write(String.format("BST;%f;%f\n", media, desvioPadrao));
        }                           
    }

    private static void experimentoAntecessorAVL(FileWriter writer, FileWriter resumoWriter) throws IOException {

        for (int i = 0; i < 100; i++) 
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 2)),
                                         (int) (Math.pow(10,3)));

            List<Integer> alturas = new ArrayList<>();
                                         
            for(int z = 0; z < 100; z++)
            {
                ArvAVL<Integer> arvAVL = new ArvAVL<>();
                int alturaEsperadaAVL = ArvAVL.alturaMedia(qtdChaves);
                List<Integer> chaves = new ArrayList<>();

                for (int j = 0; j < qtdChaves; j++)
                {
                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvAVL.insere(chave, j);
                    chaves.add(chave);
                }
                
                int qtdLoop = (int) Math.pow(qtdChaves, 2);
                
                for (int k = 0; k < qtdLoop; k++)
                { 
                    if(new Random().nextBoolean())
                        arvAVL.remove(chaves.remove(
                                        new Random()
                                        .nextInt(chaves.size())));
                    else
                        arvAVL.removeAntecessor(chaves.remove(
                                        new Random()
                                        .nextInt(chaves.size())));

                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvAVL.insere(chave, i);
                    chaves.add(chave);
                }

                writer.write(String.format("AVL;%d;%d;%d\n", 
                                        qtdChaves, arvAVL.getAltura(), alturaEsperadaAVL));
                
                alturas.add(arvAVL.getAltura());
            }

            double media = calculaMedia(alturas);
            double desvioPadrao = calculaDesvioPadrao(alturas, media);

            resumoWriter.write(String.format("AVL;%f;%f\n", media, desvioPadrao));
        }
    }
    
    private static void experimentoAVL(FileWriter writer, FileWriter resumoWriter) throws IOException {

        for (int i = 0; i < 100; i++) 
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 2)),
                                         (int) (Math.pow(10,3)));

            List<Integer> alturas = new ArrayList<>();
            
            for(int z = 0; z < 100; z++)
            {
                ArvAVL<Integer> arvAVL = new ArvAVL<>();
                int alturaEsperadaAVL = ArvAVL.alturaMedia(qtdChaves);
                List<Integer> chaves = new ArrayList<>();

                for (int j = 0; j < qtdChaves; j++)
                {
                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvAVL.insere(chave, j);
                    chaves.add(chave);
                }
                
                int qtdLoop = (int) Math.pow(qtdChaves, 2);
                
                for (int k = 0; k < qtdLoop; k++)
                { 
                    arvAVL.remove(chaves.remove(
                                        new Random()
                                        .nextInt(chaves.size())));

                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvAVL.insere(chave, k);
                    chaves.add(chave);
                }

                writer.write(String.format("AVL;%d;%d;%d\n", 
                                        qtdChaves, arvAVL.getAltura(), alturaEsperadaAVL));

                alturas.add(arvAVL.getAltura());
            }

            double media = calculaMedia(alturas);
            double desvioPadrao = calculaDesvioPadrao(alturas, media);

            resumoWriter.write(String.format("AVL;%f;%f\n", media, desvioPadrao));
        }
    }

    private static void experimentoBST(FileWriter writer, FileWriter resumoWriter) throws IOException {

        for (int i = 0; i < 100; i++) 
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 2)),
                                         (int) (Math.pow(10,3)));

            List<Integer> alturas = new ArrayList<>();

            for(int z = 0; z < 100; z++)
            {
                ArvBinBusca<Integer> arvBusca = new ArvBinBusca<>();
                int alturaEsperadaBST = ArvBinBusca.alturaMedia(qtdChaves); 
                List<Integer> chaves = new ArrayList<>();

                for (int j = 0; j < qtdChaves; j++)
                {
                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvBusca.insere(chave, j);
                    chaves.add(chave);
                }
                
                int qtdLoop = (int) Math.pow(qtdChaves, 2);
                
                for (int k = 0; k < qtdLoop; k++)
                { 
                    arvBusca.remove(chaves.remove(
                                        new Random()
                                        .nextInt(chaves.size())));

                    int chave = new Random().nextInt(1, qtdChaves * 1000);
                    arvBusca.insere(chave, k);
                    chaves.add(chave);
                }
                
                writer.write(String.format("BST;%d;%d;%d\n", 
                                        qtdChaves, arvBusca.getAltura(), alturaEsperadaBST));

                alturas.add(arvBusca.getAltura());
            }

            double media = calculaMedia(alturas);
            double desvioPadrao = calculaDesvioPadrao(alturas, media);

            resumoWriter.write(String.format("BST;%f;%f\n", media, desvioPadrao));            
        }    
    }

    private static double calculaMedia(List<Integer> valores) {
        double soma = 0.0;
        for (int valor : valores) {
            soma += valor;
        }
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
