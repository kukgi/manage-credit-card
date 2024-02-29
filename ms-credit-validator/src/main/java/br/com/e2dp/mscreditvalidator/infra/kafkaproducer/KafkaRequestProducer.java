package br.com.e2dp.mscreditvalidator.infra.kafkaproducer;

import br.com.e2dp.mscreditvalidator.model.DadosSolicitacaoEmissaoCartao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class KafkaRequestProducer {
    @Value("${topic.queues.emissao-cartoes}")
    private String cardCreditValidator;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public String emitirCartao(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) throws Exception {
        String json = objectMapper.writeValueAsString(dadosSolicitacaoEmissaoCartao);
        kafkaTemplate.send(cardCreditValidator, json);
        return json;
    }
}
