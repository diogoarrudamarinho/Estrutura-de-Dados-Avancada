
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Experimentos {
    public static void main(String[] args) {
        
        int experimentos = 100; 
        String fileName = "Experimento_Altura_Arvores.csv"; 
        
        try (FileWriter writer = new FileWriter(fileName))
        {
            writer.write("Tipo,Qtd Chaves,Altura,Altura Esperada\n");
            
            experimentoAVL(writer, experimentos);
            experimentoBST(writer, experimentos);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void experimentoAVL(FileWriter writer, int experimentos) throws IOException {
        
        for(int i = 0; i < experimentos; i++)
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 4)),
                                         (int) (Math.pow(10, 6))); 

            ArvAVL<Integer> arvAVL = new ArvAVL<>();
            int alturaEsperadaAVL = ArvAVL.alturaMedia(qtdChaves);

            for (int j = 0; j < qtdChaves; j++)
                arvAVL.insere(j, j);

            writer.write(String.format("AVL,%d,%d,%d\n", 
                         qtdChaves, arvAVL.getAltura(), alturaEsperadaAVL));
        }
    }

    private static void experimentoBST(FileWriter writer, int experimentos) throws IOException {

        for(int i = 0; i < experimentos; i++)
        {
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 4)),
                                         (int) (Math.pow(10, 6)));  

            ArvBinBusca<Integer> arvBusca = new ArvBinBusca<>();
            int alturaEsperadaBST = ArvAVL.alturaMedia(qtdChaves);

            for (int j = 0; j < qtdChaves; j++)
                arvBusca.insere(j, j);

            writer.write(String.format("AVL,%d,%d,%d\n", 
                         qtdChaves, arvBusca.altura(), alturaEsperadaBST));
        }
    }
}
