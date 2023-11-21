package com.api.endereco.service.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.entity.dtos.AtualizarParcialmenteEnderecoRequestDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.mock.AtualizarEnderecoParcialmenteRequestDtoBuilder;
import com.api.endereco.mock.EnderecoModelBuilder;
import com.api.endereco.repositories.EnderecoRepository;
import com.api.endereco.services.v1.AtualizarParcialmenteEnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtualizarParcialmenteEnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Autowired
    private AtualizarParcialmenteEnderecoService atualizarParcialmenteEnderecoService;

    private AtualizarParcialmenteEnderecoRequestDto atualizarParcialmenteEnderecoRequestDto;
    private EnderecoModel enderecoModel;

    @BeforeEach
    void setUp () {
        enderecoRepository = mock(EnderecoRepository.class);
        atualizarParcialmenteEnderecoService = new AtualizarParcialmenteEnderecoService(enderecoRepository);

        atualizarParcialmenteEnderecoRequestDto = new AtualizarParcialmenteEnderecoRequestDto();
        atualizarParcialmenteEnderecoRequestDto = AtualizarEnderecoParcialmenteRequestDtoBuilder.build();
        enderecoModel = EnderecoModelBuilder.buildIdFuncionario();
    }

    @Test
    public void testeAtualizarClienteParcialmente_Sucesso () {

        when(enderecoRepository.existsById(enderecoModel.getId())).thenReturn(true);
        when(enderecoRepository.findById(enderecoModel.getId())).thenReturn(Optional.of(enderecoModel));

        ResponseEntity<BaseDto<EnderecoModel>> resultado = atualizarParcialmenteEnderecoService.atualizarParcialmente(
                enderecoModel.getId().toString(),
                atualizarParcialmenteEnderecoRequestDto
        );

        assertEquals(HttpStatus.ACCEPTED, resultado.getStatusCode());
        assertEquals("Atualizado com sucesso.", resultado.getBody().getResultado().getDescricao());
    }

    @Test
    public void testeAtualizarClienteParcialmente_Fracasso_IdNaoEncontrado () {

        when(enderecoRepository.existsById(enderecoModel.getId())).thenReturn(false);

        ResponseEntity<BaseDto<EnderecoModel>> resultado = atualizarParcialmenteEnderecoService.atualizarParcialmente(
                enderecoModel.getId().toString(),
                atualizarParcialmenteEnderecoRequestDto
        );

        assertEquals(HttpStatus.BAD_REQUEST, resultado.getStatusCode());
        assertEquals("id", resultado.getBody().getErros().get(0).getCampo());
        assertEquals("Dado não encontrado.", resultado.getBody().getErros().get(0).getMensagem());
    }
}
