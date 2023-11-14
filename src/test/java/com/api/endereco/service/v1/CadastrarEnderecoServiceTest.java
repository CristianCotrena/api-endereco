package com.api.endereco.service.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.entity.dtos.CadastrarEnderecoRequestDto;
import com.api.endereco.entity.dtos.CadastrarEnderecoResponseDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.mock.CadastrarEnderecoRequestDtoBuilder;
import com.api.endereco.mock.EnderecoModelBuilder;
import com.api.endereco.repositories.EnderecoRepository;
import com.api.endereco.services.v1.CadastrarEnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarEnderecoServiceTest {

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CadastrarEnderecoService cadastrarEnderecoService;

    private CadastrarEnderecoRequestDto cadastrarEnderecoRequestDtoCliente;
    private CadastrarEnderecoRequestDto cadastrarEnderecoRequestDtoFornecedor;
    private CadastrarEnderecoRequestDto cadastrarEnderecoRequestDtoFuncionario;
    private EnderecoModel enderecoModel;

    @BeforeEach
    void setUp() {
        enderecoRepository = mock(EnderecoRepository.class);
        cadastrarEnderecoService = new CadastrarEnderecoService(enderecoRepository);

        cadastrarEnderecoRequestDtoCliente = CadastrarEnderecoRequestDtoBuilder.criarIdCliente();
        cadastrarEnderecoRequestDtoFornecedor = CadastrarEnderecoRequestDtoBuilder.criarIdFornecedor();
        cadastrarEnderecoRequestDtoFuncionario = CadastrarEnderecoRequestDtoBuilder.criarIdFuncionario();
    }

    @Test
    public void testeCadastrarEnderecoIdCliente_Sucesso() {

        enderecoModel = EnderecoModelBuilder.buildIdCliente();
        when(enderecoRepository.save(any(EnderecoModel.class))).thenReturn(enderecoModel);
        ResponseEntity<BaseDto<EnderecoModel>> resposta = cadastrarEnderecoService.cadastrarEndereco(cadastrarEnderecoRequestDtoCliente);
        CadastrarEnderecoResponseDto cadastrarEnderecoResponseDto = new CadastrarEnderecoResponseDto(enderecoModel.getId().toString());

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(enderecoModel.getId().toString(), cadastrarEnderecoResponseDto.getIdEndereco());
        assertEquals("Cadastrado com sucesso.", resposta.getBody().getResultado().getDescricao());
    }

    @Test
    public void testeCadastrarEnderecoIdFornecedor_Sucesso() {

        enderecoModel = EnderecoModelBuilder.buildIdFornecedor();
        when(enderecoRepository.save(any(EnderecoModel.class))).thenReturn(enderecoModel);
        ResponseEntity<BaseDto<EnderecoModel>> resposta = cadastrarEnderecoService.cadastrarEndereco(cadastrarEnderecoRequestDtoFornecedor);
        CadastrarEnderecoResponseDto cadastrarEnderecoResponseDto = new CadastrarEnderecoResponseDto(enderecoModel.getId().toString());

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(enderecoModel.getId().toString(), cadastrarEnderecoResponseDto.getIdEndereco());
        assertEquals("Cadastrado com sucesso.", resposta.getBody().getResultado().getDescricao());
    }

    @Test
    public void testeCadastrarEnderecoIdFuncionario_Sucesso() {

        enderecoModel = EnderecoModelBuilder.buildIdFuncionario();
        when(enderecoRepository.save(any(EnderecoModel.class))).thenReturn(enderecoModel);
        ResponseEntity<BaseDto<EnderecoModel>> resposta = cadastrarEnderecoService.cadastrarEndereco(cadastrarEnderecoRequestDtoFuncionario);
        CadastrarEnderecoResponseDto cadastrarEnderecoResponseDto = new CadastrarEnderecoResponseDto(enderecoModel.getId().toString());

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(enderecoModel.getId().toString(), cadastrarEnderecoResponseDto.getIdEndereco());
        assertEquals("Cadastrado com sucesso.", resposta.getBody().getResultado().getDescricao());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteCepInvalido() {

        enderecoModel = EnderecoModelBuilder.buildIdCliente();
        cadastrarEnderecoRequestDtoCliente.setCep("00000000");
        enderecoModel.setCep(cadastrarEnderecoRequestDtoCliente.getCep());

        ResponseEntity<BaseDto<EnderecoModel>> resposta = cadastrarEnderecoService.cadastrarEndereco(cadastrarEnderecoRequestDtoCliente);
        List<BaseErrorDto> listaErros = resposta.getBody().getErros();

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("CEP", listaErros.get(0).getCampo());
        assertEquals("CEP inválido.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorCepInvalido() {

        enderecoModel = EnderecoModelBuilder.buildIdFornecedor();
        cadastrarEnderecoRequestDtoFornecedor.setCep("00000000");
        enderecoModel.setCep(cadastrarEnderecoRequestDtoFornecedor.getCep());

        ResponseEntity<BaseDto<EnderecoModel>> resposta = cadastrarEnderecoService.cadastrarEndereco(cadastrarEnderecoRequestDtoFornecedor);
        List<BaseErrorDto> listaErros = resposta.getBody().getErros();

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("CEP", listaErros.get(0).getCampo());
        assertEquals("CEP inválido.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioCepInvalido() {

        enderecoModel = EnderecoModelBuilder.buildIdFuncionario();
        cadastrarEnderecoRequestDtoFuncionario.setCep("00000000");
        enderecoModel.setCep(cadastrarEnderecoRequestDtoFuncionario.getCep());

        ResponseEntity<BaseDto<EnderecoModel>> resposta = cadastrarEnderecoService.cadastrarEndereco(cadastrarEnderecoRequestDtoFuncionario);
        List<BaseErrorDto> listaErros = resposta.getBody().getErros();

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("CEP", listaErros.get(0).getCampo());
        assertEquals("CEP inválido.", listaErros.get(0).getMensagem());
    }
}