package br.com.desafio.solo.picpay.service;

import br.com.desafio.solo.picpay.domain.transacao.Transacao;
import br.com.desafio.solo.picpay.domain.user.TipoUsuario;
import br.com.desafio.solo.picpay.domain.user.Usuario;
import br.com.desafio.solo.picpay.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Transacao criarTransacao(Usuario remetente, Usuario destinatario, BigDecimal valor) throws Exception {
        if (remetente.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new Exception("Usuário do tipo Lojista não pode realizar transferência.");
        }

        remetente.getSaldo().subtract(valor);
        destinatario.getSaldo().add(valor);

        Transacao transacao = new Transacao();
        transacao.setRemetente(remetente);
        transacao.setDestinatario(destinatario);
        transacao.setValor(valor);

        return transacao;
    }

}
