package br.com.e2dp.mscreditvalidator.model;

import br.com.e2dp.mscreditvalidator.model.enums.CardBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CardAprovado {
    private String nome;
    private CardBrand bandeira;
    private BigDecimal limiteAprovado;
}
