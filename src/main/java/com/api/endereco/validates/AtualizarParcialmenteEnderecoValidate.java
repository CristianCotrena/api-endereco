package com.api.endereco.validates;

import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.constants.MensagensErros;
import com.api.endereco.constants.Regex;
import com.api.endereco.entity.dtos.AtualizarParcialmenteEnderecoRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AtualizarParcialmenteEnderecoValidate {
    public List<BaseErrorDto> validar(
            String id,
            AtualizarParcialmenteEnderecoRequestDto atualizarParcialmenteEnderecoRequestDto) {

        List<BaseErrorDto> erros = new ArrayList<>();

        if (id != null && !id.isEmpty()) {
            if (!Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(id).matches()) {
                erros.add(new BaseErrorDto("id", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if (atualizarParcialmenteEnderecoRequestDto.getCep() != null && !atualizarParcialmenteEnderecoRequestDto.getCep().isEmpty()) {
            if (!Pattern.compile(Regex.cep).matcher(atualizarParcialmenteEnderecoRequestDto.getCep()).matches()) {
                erros.add(new BaseErrorDto("cep", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        if (atualizarParcialmenteEnderecoRequestDto.getNumero() != null && !atualizarParcialmenteEnderecoRequestDto.getNumero().isEmpty()) {
            if (ehUmNumeroInteiro(atualizarParcialmenteEnderecoRequestDto.getNumero()) == false) {
                erros.add(new BaseErrorDto("numero", MensagensErros.CAMPO_FORA_DO_PADRAO));
            }
        }
        return erros;
    }

    private static boolean ehUmNumeroInteiro (String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
