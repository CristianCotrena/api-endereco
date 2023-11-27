package com.api.endereco.controllers.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.entity.dtos.AtualizarParcialmenteEnderecoRequestDto;
import com.api.endereco.entity.dtos.CadastrarEnderecoRequestDto;
import com.api.endereco.entity.dtos.ListarEnderecosRequestDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.services.v1.AtualizarParcialmenteEnderecoService;
import com.api.endereco.services.v1.CadastrarEnderecoService;
import com.api.endereco.services.v1.ListarEnderecosService;
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
    private final ListarEnderecosService listarEnderecosService;

    @Autowired
    public EnderecoController(
            CadastrarEnderecoService cadastrarEnderecoService,
            AtualizarParcialmenteEnderecoService atualizarParcialmenteEnderecoService,
            ListarEnderecosService listarEnderecosService) {
        this.cadastrarEnderecoService = cadastrarEnderecoService;
        this.atualizarParcialmenteEnderecoService = atualizarParcialmenteEnderecoService;
        this.listarEnderecosService = listarEnderecosService;
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

    @Operation(summary = "Atualizar parcialmente um endereço já existente.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Endereço atualizado com sucesso.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Endereço atualizado com sucesso.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Id informado fora do padrão.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Id informado fora do padrão.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Campo número fora do padrão.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Campo número fora do padrão.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Campo CEP fora do padrão.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Campo CEP fora do padrão.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Id não existente na base de dados.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Id não existente na base de dados.")
                    )
            })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BaseDto<EnderecoModel>> atualizarParcialmente(
            @PathVariable(value = "id") String id,
            @RequestBody AtualizarParcialmenteEnderecoRequestDto atualizarParcialmenteEnderecoRequestDto) {
        ResponseEntity<BaseDto<EnderecoModel>> resultado = atualizarParcialmenteEnderecoService.atualizarParcialmente(
                id,
                atualizarParcialmenteEnderecoRequestDto);
        return resultado;
    }

    @Operation(summary = "Listar enderecos de um id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Listado com sucesso.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Listado com sucesso.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Somente um id deve ser informado.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Somente um id deve ser informado.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Campo Id é obrigatório.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Campo Id é obrigatório.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Id fora do padrão UUID.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Id fora do padrão UUID.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Pagina incorreta.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Pagina incorreta.")
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Id não encontrado.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Id não encontrado.")
                    )
            }),
    })
    @GetMapping
    public ResponseEntity<BaseDto<EnderecoModel>> listarEnderecos (
            ListarEnderecosRequestDto listarEnderecosRequestDto,
            @RequestParam(required = false) String pagina
            ) {
        ResponseEntity<BaseDto<EnderecoModel>> resultado = listarEnderecosService.listarEnderecos(listarEnderecosRequestDto, pagina);
        return resultado;
    }
}
