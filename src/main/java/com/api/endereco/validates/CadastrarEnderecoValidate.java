package com.api.endereco.validates;

import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.constants.MensagensErros;
import com.api.endereco.constants.Regex;
import com.api.endereco.entity.dtos.CadastrarEnderecoRequestDto;
import com.api.endereco.transformer.BuscarViaCep;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CadastrarEnderecoValidate {

    public List<BaseErrorDto> validate(CadastrarEnderecoRequestDto cadastrarEnderecoRequestDto) {
        List<BaseErrorDto> erros = validarCamposRequeridos(cadastrarEnderecoRequestDto);
        return erros.size() > 0 ? erros : validarCamposInvalidos(cadastrarEnderecoRequestDto, erros);
    }

    public List<BaseErrorDto> validarCamposRequeridos(CadastrarEnderecoRequestDto cadastrarEnderecoRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        {
            List<String> listaIds = new ArrayList<>();
            if (cadastrarEnderecoRequestDto.getIdCliente() != null && !cadastrarEnderecoRequestDto.getIdCliente().isEmpty()) {
                listaIds.add(cadastrarEnderecoRequestDto.getIdCliente());
            }
            if (cadastrarEnderecoRequestDto.getIdFuncionario() != null && !cadastrarEnderecoRequestDto.getIdFuncionario().isEmpty()) {
                listaIds.add(cadastrarEnderecoRequestDto.getIdFuncionario());
            }
            if (cadastrarEnderecoRequestDto.getIdFornecedor() != null && !cadastrarEnderecoRequestDto.getIdFornecedor().isEmpty()) {
                listaIds.add(cadastrarEnderecoRequestDto.getIdFornecedor());
            }
            if (listaIds.size() < 1) {
                erros.add(new BaseErrorDto("idCliente, idFornecedor, idFuncionario", "Um Id deve ser informado."));
            } else {
                int contador = 0;
                int id = 0;
                for (int i = 0; i < listaIds.size(); i++) {
                    if (!listaIds.get(i).isEmpty() && listaIds.get(i) != null) {
                        id = i;
                        if (contador > 0) {
                            erros.add(new BaseErrorDto("id", MensagensErros.APENAS_UM_ID));
                            break;
                        } else {
                            contador++;
                        }
                    }
                }
            }
        }

        if (cadastrarEnderecoRequestDto.getCep() == null || cadastrarEnderecoRequestDto.getCep().isEmpty()) {
            erros.add(new BaseErrorDto("CEP.", MensagensErros.CAMPO_OBRIGATORIO));
        }
        if (cadastrarEnderecoRequestDto.getNumero() == null) {
            erros.add(new BaseErrorDto("Número.", MensagensErros.CAMPO_OBRIGATORIO));
        }
        return erros;
    }

    public List<BaseErrorDto> validarCamposInvalidos(
            CadastrarEnderecoRequestDto cadastrarEnderecoRequestDto,
            List<BaseErrorDto> erros) {

        if ((cadastrarEnderecoRequestDto.getIdCliente() != null) && (!cadastrarEnderecoRequestDto.getIdCliente().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(cadastrarEnderecoRequestDto.getIdCliente()).matches()) {
                erros.add(new BaseErrorDto("idCliente", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if ((cadastrarEnderecoRequestDto.getIdFornecedor() != null) && (!cadastrarEnderecoRequestDto.getIdFornecedor().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(cadastrarEnderecoRequestDto.getIdFornecedor()).matches()) {
                erros.add(new BaseErrorDto("idFornecedor", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if ((cadastrarEnderecoRequestDto.getIdFuncionario() != null) && (!cadastrarEnderecoRequestDto.getIdFuncionario().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(cadastrarEnderecoRequestDto.getIdFuncionario()).matches()) {
                erros.add(new BaseErrorDto("idFuncionario", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if (!(Pattern.compile(Regex.cep).matcher(cadastrarEnderecoRequestDto.getCep()).matches())) {
            erros.add(new BaseErrorDto("CEP.", MensagensErros.CAMPO_FORA_DO_PADRAO));
        }
        if (new BuscarViaCep().getErro() != null) {
            erros.add(new BaseErrorDto("CEP.", "CEP inexistente."));
        }
        return erros;
    }
}
