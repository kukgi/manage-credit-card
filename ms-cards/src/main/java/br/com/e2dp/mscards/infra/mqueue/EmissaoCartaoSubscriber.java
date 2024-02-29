package br.com.e2dp.mscards.infra.mqueue;


import br.com.e2dp.mscards.model.dto.DadosSolicitacaoEmissaoCartao;
import br.com.e2dp.mscards.model.entity.Card;
import br.com.e2dp.mscards.model.entity.ClientCard;
import br.com.e2dp.mscards.repository.CardRespository;
import br.com.e2dp.mscards.repository.ClientCardRepository;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EmissaoCartaoSubscriber {

    @Autowired
    private CardRespository cardRespository;
    @Autowired
    private ClientCardRepository cardRepository;

    //Lendo da fila RobbitMQ
    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        Gson gson = new Gson();
        DadosSolicitacaoEmissaoCartao dados = gson.fromJson(payload, DadosSolicitacaoEmissaoCartao.class);
        Card card = cardRespository.findById(dados.getIdCartao()).orElseThrow();
        ClientCard clientCard = new ClientCard();
        clientCard.setCard(card);
        clientCard.setCpf(dados.getCpf());
        clientCard.setLimite(dados.getLimiteLiberado());
        cardRepository.save(clientCard);
    }
}
