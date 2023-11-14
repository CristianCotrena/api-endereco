package com.api.endereco.validates;

import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.constants.MensagensErros;
import com.api.endereco.entity.dtos.ListarEnderecosRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ListarEnderecosValidate {
    public List<BaseErrorDto> validarParametros(ListarEnderecosRequestDto listarEnderecosRequestDto, String pagina) {

        List<BaseErrorDto> erros = new ArrayList<>();

        if (
                (listarEnderecosRequestDto.getIdCliente() != null && !listarEnderecosRequestDto.getIdCliente().isEmpty())
                        && (listarEnderecosRequestDto.getIdFornecedor() != null && !listarEnderecosRequestDto.getIdFornecedor().isEmpty())
                        && (listarEnderecosRequestDto.getIdFuncionario() != null && !listarEnderecosRequestDto.getIdFuncionario().isEmpty())
        ) {
            erros.add(new BaseErrorDto("idCliente, idFornecedor, idFuncionario", MensagensErros.APENAS_UM_ID));
            return erros;
        }
        if (
                (listarEnderecosRequestDto.getIdCliente() != null && !listarEnderecosRequestDto.getIdCliente().isEmpty())
                        && (listarEnderecosRequestDto.getIdFornecedor() != null && !listarEnderecosRequestDto.getIdFornecedor().isEmpty())
        ) {
            erros.add(new BaseErrorDto("idCliente, idFornecedor", MensagensErros.APENAS_UM_ID));
            return erros;
        }
        if (
                (listarEnderecosRequestDto.getIdCliente() != null && !listarEnderecosRequestDto.getIdCliente().isEmpty())
                        && (listarEnderecosRequestDto.getIdFuncionario() != null && !listarEnderecosRequestDto.getIdFuncionario().isEmpty())
        ) {
            erros.add(new BaseErrorDto("idCliente, idFuncionario", MensagensErros.APENAS_UM_ID));
            return erros;
        }
        if (
                (listarEnderecosRequestDto.getIdFornecedor() != null && !listarEnderecosRequestDto.getIdFornecedor().isEmpty())
                        && (listarEnderecosRequestDto.getIdFuncionario() != null && !listarEnderecosRequestDto.getIdFuncionario().isEmpty())
        ) {
            erros.add(new BaseErrorDto("idFornecedor, idFuncionario", MensagensErros.APENAS_UM_ID));
            return erros;
        }
        if (
                (listarEnderecosRequestDto.getIdCliente() == null || listarEnderecosRequestDto.getIdCliente().isEmpty())
                        && (listarEnderecosRequestDto.getIdFornecedor() == null || listarEnderecosRequestDto.getIdFornecedor().isEmpty())
                        && (listarEnderecosRequestDto.getIdFuncionario() == null || listarEnderecosRequestDto.getIdFuncionario().isEmpty())
        ) {
            erros.add(new BaseErrorDto("idCliente, idFornecedor, idFuncionario", MensagensErros.CAMPO_OBRIGATORIO));
            return erros;
        }

        if (listarEnderecosRequestDto.getIdCliente() != null && !listarEnderecosRequestDto.getIdCliente().isEmpty()) {
            if (verificaId(listarEnderecosRequestDto.getIdCliente()) == false) {
                erros.add(new BaseErrorDto("idCliente", MensagensErros.CAMPO_FORA_DO_PADRAO));
                return erros;
            }
        }
        if (listarEnderecosRequestDto.getIdFornecedor() != null && !listarEnderecosRequestDto.getIdFornecedor().isEmpty()) {
            if (verificaId(listarEnderecosRequestDto.getIdFornecedor()) == false) {
                erros.add(new BaseErrorDto("idFornecedor", MensagensErros.CAMPO_FORA_DO_PADRAO));
                return erros;
            }
        }
        if (listarEnderecosRequestDto.getIdFuncionario() != null && !listarEnderecosRequestDto.getIdFuncionario().isEmpty()) {
            if (verificaId(listarEnderecosRequestDto.getIdFuncionario()) == false) {
                erros.add(new BaseErrorDto("idFuncionario", MensagensErros.CAMPO_FORA_DO_PADRAO));
                return erros;
            }
        }

        if (pagina != null || !pagina.isEmpty()) {
            int numeroPagina = Integer.parseInt(pagina);
            if (numeroPagina < 0) {
                erros.add(new BaseErrorDto("pagina", MensagensErros.PAGINA_INCORRETA));
            }
            return erros;
        }
        return null;
    }

    public boolean verificaId (String id) {

        if (Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(id).matches()) {
            return true;
        } else {
            return false;
        }
    }
}
