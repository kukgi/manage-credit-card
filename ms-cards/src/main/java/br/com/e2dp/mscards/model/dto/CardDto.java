package br.com.e2dp.mscards.model.dto;

import br.com.e2dp.mscards.model.entity.Card;
import br.com.e2dp.mscards.model.entity.enums.CardBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDto {
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;
    private BigDecimal monthlyIncome;
    private BigDecimal creditLimit;
}
