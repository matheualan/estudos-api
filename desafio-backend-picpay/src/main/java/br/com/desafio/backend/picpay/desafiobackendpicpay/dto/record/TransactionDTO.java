package br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Integer senderId, Integer receiverId) {
}