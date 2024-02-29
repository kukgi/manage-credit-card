package br.com.e2dp.mscards.service;

import br.com.e2dp.mscards.model.dto.ClientCardDto;
import br.com.e2dp.mscards.model.entity.ClientCard;
import br.com.e2dp.mscards.repository.ClientCardRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ClientCardService {

    @Autowired
    private ClientCardRepository repository;

    public List<ClientCardDto> listCardsByCpf(String cpf) {
        List<ClientCard> listClientCard = repository.findByCpf(cpf);
        List<ClientCardDto> listClietCardDto = listClientCard.stream()
                .map(ClientCardDto::fromModel)
                .collect(Collectors.toList());
        return listClietCardDto;
    }
}
