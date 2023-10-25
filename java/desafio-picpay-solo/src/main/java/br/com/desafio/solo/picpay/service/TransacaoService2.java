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
    private UsuarioService usuarioService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public void validarTransacao(Usuario usuario, BigDecimal valor) {
        if (usuario.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new PersonalizedException("Usuário do tipo LOJISTA não pode efetuar transações.");
        }
        if (usuario.getSaldo().compareTo(valor) < 0) {
            throw new PersonalizedException("Saldo insuficiente.");
        }
    }

    public Transacao createTransaction(TransacaoDTO transacaoDTO) {
        Usuario remetente = usuarioService.encontrarUsuarioPorId(transacaoDTO.idRemetente());
        Usuario destinatario = usuarioService.encontrarUsuarioPorId(transacaoDTO.idDestinatario());

        validarTransacao(remetente, transacaoDTO.valor());

        remetente.setSaldo(remetente.getSaldo().subtract(transacaoDTO.valor()));
        destinatario.setSaldo(destinatario.getSaldo().add(transacaoDTO.valor()));

        Transacao transacao = new Transacao();
        transacao.setRemetente(remetente);
        transacao.setDestinatario(destinatario);
        transacao.setValor(transacaoDTO.valor());

        usuarioService.salvarUsuario(remetente);
        usuarioService.salvarUsuario(destinatario);
        transacaoRepository.save(transacao);

        return transacao;

    }


}
