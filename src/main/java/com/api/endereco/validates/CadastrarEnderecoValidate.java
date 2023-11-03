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
            if (enderecoRequestDto.getIdCliente() != null && !enderecoRequestDto.getIdCliente().toString().isEmpty()) {
                listaIds.add(enderecoRequestDto.getIdCliente().toString());
            }
            if (enderecoRequestDto.getIdFuncionario() != null && !enderecoRequestDto.getIdFuncionario().toString().isEmpty()) {
                listaIds.add(enderecoRequestDto.getIdFuncionario().toString());
            }
            if (enderecoRequestDto.getIdFornecedor() != null && !enderecoRequestDto.getIdFornecedor().toString().isEmpty()) {
                listaIds.add(enderecoRequestDto.getIdFornecedor().toString());
            }
            if (listaIds.size() < 1) {
                erros.add(new BaseErrorDto("Id.", "Um Id deve ser informado."));
            } else {
                int contador = 0;
                int id = 0;
                for (int i = 0; i < listaIds.size(); i++) {
                    if (!listaIds.get(i).isEmpty() && listaIds.get(i) != null) {
                        id = i;
                        if (contador > 0) {
                            erros.add(new BaseErrorDto("Id.", MensagensErros.APENAS_UM_ID));
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
        if (!(Pattern.compile(Regex.cep).matcher(enderecoRequestDto.getCep()).matches())) {
            erros.add(new BaseErrorDto("CEP.", MensagensErros.CAMPO_FORA_DO_PADRAO));
        }
        if (new BuscarViaCep().getErro() != null) {
            erros.add(new BaseErrorDto("CEP.", "CEP inexistente."));
        }
        return erros;
    }
}
