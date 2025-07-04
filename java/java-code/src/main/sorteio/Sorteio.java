package main.sorteio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorteio {

    public static void main(String[] args) {

        List<String> sorteio4 = new ArrayList<>();
        List<String> sorteio10 = new ArrayList<>();
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = timestamp.format(formatter);

        String pathSorteio4 = "C:\\Users\\mathe\\OneDrive\\Área de Trabalho\\SORTEIO 4G.txt";
        String pathSorteio10 = "C:\\Users\\mathe\\OneDrive\\Área de Trabalho\\SORTEIO 10G.txt";

        try {
            sorteio4 = Files.readAllLines(Paths.get(pathSorteio4));
            sorteio10 = Files.readAllLines(Paths.get(pathSorteio10));
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }

        Random random = new Random();
        int sort4 = random.nextInt(sorteio4.size());
        int sort4_2 = random.nextInt(sorteio4.size());
        int sort10 = random.nextInt(sorteio10.size());
        String ganhadorSorteio4 = sorteio4.get(sort4);
        String ganhadorSorteio4_2 = sorteio4.get(sort4_2);
        String ganhadorSorteio10 = sorteio10.get(sort10);

        System.err.println("\n=====================================================");
        System.err.println("\nDATA DO SORTEIO: " + dataFormatada);
        System.err.println("\nPRIMEIRO GANHADOR SORTEIO 4G: \n" + ganhadorSorteio4);
        System.err.println("--------------------------------------\nSEGUNDO GANHADOR SORTEIO 4G: \n" + ganhadorSorteio4_2);
        System.err.println("--------------------------------------\nGANHADOR SORTEIO 10G: \n" + ganhadorSorteio10);
        System.err.println("\n=====================================================");

    }

}
