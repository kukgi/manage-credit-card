package br.com.e2dp.mscreditvalidator.api.v1.controller;

import br.com.e2dp.mscreditvalidator.exception.ClientDateNotfoundException;
import br.com.e2dp.mscreditvalidator.exception.ComunicationErrorException;
import br.com.e2dp.mscreditvalidator.model.*;
import br.com.e2dp.mscreditvalidator.service.CreditValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-service/api/v1/credit-validator")
@RequiredArgsConstructor
public class CreditValidatorController {


    private final CreditValidatorService creditValidatorService;

    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<RetornoAvaliacaoCliente> avaliacaoCliente(@RequestBody DadosAvaliacao dadosAvaliacao) throws ClientDateNotfoundException, ComunicationErrorException {
        //TODO.. fazer validacao dos campos DadosAvaliacao
        RetornoAvaliacaoCliente retornoAvaliacaoCliente = creditValidatorService.validaCartoesDisponiveisParaCliente(dadosAvaliacao);
        return ResponseEntity.ok(retornoAvaliacaoCliente);
    }

    @PostMapping("solicitacao-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) throws Exception {
        //ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = creditValidatorService.solicitaEmissaoDeCartao(dadosSolicitacaoEmissaoCartao);
        //creditValidatorService.solicitaEmissaoDeCartaoKfk(dadosSolicitacaoEmissaoCartao);
        return ResponseEntity.ok(creditValidatorService.solicitaEmissaoDeCartaoKfk(dadosSolicitacaoEmissaoCartao));
    }

    @GetMapping("situacao-cliente")
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam("cpf") String cpf) throws ClientDateNotfoundException, ComunicationErrorException {
        SituacaoCliente situacaoCliente = creditValidatorService.verificaSituacaoCliente(cpf);
        return ResponseEntity.ok(situacaoCliente);
    }


}
