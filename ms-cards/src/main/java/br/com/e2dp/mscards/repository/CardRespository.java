package br.com.e2dp.mscards.repository;

import br.com.e2dp.mscards.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRespository extends JpaRepository<Card, Long> {

    public List<Card> findByMonthlyIncomeLessThanEqual(BigDecimal income);
}
