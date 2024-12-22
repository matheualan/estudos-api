package main.transactionProject.service;

import main.transactionProject.enuns.TypePersonEnum;
import main.transactionProject.entities.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transaction {

    public static void main(String[] args) {

        List<Person> listPersons = new ArrayList<>();

        Person personOne = new Person("Matheus", "123", new BigDecimal(50000));
        Person personTwo = new Person("Alan", "987" , new BigDecimal(30000));
//        System.out.println(personOne);
//        System.out.println(personTwo);

        listPersons.add(personOne);
        listPersons.add(personTwo);

        Scanner sc = new Scanner(System.in);
        System.out.println("\nInforme o documento de quem ira realizar a transferencia: ");
        String sender = sc.nextLine();

        if(sender.equalsIgnoreCase("123")) {
            personOne.setTypePerson(TypePersonEnum.REMETENTE);
            personTwo.setTypePerson(TypePersonEnum.DESTINATARIO);
        } else if(sender.equalsIgnoreCase("987")) {
            personTwo.setTypePerson(TypePersonEnum.REMETENTE);
            personOne.setTypePerson(TypePersonEnum.DESTINATARIO);
        }

//        System.out.println("\nInforme o documento de quem irá receber a transferência: ");
//        String recipient = sc.nextLine();

        System.out.println("\nQual valor voce quer transferir?");
        BigDecimal amountTransferred = sc.nextBigDecimal();
        System.out.println("Valor informado: " + amountTransferred + "\n");
        sc.close();

        if(personOne.getBalance().compareTo(amountTransferred) < 0) {
            throw new RuntimeException("Valor indisponivel.");
        } else {
            personTwo.setBalance(personTwo.getBalance().add(amountTransferred));
            personOne.setBalance(personOne.getBalance().subtract(amountTransferred));
        }
        System.out.println(personOne);
        System.out.println(personTwo);

    }

}
