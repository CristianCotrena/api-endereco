package com.api.endereco.entity.dtos;

public class AtualizarParcialmenteEnderecoRequestDto {

    private String cep;
    private String complemento;
    private String numero;

    public AtualizarParcialmenteEnderecoRequestDto() {
    }

    public AtualizarParcialmenteEnderecoRequestDto(
            String cep,
            String complemento,
            String numero) {
        this.cep = cep;
        this.complemento = complemento;
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
