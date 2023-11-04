package com.api.endereco.service.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.base.dto.BaseResultDto;
import com.api.endereco.entity.dtos.EnderecoRequestDto;
import com.api.endereco.entity.dtos.EnderecoResponseDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.repositories.EnderecoRepository;
import com.api.endereco.services.v1.CadastrarEnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarEnderecoServiceTest {

    /**
    @MockBean
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CadastrarEnderecoService cadastrarEnderecoService;

    private EnderecoRequestDto enderecoRequestDto;
    private EnderecoModel enderecoModel;

    @BeforeEach
    void setUp() {
        enderecoRepository = mock(EnderecoRepository.class);
        cadastrarEnderecoService = new CadastrarEnderecoService(enderecoRepository);

        enderecoRequestDto = new EnderecoRequestDto();
        enderecoRequestDto.setCep("70150904");
        enderecoRequestDto.setNumero(1);
        enderecoRequestDto.setComplemento("Palácio do Planalto");
    }

    @Test
    public void testeCadastrarEnderecoIdCliente_Sucesso() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.empty());
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        when(enderecoRepository.save(any(EnderecoModel.class))).thenReturn(enderecoModel);

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        BaseResultDto cadastradoComSucesso = new BaseResultDto(
                baseDto.getResultado().getStatus(),
                baseDto.getResultado().getDescricao());

        assertEquals(HttpStatus.CREATED.value(), baseDto.getResultado().getStatus());
        assertEquals("Cadastrado com sucesso.", baseDto.getResultado().getDescricao());
        assertEquals(HttpStatus.CREATED.value(), cadastradoComSucesso.getStatus());
        assertEquals(enderecoModel.getId().toString(), enderecoResponseDto.getIdEndereco());
        assertEquals("Cadastrado com sucesso.", cadastradoComSucesso.getDescricao());
    }

    @Test
    public void testeCadastrarEnderecoIdFornecedor_Sucesso() {
        UUID idEndereco = UUID.randomUUID();
        UUID idForncedor = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idForncedor);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.empty());
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        when(enderecoRepository.save(any(EnderecoModel.class))).thenReturn(enderecoModel);

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        BaseResultDto cadastradoComSucesso = new BaseResultDto(
                baseDto.getResultado().getStatus(),
                baseDto.getResultado().getDescricao());

        assertEquals(HttpStatus.CREATED.value(), baseDto.getResultado().getStatus());
        assertEquals("Cadastrado com sucesso.", baseDto.getResultado().getDescricao());
        assertEquals(HttpStatus.CREATED.value(), cadastradoComSucesso.getStatus());
        assertEquals(enderecoModel.getId().toString(), enderecoResponseDto.getIdEndereco());
        assertEquals("Cadastrado com sucesso.", cadastradoComSucesso.getDescricao());
    }

    @Test
    public void testeCadastrarEnderecoIdFuncionario_Sucesso() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.empty());
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        when(enderecoRepository.save(any(EnderecoModel.class))).thenReturn(enderecoModel);

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        BaseResultDto cadastradoComSucesso = new BaseResultDto(
                baseDto.getResultado().getStatus(),
                baseDto.getResultado().getDescricao());

        assertEquals(HttpStatus.CREATED.value(), baseDto.getResultado().getStatus());
        assertEquals("Cadastrado com sucesso.", baseDto.getResultado().getDescricao());
        assertEquals(HttpStatus.CREATED.value(), cadastradoComSucesso.getStatus());
        assertEquals(enderecoModel.getId().toString(), enderecoResponseDto.getIdEndereco());
        assertEquals("Cadastrado com sucesso.", cadastradoComSucesso.getDescricao());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_SemIdAssociado() {
        UUID idEndereco = UUID.randomUUID();

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id.", listaErros.get(0).getCampo());
        assertEquals("Um Id deve ser informado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteMaisIdFornecedor() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        enderecoRequestDto.setIdFornecedor(idFornecedor);
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id.", listaErros.get(0).getCampo());
        assertEquals("Apenas um ID é aceito.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteMaisIdFuncionario() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        enderecoRequestDto.setIdFuncionario(idFuncionario);
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id.", listaErros.get(0).getCampo());
        assertEquals("Apenas um ID é aceito.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorMaisIdCliente() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idFornecedor);
        enderecoRequestDto.setIdCliente(idCliente);
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id.", listaErros.get(0).getCampo());
        assertEquals("Apenas um ID é aceito.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorMaisIdFuncionario() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idFornecedor);
        enderecoRequestDto.setIdFuncionario(idFuncionario);
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id.", listaErros.get(0).getCampo());
        assertEquals("Apenas um ID é aceito.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioMaisIdCliente() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        enderecoRequestDto.setIdCliente(idCliente);
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id.", listaErros.get(0).getCampo());
        assertEquals("Apenas um ID é aceito.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioMaisIdFornecedor() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        enderecoRequestDto.setIdFornecedor(idFornecedor);
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id.", listaErros.get(0).getCampo());
        assertEquals("Apenas um ID é aceito.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteJaUtilizadoIdCliente() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Cliente.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteJaUtilizadoIdFornecedor() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Cliente.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteJaUtilizadoIdFuncionario() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Cliente.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorJaUtilizadoIdCliente() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idFornecedor);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Fornecedor.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorJaUtilizadoIdFornecedor() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idFornecedor);
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Fornecedor.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorJaUtilizadoIdFuncionario() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idFornecedor);
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Fornecedor.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioJaUtilizadoIdCliente() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Funcionário.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioJaUtilizadoIdFornecedor() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Funcionário.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioJaUtilizadoIdFuncionario() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Funcionário.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteCepInvalido() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.empty());
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoRequestDto.setCep("00000000");
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("CEP", listaErros.get(0).getCampo());
        assertEquals("CEP inválido.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorCepInvalido() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idFornecedor);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.empty());
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoRequestDto.setCep("00000000");
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("CEP", listaErros.get(0).getCampo());
        assertEquals("CEP inválido.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioCepInvalido() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.empty());
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.empty());
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoRequestDto.setCep("00000000");
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("CEP", listaErros.get(0).getCampo());
        assertEquals("CEP inválido.", listaErros.get(0).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdClienteJaCadastradoCepInvalido() {
        UUID idEndereco = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();

        enderecoRequestDto.setIdCliente(idCliente);
        when(enderecoRepository.existsByIdCliente(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoRequestDto.setCep("00000000");
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Cliente.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
        assertEquals("CEP", listaErros.get(1).getCampo());
        assertEquals("CEP inválido.", listaErros.get(1).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFornecedorJaUtilizadoCepInvalido() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFornecedor = UUID.randomUUID();

        enderecoRequestDto.setIdFornecedor(idFornecedor);
        when(enderecoRepository.existsByIdFornecedor(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoRequestDto.setCep("00000000");
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Fornecedor.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
        assertEquals("CEP", listaErros.get(1).getCampo());
        assertEquals("CEP inválido.", listaErros.get(1).getMensagem());
    }

    @Test
    public void testeCadastrarEndereco_Fracasso_IdFuncionarioJaCadastradoCepInvalido() {
        UUID idEndereco = UUID.randomUUID();
        UUID idFuncionario = UUID.randomUUID();

        enderecoRequestDto.setIdFuncionario(idFuncionario);
        when(enderecoRepository.existsByIdFuncionario(any(UUID.class))).thenReturn(Optional.of(true));
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(idEndereco);
        enderecoRequestDto.setCep("00000000");
        enderecoModel.setCep(enderecoRequestDto.getCep());

        BaseDto baseDto = cadastrarEnderecoService.cadastrarEndereco(enderecoRequestDto);
        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(enderecoModel.getId().toString());
        List<BaseErrorDto> listaErros = baseDto.getErros();

        assertEquals(HttpStatus.BAD_REQUEST.value(), baseDto.getResultado().getStatus());
        assertEquals("Bad Request", baseDto.getResultado().getDescricao());
        assertEquals("Id Funcionário.", listaErros.get(0).getCampo());
        assertEquals("Já cadastrado.", listaErros.get(0).getMensagem());
        assertEquals("CEP", listaErros.get(1).getCampo());
        assertEquals("CEP inválido.", listaErros.get(1).getMensagem());
    }
    **/
}