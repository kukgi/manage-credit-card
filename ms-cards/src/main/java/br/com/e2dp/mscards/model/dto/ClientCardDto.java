package br.com.e2dp.mscards.model.dto;

import br.com.e2dp.mscards.model.entity.Card;
import br.com.e2dp.mscards.model.entity.ClientCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCardDto {

    private String name;
    private String cardBrand;
    private BigDecimal creditLimit;

    public static ClientCardDto fromModel(ClientCard clientCard) {
        return new ClientCardDto(
                clientCard.getCard().getName(),
                clientCard.getCard().getCardBrand().name(),
                clientCard.getLimite());
    }
}
