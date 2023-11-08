package com.api.endereco.services.v1;

import com.api.endereco.base.dto.BaseDto;
import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.builder.ResponseErrorBuilder;
import com.api.endereco.builder.ResponseSuccessBuilder;
import com.api.endereco.constants.MensagemSucessos;
import com.api.endereco.constants.MensagensErros;
import com.api.endereco.entity.dtos.EnderecoRequestDto;
import com.api.endereco.entity.dtos.EnderecoResponseDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.repositories.EnderecoRepository;
import com.api.endereco.transformer.EnderecoModelTransform;
import com.api.endereco.validates.CadastrarEnderecoValidate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CadastrarEnderecoService {

    private EnderecoRepository enderecoRepository;

    public CadastrarEnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public ResponseEntity cadastrarEndereco(EnderecoRequestDto enderecoRequestDto) {

        List<BaseErrorDto> erros = new CadastrarEnderecoValidate().validate(enderecoRequestDto);
        if (erros.size() > 0) {
            ResponseErrorBuilder resultado =  new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.getResultado();
        }

        if ((new EnderecoModelTransform().verificarErroCep(enderecoRequestDto) == false)) {
            erros.add(new BaseErrorDto("CEP", "CEP inválido."));
        }

        if (erros.size() > 0) {
            ResponseErrorBuilder resultado =  new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.getResultado();
        }

        EnderecoModel enderecoModel = new EnderecoModelTransform().transformarParaEnderecoModel(enderecoRequestDto);
        UUID cadastrarIdEndereco = enderecoRepository.save(enderecoModel).getId();

        ResponseSuccessBuilder cadastradoComSucesso = new ResponseSuccessBuilder<EnderecoResponseDto>(
                HttpStatus.CREATED,
                new EnderecoResponseDto(cadastrarIdEndereco.toString()),
                MensagemSucessos.CADASTRADO_COM_SUCESSO
        );
        return cadastradoComSucesso.getResultado();
    }
}
