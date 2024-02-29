package br.com.e2dp.mscreditvalidator.infra.mqueueproduce;

import br.com.e2dp.mscreditvalidator.model.DadosSolicitacaoEmissaoCartao;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SolicitacaoCartaoEmissaoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queueEmissaoCartoes;

    //Produtor
    public void solicitarCartao(DadosSolicitacaoEmissaoCartao emissaoCartao){
        Gson gson = new Gson();
        val emissaoCartaoJson = gson.toJson(emissaoCartao);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getActualName(), emissaoCartaoJson);
    }
}
