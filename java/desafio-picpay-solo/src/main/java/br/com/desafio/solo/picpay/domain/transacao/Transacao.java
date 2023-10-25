package br.com.desafio.solo.picpay.domain.transacao;

import br.com.desafio.solo.picpay.domain.user.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idTransacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransacao;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_remetente", referencedColumnName = "idUsuario")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "id_destinatario", referencedColumnName = "idUsuario")
    private Usuario destinatario;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime dataTransacao = LocalDateTime.now();

}
