package br.com.e2dp.mscreditvalidator.model;


import br.com.e2dp.mscreditvalidator.model.enums.CardBrand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    private Long id;
    private String name;
    private CardBrand cardBrand;
    //private BigDecimal monthlyIncome;
    private BigDecimal creditLimit;
}
