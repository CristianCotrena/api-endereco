package com.api.endereco.entity.dtos;

public class ListarEnderecosRequestDto {

    private String idCliente;
    private String idFornecedor;
    private String idFuncionario;

    public ListarEnderecosRequestDto() {
    }

    public ListarEnderecosRequestDto(
            String idCliente,
            String idFornecedor,
            String idFuncionario) {
        this.idCliente = idCliente;
        this.idFornecedor = idFornecedor;
        this.idFuncionario = idFuncionario;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
