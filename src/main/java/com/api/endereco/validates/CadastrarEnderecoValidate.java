package com.api.endereco.validates;

import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.constants.MensagensErros;
import com.api.endereco.constants.Regex;
import com.api.endereco.entity.dtos.EnderecoRequestDto;
import com.api.endereco.transformer.BuscarViaCep;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CadastrarEnderecoValidate {

    public List<BaseErrorDto> validate(EnderecoRequestDto enderecoRequestDto) {
        List<BaseErrorDto> erros = validarCamposRequeridos(enderecoRequestDto);
        return erros.size() > 0 ? erros : validarCamposInvalidos(enderecoRequestDto, erros);
    }

    public List<BaseErrorDto> validarCamposRequeridos(EnderecoRequestDto enderecoRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        {
            List<String> listaIds = new ArrayList<>();
            if (enderecoRequestDto.getIdCliente() != null && !enderecoRequestDto.getIdCliente().isEmpty()) {
                listaIds.add(enderecoRequestDto.getIdCliente());
            }
            if (enderecoRequestDto.getIdFuncionario() != null && !enderecoRequestDto.getIdFuncionario().isEmpty()) {
                listaIds.add(enderecoRequestDto.getIdFuncionario());
            }
            if (enderecoRequestDto.getIdFornecedor() != null && !enderecoRequestDto.getIdFornecedor().isEmpty()) {
                listaIds.add(enderecoRequestDto.getIdFornecedor());
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

        if (enderecoRequestDto.getCep() == null || enderecoRequestDto.getCep().isEmpty()) {
            erros.add(new BaseErrorDto("CEP.", MensagensErros.CAMPO_OBRIGATORIO));
        }
        if (enderecoRequestDto.getNumero() == null) {
            erros.add(new BaseErrorDto("Número.", MensagensErros.CAMPO_OBRIGATORIO));
        }
        return erros;
    }

    public List<BaseErrorDto> validarCamposInvalidos(
            EnderecoRequestDto enderecoRequestDto,
            List<BaseErrorDto> erros) {

        if ((enderecoRequestDto.getIdCliente() != null) && (!enderecoRequestDto.getIdCliente().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(enderecoRequestDto.getIdCliente()).matches()) {
                erros.add(new BaseErrorDto("idCliente", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if ((enderecoRequestDto.getIdFornecedor() != null) && (!enderecoRequestDto.getIdFornecedor().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(enderecoRequestDto.getIdFornecedor()).matches()) {
                erros.add(new BaseErrorDto("idFornecedor", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if ((enderecoRequestDto.getIdFuncionario() != null) && (!enderecoRequestDto.getIdFuncionario().isEmpty())) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(enderecoRequestDto.getIdFuncionario()).matches()) {
                erros.add(new BaseErrorDto("idFuncionario", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if (!(Pattern.compile(Regex.cep).matcher(enderecoRequestDto.getCep()).matches())) {
            erros.add(new BaseErrorDto("CEP.", MensagensErros.CAMPO_FORA_DO_PADRAO));
        }
        if (new BuscarViaCep().getErro() != null) {
            erros.add(new BaseErrorDto("CEP.", "CEP inexistente."));
        }
        return erros;
    }
}
