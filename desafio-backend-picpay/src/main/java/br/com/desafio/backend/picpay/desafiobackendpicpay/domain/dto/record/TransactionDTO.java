package br.com.desafio.backend.picpay.desafiobackendpicpay.domain.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Integer senderId, Integer receiverId) {
}