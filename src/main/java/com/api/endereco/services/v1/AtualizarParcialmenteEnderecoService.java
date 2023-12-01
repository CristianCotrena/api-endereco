package com.api.endereco.services.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.builder.ResponseErrorBuilder;
import com.api.endereco.builder.ResponseSuccessBuilder;
import com.api.endereco.constants.MensagensErros;
import com.api.endereco.entity.dtos.AtualizarParcialmenteEnderecoRequestDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.repositories.EnderecoRepository;
import com.api.endereco.transformer.EnderecoModelTransform;
import com.api.endereco.validates.AtualizarParcialmenteEnderecoValidate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtualizarParcialmenteEnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public AtualizarParcialmenteEnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public ResponseEntity atualizarParcialmente(
            String id,
            AtualizarParcialmenteEnderecoRequestDto atualizarParcialmenteEnderecoRequestDto) {

        List<BaseErrorDto> erros = new AtualizarParcialmenteEnderecoValidate().validar(id, atualizarParcialmenteEnderecoRequestDto);
        ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
        if (!erros.isEmpty()) {
            return resultado.getResultado();
        }

        UUID uuid = UUID.fromString(id);
        if (!enderecoRepository.existsById(uuid)) {
            erros.add(new BaseErrorDto("id", MensagensErros.NAO_ENCONTRADO));
            return resultado.getResultado();
        }

        Optional<EnderecoModel> enderecoExistente = enderecoRepository.findById(uuid);
        EnderecoModel enderecoModel = enderecoExistente.get();
        enderecoModel = new EnderecoModelTransform().transformarEnderecoParcialmente(atualizarParcialmenteEnderecoRequestDto, enderecoModel);

        ResponseSuccessBuilder atualizadoComSucesso = new ResponseSuccessBuilder<AtualizarParcialmenteEnderecoRequestDto>(
                HttpStatus.ACCEPTED,
                null,
                "Atualizado com sucesso."
        );
        return atualizadoComSucesso.getResultado();
    }
}
