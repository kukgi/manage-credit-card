package br.com.e2dp.mscreditvalidator.infra.ms;

import br.com.e2dp.mscreditvalidator.model.Card;
import br.com.e2dp.mscreditvalidator.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "ms-cards",path = "/credit-service/api/v1/card")
public interface ClientCardsResource {

     @GetMapping("/find-by-cpf")
     ResponseEntity<List<CartaoCliente>> getRequestCardsByClient(@RequestParam("cpf") String cpf);

     @GetMapping("/available")
     public ResponseEntity<List<Card>> getRequestCardsIncomeLessThanEqual(@RequestParam("income") BigDecimal icome);

}
