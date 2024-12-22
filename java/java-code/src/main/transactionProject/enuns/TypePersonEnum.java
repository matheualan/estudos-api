package main.transactionProject.enuns;

public enum TypePersonEnum {

    STANDBY(0, "AGUARDANDO"), REMETENTE(1, "ENVIANDO"), DESTINATARIO(2, "RECEBENDO");

    public int typeId;
    public String description;

    TypePersonEnum(int typeId, String description) {
        this.typeId = typeId;
        this.description = description;
    }

}
