package com.api.endereco.transformer;

import com.api.endereco.entity.dtos.EnderecoRequestDto;
import com.api.endereco.entity.models.EnderecoModel;

public class EnderecoModelTransform {

    public EnderecoModel transformarParaEnderecoModel(EnderecoRequestDto dto) {
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setIdCliente(dto.getIdCliente());
        enderecoModel.setIdFuncionario(dto.getIdFuncionario());
        enderecoModel.setIdFornecedor(dto.getIdFornecedor());
        enderecoModel.setCep(dto.getCep());

        BuscarViaCep buscarViaCep = EnderecoBuscarCep.recebendoEndereco(dto.getCep());
        enderecoModel.setNumero(dto.getNumero());
        enderecoModel.setStatus(1);
        enderecoModel.setRua(buscarViaCep.getLogradouro());
        enderecoModel.setBairro(buscarViaCep.getBairro());
        enderecoModel.setCidade(buscarViaCep.getLocalidade());
        enderecoModel.setEstado(buscarViaCep.getUf());
        return enderecoModel;
    }

    public boolean verificarErroCep(EnderecoRequestDto dto) {
        BuscarViaCep buscarViaCep = EnderecoBuscarCep.recebendoEndereco(dto.getCep());
        if (buscarViaCep.getErro() != null) {
            return false;
        } else {
            return true;
        }
    }
}
