package com.api.endereco.entity.dtos;

public class CadastrarEnderecoResponseDto {

    private String idEndereco;

    public CadastrarEnderecoResponseDto(String idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(String idEndereco) {
        this.idEndereco = idEndereco;
    }
}
