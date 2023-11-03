package com.api.endereco.entity.dtos;

public class EnderecoResponseDto {

    private String idEndereco;

    public EnderecoResponseDto(String idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(String idEndereco) {
        this.idEndereco = idEndereco;
    }
}
