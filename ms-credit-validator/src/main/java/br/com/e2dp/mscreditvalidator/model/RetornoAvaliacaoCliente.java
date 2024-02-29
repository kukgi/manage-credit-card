package br.com.e2dp.mscreditvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RetornoAvaliacaoCliente {

        private List<CardAprovado> listCardsAprovados;
}
