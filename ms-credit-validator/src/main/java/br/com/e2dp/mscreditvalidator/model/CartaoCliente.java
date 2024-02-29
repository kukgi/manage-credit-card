package br.com.e2dp.mscreditvalidator.model;

import br.com.e2dp.mscreditvalidator.model.enums.CardBrand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoCliente {
    private String name;
    private String cardBrand;
    private BigDecimal creditLimit;
}
