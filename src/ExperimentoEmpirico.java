
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ExperimentoEmpirico {
    public static void main(String[] args) {

        int experimentos = 100; 
        String fileName = "Experimento_Altura_Empirica.csv"; 
        
        try (FileWriter writer = new FileWriter(fileName))
        {
            writer.write("Tipo,Qtd Chaves,Altura,Altura Esperada\n");
            
            //experimentoAVL(writer, experimentos);
            experimentoBST(writer, experimentos);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } 
    }

    private static void experimentoAVL(FileWriter writer, int experimentos) throws IOException {
        
        for(int i = 0; i < experimentos; i++)
        {
            double media = 0;
            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 4)),
                                         (int) (Math.pow(10, 6))); 

            int alturaEsperadaAVL = ArvAVL.alturaMedia(qtdChaves);

            for (int k = 0; k < experimentos; k++) 
            {
                ArvAVL<Integer> arvAVL = new ArvAVL<>();

                for (int j = 0; j < qtdChaves; j++)
                {

                    int rand = (new Random().nextInt(1, qtdChaves * 1000));
                    arvAVL.insere(rand,j);
                }

                media += arvAVL.getAltura();

                writer.write(String.format("AVL,%d,%d,%d\n", 
                                    qtdChaves, arvAVL.getAltura(), alturaEsperadaAVL));
            }
            System.out.println(media /= experimentos);   
        }
    }

    private static void experimentoBST(FileWriter writer, int experimentos) throws IOException {

        for(int i = 0; i < 3; i++)
        {
            double media = 0;

            int qtdChaves = new Random()
                                .nextInt((int) (Math.pow(10, 4)),
                                         (int) (Math.pow(10, 6)));  

            ArvBinBusca<Integer> arvBusca = new ArvBinBusca<>();
            int alturaEsperadaBST = ArvBinBusca.alturaMedia(qtdChaves); // fazer a altura media na ArvBinBusca

            for (int k = 0; k < experimentos; k++) 
            {
                for (int j = 0; j < qtdChaves; j++)
                arvBusca.insere(new Random()
                                    .nextInt(1, qtdChaves * 1000),
                                    j);

                media += arvBusca.getAltura();
                
                writer.write(String.format("BST,%d,%d,%d\n", qtdChaves, arvBusca.getAltura(), alturaEsperadaBST));
            }

            System.out.println(media /= experimentos);   

        }
    }
}
