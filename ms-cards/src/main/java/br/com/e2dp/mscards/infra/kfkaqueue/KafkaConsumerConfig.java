package br.com.e2dp.mscards.infra.kfkaqueue;

import br.com.e2dp.mscards.model.dto.DadosSolicitacaoEmissaoCartao;
import br.com.e2dp.mscards.model.entity.Card;
import br.com.e2dp.mscards.model.entity.ClientCard;
import br.com.e2dp.mscards.repository.CardRespository;
import br.com.e2dp.mscards.repository.ClientCardRepository;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaConsumerConfig {

    @Autowired
    private CardRespository cardRespository;
    @Autowired
    private ClientCardRepository cardRepository;

    @KafkaListener(
            topics = "${topic.queues.emissao-cartoes}",
            groupId = "group_id_cartoes",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void persisterSolicitacaoEmissao(@Payload String payload) {
        log.info("Lendo dados da fila: topic.queues.emissao-cartoes");
        Gson gson = new Gson();
        DadosSolicitacaoEmissaoCartao dados = gson.fromJson(payload, DadosSolicitacaoEmissaoCartao.class);
        Card card = cardRespository.findById(dados.getIdCartao()).orElseThrow();
        ClientCard clientCard = new ClientCard();
        clientCard.setCard(card);
        clientCard.setCpf(dados.getCpf());
        clientCard.setLimite(dados.getLimiteLiberado());
        cardRepository.save(clientCard);
        log.info("Solicitacao de emissao de cartoes recebida: " + payload);
    }
}
