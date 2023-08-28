package br.com.desafio.solo.picpay.service;

import br.com.desafio.solo.picpay.domain.transacao.Transacao;
import br.com.desafio.solo.picpay.domain.user.TipoUsuario;
import br.com.desafio.solo.picpay.domain.user.Usuario;
import br.com.desafio.solo.picpay.dto.record.TransacaoDTO;
import br.com.desafio.solo.picpay.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void validarTransacao(Usuario remetente, BigDecimal valor) throws Exception {
        if (remetente.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente.");
        }

        if (remetente.getTipoUsuario() == TipoUsuario.LOJISTA ) {
            throw new Exception("Usuário do tipo Lojista não pode efetuar transações.");
        }
    }

    public Transacao criarTransacao(TransacaoDTO transacaoDTO) throws Exception {
        Usuario remetente = usuarioService.encontrarUsuarioPorId(transacaoDTO.idRemetente());
        Usuario destinatario = usuarioService.encontrarUsuarioPorId(transacaoDTO.idDestinatario());

        validarTransacao(remetente, transacaoDTO.valor());

        remetente.setSaldo(remetente.getSaldo().subtract(transacaoDTO.valor()));
        destinatario.setSaldo(destinatario.getSaldo().add(transacaoDTO.valor()));

        Transacao newTransacao = new Transacao();
        newTransacao.setRemetente(remetente);
        newTransacao.setDestinatario(destinatario);
        newTransacao.setValor(transacaoDTO.valor());

        usuarioService.salvarUsuario(remetente);
        usuarioService.salvarUsuario(destinatario);
        transacaoRepository.save(newTransacao);

        return newTransacao;
    }

    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }
}