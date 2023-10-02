package com.api.endereco.base.dto;

import java.util.List;

public class BaseDto<T> {

    private T dados;
    private List<BaseErrorDto> erros;
    private BaseResultDto resultado;
}
