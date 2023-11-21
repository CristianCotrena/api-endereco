package com.api.endereco.mock;

import com.api.endereco.entity.dtos.AtualizarParcialmenteEnderecoRequestDto;

public class AtualizarEnderecoParcialmenteRequestDtoBuilder {

    public static AtualizarParcialmenteEnderecoRequestDto build () {

        var request = new AtualizarParcialmenteEnderecoRequestDto();
        request.setNumero("23");
        request.setComplemento("Complemento.");
        request.setCep("88960000");
        return request;
    }
}
