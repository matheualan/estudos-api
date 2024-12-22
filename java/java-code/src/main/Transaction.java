package main;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Transaction {

    public static void main(String[] args) {

//        main.Person.firstTest();
//        main.Person person = new main.Person();
//        person.secondTest();

        Person personOne = new Person("Matheus", new BigDecimal(50000));
        Person personTwo = new Person("Alan", new BigDecimal(30000));

//        personOne.setValue(personOne.getValue().add(new BigDecimal(2)));
//        System.out.println(personOne);

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual valor voce quer transferir?");
        BigDecimal amountTransferred = sc.nextBigDecimal();
        System.out.println("Valor informado: " + amountTransferred);

        if(personTwo.getValue().compareTo(amountTransferred) < 0) {

        }

        personOne.setValue(personOne.getValue().add(amountTransferred));
        personTwo.setValue(personTwo.getValue().subtract(amountTransferred));

        System.out.println(personOne);
        System.out.println(personTwo);


    }

}

class Person {

    private String name;
    private BigDecimal value;
    private final String timestamp = DateFormatter.dateFormat(LocalDateTime.now());

    public Person() {

    }

    public Person(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    static void firstTest() {
        System.out.println("Primeiro teste");
    }

    void secondTest() {
        System.out.println("Segundo teste");
    }

    @Override
    public String toString() {
        return "main.Person {" + "Name: " + name
                + ", Value: " + value
                + ", Timestamp: " + timestamp + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

class DateFormatter {

    public static String dateFormat(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(localDateTime);
    }

}
