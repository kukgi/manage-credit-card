package br.com.e2dp.msclient.service;

import br.com.e2dp.msclient.exception.ClienteExeception;
import br.com.e2dp.msclient.model.dto.ClientDto;
import br.com.e2dp.msclient.model.entity.Client;
import br.com.e2dp.msclient.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Transactional
    public ClientDto save(ClientDto clientDto) throws ClienteExeception {
        ModelMapper modelMapper = new ModelMapper();
        Client clientEntity = null;
        if (Objects.nonNull(clientDto)) {
            val cliente = clientRepository.findByCpf(clientDto.getCpf());
            if (!cliente.isEmpty()) {
                log.error("cliente ja cadastrado");
                throw new ClienteExeception("cliente ja cadastrado");
            }
            clientEntity = modelMapper.map(clientDto, Client.class);
            clientRepository.save(clientEntity);
        }
        return modelMapper.map(clientEntity, ClientDto.class);
    }


    public List<ClientDto> getAllCliente() {
        ModelMapper modelMapper = new ModelMapper();
        List<ClientDto> optionalClient = null;
        val all = clientRepository.findAll();
        return modelMapper.map(all, List.class);
    }

    public Optional<ClientDto> getByCpf(String cpf) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Client> optionalClient = null;
        if (!cpf.isBlank() && !cpf.isEmpty()) {
            optionalClient = clientRepository.findByCpf(cpf);
        }
        return Optional.ofNullable(modelMapper.map(optionalClient, ClientDto.class));
    }
}
