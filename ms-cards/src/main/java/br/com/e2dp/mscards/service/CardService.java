package br.com.e2dp.mscards.service;

import br.com.e2dp.mscards.model.dto.CardDto;
import br.com.e2dp.mscards.model.entity.Card;
import br.com.e2dp.mscards.repository.CardRespository;
import com.netflix.discovery.provider.Serializer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardRespository respository;


    @Transactional
    public CardDto saveCard(CardDto cardDto) {
        ModelMapper modelMapper = new ModelMapper();
        Card card= null;
        if (Objects.nonNull(cardDto)) {
            card = modelMapper.map(cardDto, Card.class);
            respository.save(card);
        }
        return modelMapper.map(card, CardDto.class);
    }

    /**
     * Aqui estamos utilizando o método map para transformar cada elemento da lista
     * cardsIncomeLessThanEqual em um objeto do tipo CardDto usando o modelMapper.
     * Em seguida, estamos coletando os resultados usando collect para criar uma nova lista de CardDto.
     * Isso elimina a necessidade do forEach e cria um código mais conciso e eficiente.
     *
     * @param income
     * @return List<CardDto>
     */
    public List<CardDto> getCardsIncomeLessThanEqual(BigDecimal income) {
        if (income.compareTo(BigDecimal.ZERO) <= 0) {
            //TODO exception...
        }
        List<Card> cardsIncomeLessThanEqual = respository.findByMonthlyIncomeLessThanEqual(income);
        ModelMapper modelMapper = new ModelMapper();
        List<CardDto> listCardDtos = cardsIncomeLessThanEqual.stream()
                .map(item -> modelMapper.map(item, CardDto.class))
                .collect(Collectors.toList());
        return listCardDtos;
    }

}
