package com.api.endereco.transformer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EnderecoBuscarCep {

    public static BuscarViaCep recebendoEndereco(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://viacep.com.br/ws/");
        stringBuilder.append(cep);
        stringBuilder.append("/json/");
        ResponseEntity<BuscarViaCep> cepBuscado = restTemplate.getForEntity(stringBuilder.toString(), BuscarViaCep.class);
        return cepBuscado.getBody();
    }
}
