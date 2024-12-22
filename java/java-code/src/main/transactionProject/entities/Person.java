package main.transactionProject.entities;

import main.transactionProject.enuns.TypePersonEnum;
import main.transactionProject.util.DateFormatter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Person {

    private String name;
    private String document;
    private BigDecimal balance;
    private TypePersonEnum typePerson;
    private final String timestamp = DateFormatter.dateFormat(LocalDateTime.now());

    public Person() {
    }

    public Person(String name, String document, BigDecimal balance) {
        this.name = name;
        this.document = document;
        this.balance = balance;
//        this.typePerson = typePerson;
    }

    @Override
    public String toString() {
        return "Person {" + "Name: " + name
                + ", Value: " + balance
                + ", TypePerson: " + typePerson + " - " + typePerson.description
                + ", Timestamp: " + timestamp + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public TypePersonEnum getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(TypePersonEnum typePerson) {
        this.typePerson = typePerson;
    }

}
