package br.com.desafio.solo.picpay.service;

import br.com.desafio.solo.picpay.domain.transacao.Transacao;
import br.com.desafio.solo.picpay.domain.user.TipoUsuario;
import br.com.desafio.solo.picpay.domain.user.Usuario;
import br.com.desafio.solo.picpay.dto.record.TransacaoDTO;
import br.com.desafio.solo.picpay.exception.PersonalizedException;
import br.com.desafio.solo.picpay.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransacaoService2 {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void validationTransaction(Usuario usuario, BigDecimal valor) throws Exception {
        if (usuario.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new Exception("Usuário do tipo lojista não pode realizar transações.");
        } else if (usuario.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public Transacao createTranscation(TransacaoDTO transacaoDTO) {
        Usuario remetente = usuarioService.encontrarUsuarioPorId(transacaoDTO.idRemetente());
        Usuario destinatario = usuarioService.encontrarUsuarioPorId(transacaoDTO.idDestinatario());

        try {
            validationTransaction(remetente, transacaoDTO.valor());
        } catch (Exception e) {
            new PersonalizedException("Não foi possível prosseguir com a transação.");
        }

        return null;

    }

}
