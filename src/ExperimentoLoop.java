import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

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

        for (int i = 0; i < qtdChaves; i++)
            arvAVL.insere(new Random()
                              .nextInt(1, qtdChaves),
                              i);
        
        int qtdLoop = (int) Math.pow(qtdChaves, 2);
        
        for (int i = 0; i < qtdLoop; i++) 
            arvAVL.remove(new Random()
                              .nextInt(1, qtdChaves));
        
        writer.write(String.format("AVL,%d,%d,%d\n", 
                                  qtdChaves, arvAVL.getAltura(), alturaEsperadaAVL));
        
    }

    private static void experimentoBST(FileWriter writer, int qtdChaves) throws IOException {

        ArvBinBusca<Integer> arvBusca = new ArvBinBusca<>();
        int alturaEsperadaAVL = ArvAVL.alturaMedia(qtdChaves); // fazer a altura media na ArvBinBusca

        for (int i = 0; i < qtdChaves; i++)
            arvBusca.insere(new Random()
                                .nextInt(1, qtdChaves),
                                i);
        
        int qtdLoop = (int) Math.pow(qtdChaves, 2);
        
        for (int i = 0; i < qtdLoop; i++) 
            arvBusca.remove(new Random()
                              .nextInt(1, qtdChaves));
        
        writer.write(String.format("AVL,%d,%d,%d\n", 
                                  qtdChaves, arvBusca.getAltura(), alturaEsperadaAVL));
    }
}
