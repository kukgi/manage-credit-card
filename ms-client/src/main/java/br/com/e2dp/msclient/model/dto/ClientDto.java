package br.com.e2dp.msclient.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientDto {
    private Long id;
    private String name;
    private String cpf;
    private Integer idade;
}
