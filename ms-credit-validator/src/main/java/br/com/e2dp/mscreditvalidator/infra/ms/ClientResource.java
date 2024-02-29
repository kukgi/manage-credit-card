package br.com.e2dp.mscreditvalidator.infra.ms;

import br.com.e2dp.mscreditvalidator.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ms-client", path = "/credit-service/api/v1/client")
public interface ClientResource {


    @GetMapping( "/find-by-cpf")
    ResponseEntity<Client> getDataClientByCpf(@RequestParam("cpf") String cpf);

}
