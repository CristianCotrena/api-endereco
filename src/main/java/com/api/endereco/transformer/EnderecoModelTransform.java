package com.api.endereco.transformer;

import com.api.endereco.entity.dtos.AtualizarParcialmenteEnderecoRequestDto;
import com.api.endereco.entity.dtos.CadastrarEnderecoRequestDto;
import com.api.endereco.entity.models.EnderecoModel;

import java.util.UUID;

public class EnderecoModelTransform {

    public EnderecoModel transformarParaEnderecoModel(CadastrarEnderecoRequestDto dto) {
        EnderecoModel enderecoModel = new EnderecoModel();
        if (dto.getIdCliente() != null && !dto.getIdCliente().isEmpty()) {
            enderecoModel.setIdCliente(UUID.fromString(dto.getIdCliente()));
        }
        if (dto.getIdFornecedor() != null && !dto.getIdFornecedor().isEmpty()) {
            enderecoModel.setIdFuncionario(UUID.fromString(dto.getIdFornecedor()));
        }
        if (dto.getIdFuncionario() != null && !dto.getIdFuncionario().isEmpty()) {
            enderecoModel.setIdFornecedor(UUID.fromString(dto.getIdFuncionario()));
        }
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

    public boolean verificarErroCep(CadastrarEnderecoRequestDto dto) {
        BuscarViaCep buscarViaCep = EnderecoBuscarCep.recebendoEndereco(dto.getCep());
        if (buscarViaCep.getErro() != null) {
            return false;
        } else {
            return true;
        }
    }

    public EnderecoModel transformarEnderecoParcialmente(
            AtualizarParcialmenteEnderecoRequestDto dto,
            EnderecoModel enderecoModel) {

        if (dto.getCep() != null && !dto.getCep().isEmpty()) {
            enderecoModel.setCep(dto.getCep());
        } else {
            dto.setCep(enderecoModel.getCep());
        }

        BuscarViaCep buscarViaCep = EnderecoBuscarCep.recebendoEndereco(dto.getCep());
        enderecoModel.setRua(buscarViaCep.getLocalidade());
        enderecoModel.setBairro(buscarViaCep.getBairro());
        enderecoModel.setCidade(buscarViaCep.getLocalidade());
        enderecoModel.setEstado(buscarViaCep.getUf());

        if (dto.getNumero() != null && !dto.getNumero().isEmpty()) {
            enderecoModel.setNumero(Integer.parseInt(dto.getNumero()));
        } else {
            dto.setNumero(enderecoModel.getNumero().toString());
        }

        if (dto.getComplemento() != null && !dto.getComplemento().isEmpty()) {
            enderecoModel.setComplemento(dto.getComplemento());
        } else {
            dto.setComplemento(dto.getComplemento());
        }
        return enderecoModel;
    }
}
