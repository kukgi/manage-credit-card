package br.com.e2dp.mscards.api.v1;

import br.com.e2dp.mscards.model.dto.CardDto;
import br.com.e2dp.mscards.model.dto.ClientCardDto;
import br.com.e2dp.mscards.service.CardService;
import br.com.e2dp.mscards.service.ClientCardService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/credit-service/api/v1/card")
public class    CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private ClientCardService clientCardService;

    @PostMapping
    public ResponseEntity<CardDto> persistRequestCard(@RequestBody CardDto cardDto){
        val cardResponse = cardService.saveCard(cardDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardResponse);
    }

    @GetMapping("/available")
    public ResponseEntity<List<CardDto>> getRequestCardsIncomeLessThanEqual(@RequestParam("income") BigDecimal icome){
        List<CardDto> listCardsAvaliable =  cardService.getCardsIncomeLessThanEqual(icome);
        return ResponseEntity.status(HttpStatus.OK).body(listCardsAvaliable);
    }

    @GetMapping("/find-by-cpf")
    public ResponseEntity<List<ClientCardDto>> getRequestCardsByClient(@RequestParam("cpf") String cpf){
        List<ClientCardDto> listCardsAvaliable =  clientCardService.listCardsByCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(listCardsAvaliable);
    }

}
