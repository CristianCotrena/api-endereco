package com.api.endereco.mock;

import com.api.endereco.entity.dtos.CadastrarEnderecoRequestDto;

import java.util.UUID;

public class CadastrarEnderecoRequestDtoBuilder {

    public static CadastrarEnderecoRequestDto criarIdCliente() {

        var request = new CadastrarEnderecoRequestDto();
        request.setIdCliente(UUID.randomUUID().toString());
        request.setIdFornecedor(null);
        request.setIdFuncionario(null);
        request.setNumero(23);
        request.setComplemento("Apartamento");
        request.setCep("93540200");
        return request;
    }

    public static CadastrarEnderecoRequestDto criarIdFornecedor() {

        var request = new CadastrarEnderecoRequestDto();
        request.setIdCliente(null);
        request.setIdFornecedor(UUID.randomUUID().toString());
        request.setIdFuncionario(null);
        request.setNumero(23);
        request.setComplemento("Apartamento");
        request.setCep("93540200");
        return request;
    }

    public static CadastrarEnderecoRequestDto criarIdFuncionario() {

        var request = new CadastrarEnderecoRequestDto();
        request.setIdCliente(null);
        request.setIdFornecedor(null);
        request.setIdFuncionario(UUID.randomUUID().toString());
        request.setNumero(23);
        request.setComplemento("Apartamento");
        request.setCep("93540200");
        return request;
    }
}
