package br.com.e2dp.msclient.api.v1.controller;


import br.com.e2dp.msclient.exception.ClienteExeception;
import br.com.e2dp.msclient.model.dto.ClientDto;
import br.com.e2dp.msclient.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/credit-service/api/v1/client")
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final ClientService service;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de cliente");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto clientDto) throws ClienteExeception {
        val clientDtoResponse = service.save(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDtoResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getDataAllCliente()  {
        val clientDtoByCpf = service.getAllCliente();
        if (clientDtoByCpf.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientDtoByCpf);
    }

    @GetMapping("/find-by-cpf")
    public ResponseEntity<ClientDto> getDataClientByCpf(@RequestParam("cpf") String cpf)  {
        val clientDtoByCpf = service.getByCpf(cpf);
        if (clientDtoByCpf.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientDtoByCpf.get());
    }
}
