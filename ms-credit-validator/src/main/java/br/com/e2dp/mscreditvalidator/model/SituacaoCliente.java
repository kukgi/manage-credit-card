package br.com.e2dp.mscreditvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SituacaoCliente {
    private Client cliente;
    private List<CartaoCliente> cartoes;
}
