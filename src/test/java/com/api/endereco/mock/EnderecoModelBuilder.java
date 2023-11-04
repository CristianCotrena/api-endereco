package com.api.endereco.mock;

import com.api.endereco.entity.models.EnderecoModel;

import java.util.UUID;

public class EnderecoModelBuilder {

    public static EnderecoModel buildIdCliente() {

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(UUID.randomUUID());
        enderecoModel.setIdCliente(UUID.randomUUID());
        enderecoModel.setCep("95032260");
        enderecoModel.setNumero(1333);
        enderecoModel.setComplemento("Prefeitura Caxias do Sul");

        return enderecoModel;
    }

    public static EnderecoModel buildIdFornecedor() {

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(UUID.randomUUID());
        enderecoModel.setIdCliente(UUID.randomUUID());
        enderecoModel.setCep("93548013");
        enderecoModel.setNumero(4201);
        enderecoModel.setComplemento("Prefeitura Novo Hamburgo");

        return enderecoModel;
    }

    public static EnderecoModel buildIdFuncionario() {

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(UUID.randomUUID());
        enderecoModel.setIdCliente(UUID.randomUUID());
        enderecoModel.setCep("90010030");
        enderecoModel.setNumero(157);
        enderecoModel.setComplemento("Prefeitura Porto Alegre");

        return enderecoModel;
    }
}
