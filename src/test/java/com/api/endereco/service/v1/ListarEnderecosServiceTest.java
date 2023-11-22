package com.api.endereco.service.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.entity.dtos.ListarEnderecosRequestDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.repositories.EnderecoRepository;
import com.api.endereco.services.v1.ListarEnderecosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarEnderecosServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private ListarEnderecosService listarEnderecosService;

    private ListarEnderecosRequestDto listarEnderecosRequestDtoIdCliente;
    private ListarEnderecosRequestDto listarEnderecosRequestDtoIdFornecedor;
    private ListarEnderecosRequestDto listarEnderecosRequestDtoIdFuncionario;

    @BeforeEach
    void setUp () {
        enderecoRepository = mock(EnderecoRepository.class);
        listarEnderecosService = new ListarEnderecosService(enderecoRepository);

        listarEnderecosRequestDtoIdCliente = new ListarEnderecosRequestDto();
        listarEnderecosRequestDtoIdCliente.setIdCliente(String.valueOf(UUID.randomUUID()));

        listarEnderecosRequestDtoIdFornecedor = new ListarEnderecosRequestDto();
        listarEnderecosRequestDtoIdFornecedor.setIdFornecedor(String.valueOf(UUID.randomUUID()));

        listarEnderecosRequestDtoIdFuncionario = new ListarEnderecosRequestDto();
        listarEnderecosRequestDtoIdFuncionario.setIdFuncionario(String.valueOf(UUID.randomUUID()));
    }

    @Test
    public void testeListarEnderecosModel_Sucesso_IdCliente () {

        List<EnderecoModel> enderecoModelList = new ArrayList<>();
        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModelList.add(enderecoModel);
        Page<EnderecoModel> enderecoModelPage = new PageImpl<>(enderecoModelList);

        when(enderecoRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(enderecoModelPage);
        ResponseEntity<BaseDto<EnderecoModel>> resultado = listarEnderecosService.listarEnderecos(listarEnderecosRequestDtoIdCliente, "0");

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    public void testeListarEnderecosModel_Sucesso_IdFornecedor () {

        List<EnderecoModel> enderecoModelList = new ArrayList<>();
        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModelList.add(enderecoModel);
        Page<EnderecoModel> enderecoModelPage = new PageImpl<>(enderecoModelList);

        when(enderecoRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(enderecoModelPage);
        ResponseEntity<BaseDto<EnderecoModel>> resultado = listarEnderecosService.listarEnderecos(listarEnderecosRequestDtoIdFornecedor, "0");

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    public void testeListarEnderecosModel_Sucesso_IdFuncionario () {

        List<EnderecoModel> enderecoModelList = new ArrayList<>();
        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModelList.add(enderecoModel);
        Page<EnderecoModel> enderecoModelPage = new PageImpl<>(enderecoModelList);

        when(enderecoRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(enderecoModelPage);
        ResponseEntity<BaseDto<EnderecoModel>> resultado = listarEnderecosService.listarEnderecos(listarEnderecosRequestDtoIdFuncionario, "0");

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    public void testeListarEnderecosModel_Fracasso_DadosNaoEncontrados () {

        ListarEnderecosRequestDto listarEnderecosRequestDto = new ListarEnderecosRequestDto();
        listarEnderecosRequestDto.setIdCliente(String.valueOf(UUID.randomUUID()));

        List<EnderecoModel> enderecoModelList = new ArrayList<>();
        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModelList.add(enderecoModel);
        Page<EnderecoModel> enderecoModelPage = new PageImpl<>(enderecoModelList);

        when(enderecoRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Page.empty());
        ResponseEntity<BaseDto<EnderecoModel>> resultado = listarEnderecosService.listarEnderecos(listarEnderecosRequestDto, "0");

        assertEquals(HttpStatus.BAD_REQUEST, resultado.getStatusCode());
    }
}
