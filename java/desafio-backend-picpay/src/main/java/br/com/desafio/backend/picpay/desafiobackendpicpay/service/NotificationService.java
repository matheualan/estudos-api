package br.com.desafio.backend.picpay.desafiobackendpicpay.service;

import br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record.NotificationDTO;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    private final WebClient webClient;

    public NotificationService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.build();
    }

    public void sendNotifyRestTemplate(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationDTO = new NotificationDTO(email, message);

//        ResponseEntity<String> responseNotification = restTemplate.postForEntity(
//                "http://o4d9z.mocklab.io/notify",
//                notificationDTO,
//                String.class);
//
//        if (!(responseNotification.getStatusCode() == HttpStatus.OK)) {
//            System.out.println("Erro ao enviar notificação.");
//            throw new Exception("Serviço de notificação está fora do ar.");
//        }

        System.out.println("Notificação enviada para o usuário");
    }

    public void sendNotification(User user, String message) {
        String email = user.getEmail();

        NotificationDTO notificationDTO = new NotificationDTO(email, message);

        Mono<String> notificationResponse = webClient.post()
                .uri("http://o4d9z.mocklab.io/notify")
                .bodyValue(notificationDTO)
                .retrieve()
                .bodyToMono(String.class);


    }

}
