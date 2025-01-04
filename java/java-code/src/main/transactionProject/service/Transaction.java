package main.transactionProject.service;

import main.transactionProject.entities.Person;
import main.transactionProject.enuns.TypePersonEnum;

import java.math.BigDecimal;
import java.util.Scanner;

public class Transaction {

    public static void main(String[] args) {

        Person personOne = new Person("Matheus", "123", new BigDecimal(50000));
        Person personTwo = new Person("Alan", "987" , new BigDecimal(30000));

        System.out.println(personOne);
        System.out.println(personTwo);

        Scanner sc = new Scanner(System.in);
        System.out.print("\nInforme o documento de quem ira realizar a transferencia: ");
        String sender = sc.nextLine();
        System.out.println("Documento informado: " + sender);

        if(sender.equalsIgnoreCase("123")) {
            personOne.setTypePerson(TypePersonEnum.REMETENTE);
            personTwo.setTypePerson(TypePersonEnum.DESTINATARIO);
        } else if(sender.equalsIgnoreCase("987")) {
            personTwo.setTypePerson(TypePersonEnum.REMETENTE);
            personOne.setTypePerson(TypePersonEnum.DESTINATARIO);
        } else {
            System.err.println("Documento nao encontrado.");
            return;
        }

        System.out.print("\nQual valor deseja transferir? ");
        BigDecimal amountTransferred = sc.nextBigDecimal();
        System.out.println("Valor informado: " + amountTransferred + "\n");
        sc.close();

        if(personOne.getTypePerson() == TypePersonEnum.REMETENTE) {
            if(personOne.getBalance().compareTo(amountTransferred) < 0) {
                throw new RuntimeException("Valor indisponivel.");
            }
            personOne.setBalance(personOne.getBalance().subtract(amountTransferred));
            personTwo.setBalance(personTwo.getBalance().add(amountTransferred));
        } else if(personTwo.getTypePerson() == TypePersonEnum.REMETENTE) {
            if(personTwo.getBalance().compareTo(amountTransferred) < 0) {
                throw new RuntimeException("Valor indisponivel.");
            }
            personTwo.setBalance(personTwo.getBalance().subtract(amountTransferred));
            personOne.setBalance(personOne.getBalance().add(amountTransferred));
        }

        System.out.println(personOne.getTypePerson() + " - " + personOne);
        System.out.println(personTwo.getTypePerson() + " - " + personTwo);

    }

}
