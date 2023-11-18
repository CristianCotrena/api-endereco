package com.api.endereco.services.v1;

import com.api.endereco.base.dto.BaseErrorDto;
import com.api.endereco.builder.ResponseErrorBuilder;
import com.api.endereco.builder.ResponseSuccessBuilder;
import com.api.endereco.constants.MensagensErros;
import com.api.endereco.entity.dtos.ListarEnderecosRequestDto;
import com.api.endereco.entity.dtos.ListarEnderecosResponseDto;
import com.api.endereco.entity.dtos.PaginaListagemEnderecosResponseDto;
import com.api.endereco.entity.models.EnderecoModel;
import com.api.endereco.repositories.EnderecoRepository;
import com.api.endereco.repositories.specifications.endereco.EnderecoSpecifications;
import com.api.endereco.validates.ListarEnderecosValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarEnderecosService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public ListarEnderecosService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public ResponseEntity listarEnderecos(ListarEnderecosRequestDto listarEnderecosRequestDto, String pagina) {

        if (pagina == null || pagina.isEmpty()) {
            pagina = "0";
        }

        List<BaseErrorDto> erros = new ListarEnderecosValidate().validarParametros(listarEnderecosRequestDto, pagina);
        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.getResultado();
        }

        Page<EnderecoModel> paginaEnderecos;
        int numeroDaPagina = Integer.parseInt(pagina);
        Pageable pesquisaDeEnderecos = PageRequest.of(numeroDaPagina, 10, Sort.by(Sort.Order.asc("cep")));
        paginaEnderecos = enderecoRepository.findAll(buscarPorUmId(listarEnderecosRequestDto), pesquisaDeEnderecos);

        if (paginaEnderecos.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, MensagensErros.DADOS_NAO_ENCONTRADOS);
            return resultado.getResultado();
        }
        List<ListarEnderecosResponseDto> enderecosResponseDtoList = paginaEnderecos.getContent().stream()
                .map(endereco -> new ListarEnderecosResponseDto(
                        endereco.getId(),
                        endereco.getIdCliente(),
                        endereco.getIdFornecedor(),
                        endereco.getIdFuncionario(),
                        endereco.getRua(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getCep()
                )).collect(Collectors.toList());

        PaginaListagemEnderecosResponseDto listagemDeEnderecos = new PaginaListagemEnderecosResponseDto(
                enderecosResponseDtoList,
                (paginaEnderecos.getNumber() + 1),
                paginaEnderecos.getTotalPages());

        ResponseSuccessBuilder listagemComSucesso = new ResponseSuccessBuilder(
                HttpStatus.OK,
                listagemDeEnderecos,
                "Listagem concluída."
        );
        return listagemComSucesso.getResultado();
    }

    private Specification<EnderecoModel> buscarPorUmId (ListarEnderecosRequestDto listarEnderecosRequestDto) {

        Specification<EnderecoModel> specificationParaUmaId = EnderecoSpecifications.buscarPorUmId(
                listarEnderecosRequestDto.getIdCliente(),
                listarEnderecosRequestDto.getIdFornecedor(),
                listarEnderecosRequestDto.getIdFuncionario());
        Specification<EnderecoModel> buscar = Specification.where(specificationParaUmaId);
        return buscar;
    }
}
