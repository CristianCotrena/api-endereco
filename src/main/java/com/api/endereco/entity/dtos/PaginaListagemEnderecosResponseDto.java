package com.api.endereco.entity.dtos;

import java.util.List;

public class PaginaListagemEnderecosResponseDto {

    private List<ListarEnderecosResponseDto> resultados;
    private int paginaAtual;
    private int totalPaginas;

    public PaginaListagemEnderecosResponseDto() {
    }

    public PaginaListagemEnderecosResponseDto(
            List<ListarEnderecosResponseDto> resultados,
            int paginaAtual,
            int totalPaginas) {
        this.resultados = resultados;
        this.paginaAtual = paginaAtual;
        this.totalPaginas = totalPaginas;
    }

    public List<ListarEnderecosResponseDto> getResultados() {
        return resultados;
    }

    public void setResultados(List<ListarEnderecosResponseDto> resultados) {
        this.resultados = resultados;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
}
