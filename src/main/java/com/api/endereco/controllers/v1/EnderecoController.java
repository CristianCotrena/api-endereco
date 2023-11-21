package com.api.endereco.controllers.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.entity.dtos.AtualizarParcialmenteEnderecoRequestDto;
import com.api.endereco.entity.dtos.CadastrarEnderecoRequestDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.services.v1.AtualizarParcialmenteEnderecoService;
import com.api.endereco.services.v1.CadastrarEnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "API Endereço")
@RestController
@RequestMapping(value = "/v1/endereco")
public class EnderecoController {

    private final CadastrarEnderecoService cadastrarEnderecoService;
    private final AtualizarParcialmenteEnderecoService atualizarParcialmenteEnderecoService;

    @Autowired
    public EnderecoController(
            CadastrarEnderecoService cadastrarEnderecoService,
            AtualizarParcialmenteEnderecoService atualizarParcialmenteEnderecoService) {
        this.cadastrarEnderecoService = cadastrarEnderecoService;
        this.atualizarParcialmenteEnderecoService = atualizarParcialmenteEnderecoService;
    }

    @Operation(summary = "Cadastra um novo endereco.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereco cadastrado com sucesso.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Endereco cadastrado com sucesso.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Id Cliente já cadastrado.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Id Cliente já cadastrado.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Id Fornecedor já cadastrado.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Id Fornecedor já cadastrado.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Id Funcionário já cadastrado.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Id Funcionário já cadastrado.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Campo CEP fora do padrão.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Campo CEP fora do padrão.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Campo CEP inválido.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Campo CEP inválido.")
                    )
            }),
    })
    @PostMapping
    public ResponseEntity<BaseDto<EnderecoModel>> cadastrarEndereco(@RequestBody CadastrarEnderecoRequestDto cadastrarEnderecoRequestDto) {
        ResponseEntity<BaseDto<EnderecoModel>> resultado = cadastrarEnderecoService.cadastrarEndereco(cadastrarEnderecoRequestDto);
        return resultado;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseDto<EnderecoModel>> atualizarParcialmente(
            @PathVariable(value = "id") String id,
            @RequestBody AtualizarParcialmenteEnderecoRequestDto atualizarParcialmenteEnderecoRequestDto) {
        ResponseEntity<BaseDto<EnderecoModel>> resultado = atualizarParcialmenteEnderecoService.atualizarParcialmente(
                id,
                atualizarParcialmenteEnderecoRequestDto);
        return resultado;
    }
}
