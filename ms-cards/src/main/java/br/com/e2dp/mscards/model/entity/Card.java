package br.com.e2dp.mscards.model.entity;

import br.com.e2dp.mscards.model.entity.enums.CardBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;
    private BigDecimal monthlyIncome;
    private BigDecimal creditLimit;
}
