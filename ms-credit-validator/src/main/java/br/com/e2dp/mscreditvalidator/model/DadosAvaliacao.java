package br.com.e2dp.mscreditvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DadosAvaliacao {

    private String cpf;
    private BigDecimal renda;
}
