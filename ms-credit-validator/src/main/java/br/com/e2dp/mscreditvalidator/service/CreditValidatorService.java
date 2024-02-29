package br.com.e2dp.mscreditvalidator.service;

import br.com.e2dp.mscreditvalidator.infra.kafkaproducer.KafkaRequestProducer;
import br.com.e2dp.mscreditvalidator.infra.mqueueproduce.SolicitacaoCartaoEmissaoPublisher;
import br.com.e2dp.mscreditvalidator.infra.ms.ClientCardsResource;
import br.com.e2dp.mscreditvalidator.infra.ms.ClientResource;
import br.com.e2dp.mscreditvalidator.exception.ClientDateNotfoundException;
import br.com.e2dp.mscreditvalidator.exception.ComunicationErrorException;
import br.com.e2dp.mscreditvalidator.model.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditValidatorService {

    @Autowired
    private ClientResource clientResource;
    @Autowired
    private ClientCardsResource clientCardsResource;

    @Autowired
    private SolicitacaoCartaoEmissaoPublisher emissaoCartaoPublisher;

    @Autowired
    private KafkaRequestProducer kafkaRequestProducer;

    public SituacaoCliente verificaSituacaoCliente(String cpf) throws ClientDateNotfoundException, ComunicationErrorException {
        try {
            ResponseEntity<Client> responseDataClient = clientResource.getDataClientByCpf(cpf);
            ResponseEntity<List<CartaoCliente>> listCards = clientCardsResource.getRequestCardsByClient(cpf);
            return SituacaoCliente
                    .builder()
                    .cliente(responseDataClient.getBody())
                    .cartoes(listCards.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDateNotfoundException();
            }
            throw new ComunicationErrorException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente validaCartoesDisponiveisParaCliente(DadosAvaliacao dadosAvaliacao) throws ClientDateNotfoundException, ComunicationErrorException {
        try {
            ResponseEntity<Client> responseDataClient = clientResource.getDataClientByCpf(dadosAvaliacao.getCpf());
            ResponseEntity<List<Card>> cartoesResponse = clientCardsResource.getRequestCardsIncomeLessThanEqual(dadosAvaliacao.getRenda());
            List<Card> listCartoes = cartoesResponse.getBody();
            List<CardAprovado> cartoesAprovados = listCartoes.stream().map(cartao -> {
                Client client = responseDataClient.getBody();
                BigDecimal limiteBasico = cartao.getCreditLimit();
                BigDecimal idadeBD = BigDecimal.valueOf(client.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);
                CardAprovado cardAprovado = new CardAprovado();
                cardAprovado.setBandeira(cartao.getCardBrand());
                cardAprovado.setNome(cartao.getName());
                cardAprovado.setLimiteAprovado(limiteAprovado);
                return cardAprovado;
            }).collect(Collectors.toList());
            return new RetornoAvaliacaoCliente(cartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDateNotfoundException();
            }
            throw new ComunicationErrorException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solicitaEmissaoDeCartao(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) {
        emissaoCartaoPublisher.solicitarCartao(dadosSolicitacaoEmissaoCartao);
        var protocolo = UUID.randomUUID().toString();
        return new ProtocoloSolicitacaoCartao(protocolo);
    }

    public ProtocoloSolicitacaoCartao solicitaEmissaoDeCartaoKfk(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) throws Exception {
        kafkaRequestProducer.emitirCartao(dadosSolicitacaoEmissaoCartao);
        var protocolo = UUID.randomUUID().toString();
        return new ProtocoloSolicitacaoCartao(protocolo);
    }
}
