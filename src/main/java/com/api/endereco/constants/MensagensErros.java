package com.api.endereco.constants;

import com.api.endereco.base.dto.BaseErrorDto;

import java.util.List;

public @interface MensagensErros {

    String CAMPO_OBRIGATORIO = "Campo obrigatorio.";
    String APENAS_UM_ID = "Apenas um ID é aceito.";
    String CAMPO_FORA_DO_PADRAO = "Campo fora do padrão.";
    String DADO_JA_CADASTRADO = "Já cadastrado.";
    String PAGINA_INCORRETA = "Página incorreta.";
    String DADOS_NAO_ENCONTRADOS = "Dados não encontrados.";
}
