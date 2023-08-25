package br.com.desafio.solo.picpay.dto.record;

import java.math.BigDecimal;

public record TransacaoDTO(Long idRemetente, Long idDestinatario, BigDecimal valor) {
}
