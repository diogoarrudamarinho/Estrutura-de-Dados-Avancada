package Trabalho_A;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Arvores.ArvAVL;
import Arvores.ArvBinBusca;

public class ExperimentoLoop {
    public static void main(String[] args) {

        String fileName = "Experimento_Altura_Loop.csv"; 
        
        try (FileWriter writer = new FileWriter(fileName))
        {
            int qtdChaves = new Random()
                            .nextInt((int) (Math.pow(10, 2)),
                                     (int) (Math.pow(10, 4))); 

            writer.write("Tipo,Qtd Chaves,Altura,Altura Esperada\n");
            
            experimentoAVL(writer, qtdChaves);
            experimentoBST(writer, qtdChaves);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } 
    }

    private static void experimentoAVL(FileWriter writer, int qtdChaves) throws IOException {

        ArvAVL<Integer> arvAVL = new ArvAVL<>();
        int alturaEsperadaAVL = ArvAVL.alturaMedia(qtdChaves);
        List<Integer> chaves = new ArrayList<>();

        for (int i = 0; i < qtdChaves; i++)
        {
            int chave = new Random().nextInt(1, qtdChaves * 1000);
            arvAVL.insere(chave, i);
            chaves.add(chave);
        }
           
        int qtdLoop = (int) Math.pow(qtdChaves, 2);
        
        for (int i = 0; i < qtdLoop; i++)
        { 
            arvAVL.remove(chaves.remove(
                                new Random()
                                .nextInt(chaves.size())));

            int chave = new Random().nextInt(1, qtdChaves * 1000);
            arvAVL.insere(chave, i);
            chaves.add(chave);
        }

        writer.write(String.format("AVL,%d,%d,%d\n", 
                                  qtdChaves, arvAVL.getAltura(), alturaEsperadaAVL));
        
    }

    private static void experimentoBST(FileWriter writer, int qtdChaves) throws IOException {

        ArvBinBusca<Integer> arvBusca = new ArvBinBusca<>();
        int alturaEsperadaBST = ArvBinBusca.alturaMedia(qtdChaves); 
        List<Integer> chaves = new ArrayList<>();

        for (int i = 0; i < qtdChaves; i++)
        {
            int chave = new Random().nextInt(1, qtdChaves * 1000);
            arvBusca.insere(chave, i);
            chaves.add(chave);
        }
           
        int qtdLoop = (int) Math.pow(qtdChaves, 2);
        
        for (int i = 0; i < qtdLoop; i++)
        { 
            arvBusca.remove(chaves.remove(
                                new Random()
                                .nextInt(chaves.size())));

            int chave = new Random().nextInt(1, qtdChaves * 1000);
            arvBusca.insere(chave, i);
            chaves.add(chave);
        }
        
        writer.write(String.format("BST,%d,%d,%d\n", 
                                  qtdChaves, arvBusca.getAltura(), alturaEsperadaBST));
    }
}
