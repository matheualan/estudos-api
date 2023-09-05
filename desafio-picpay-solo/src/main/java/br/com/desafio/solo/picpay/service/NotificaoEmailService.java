package br.com.desafio.solo.picpay.service;

import br.com.desafio.solo.picpay.domain.user.Usuario;
import br.com.desafio.solo.picpay.dto.record.NotificationDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificaoEmailService {

    public void enviarNotificacaoEmail(Usuario usuario, String mensagem) {
        String email = usuario.getEmail();

        NotificationDTO notificationDTO = new NotificationDTO(email, mensagem);
    }

}
