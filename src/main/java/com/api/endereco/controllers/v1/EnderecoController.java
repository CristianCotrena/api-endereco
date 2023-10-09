package com.api.endereco.controllers.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.entity.dtos.EnderecoRequestDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.services.v1.CadastrarEnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/endereco")
public class EnderecoController {

    CadastrarEnderecoService cadastrarEnderecoService;

    public EnderecoController(CadastrarEnderecoService cadastrarEnderecoService) {
        this.cadastrarEnderecoService = cadastrarEnderecoService;
    }

    @PostMapping
    public ResponseEntity<BaseDto<EnderecoModel>> cadastrarEndereco(@RequestBody EnderecoRequestDto enderecoRequestDto) {
        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        return ResponseEntity.status(baseDto.getResultado().getStatus()).body(baseDto);
    }
}
