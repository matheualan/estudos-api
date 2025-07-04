package main.sorteio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Sort {

    public static void main(String[] args) {

        List<String> listaSorteio = new ArrayList<>();
        String pathFileSorteio = "C:\\Users\\mathe\\OneDrive\\Área de Trabalho\\sorteio.txt";

        try {
            listaSorteio = Files.readAllLines(Paths.get(pathFileSorteio));
//            listaSorteio.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }


        Random random = new Random();
        int numeroSorteado = random.nextInt(listaSorteio.size());
        String ganhador = listaSorteio.get(numeroSorteado);

        System.out.println("\n\n============================================");
        System.err.println("\t\t\tGANHADOR DO SORTEIO: \n\t** " + ganhador.toUpperCase(Locale.ROOT) + " **");
        System.out.println("============================================\n\n");

    }

}
