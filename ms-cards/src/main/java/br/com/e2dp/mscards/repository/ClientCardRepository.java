package br.com.e2dp.mscards.repository;

import br.com.e2dp.mscards.model.entity.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {

    public List<ClientCard> findByCpf(String cpf);
}
