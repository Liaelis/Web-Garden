package com.elisrs.dto;

import java.util.List;

public class AtributoPlantaDTO {
    private List<Integer> categorias;
    private List<Integer> cordasfolhas;
    private List<Integer> cordasflores;
    private List<Integer> exposicoes;
    private List<Integer> texturasdotronco;
    private List<Integer> texturasdafolha;
    private List<Integer> climas;
    private List<Integer> persistencia;
    private List<Integer> formas;

    public List<Integer> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Integer> categorias) {
        this.categorias = categorias;
    }

    public List<Integer> getCordasfolhas() {
        return cordasfolhas;
    }

    public void setCordasfolhas(List<Integer> cordasfolhas) {
        this.cordasfolhas = cordasfolhas;
    }

    public List<Integer> getCordasflores() {
        return cordasflores;
    }

    public void setCordasflores(List<Integer> cordasflores) {
        this.cordasflores = cordasflores;
    }

    public List<Integer> getExposicoes() {
        return exposicoes;
    }

    public void setExposicoes(List<Integer> exposicoes) {
        this.exposicoes = exposicoes;
    }

    public List<Integer> getTexturasdotronco() {
        return texturasdotronco;
    }

    public void setTexturasdotronco(List<Integer> texturasdotronco) {
        this.texturasdotronco = texturasdotronco;
    }

    public List<Integer> getTexturasdafolha() {
        return texturasdafolha;
    }

    public void setTexturasdafolha(List<Integer> texturasdafolha) {
        this.texturasdafolha = texturasdafolha;
    }

    public List<Integer> getClimas() {
        return climas;
    }

    public void setClimas(List<Integer> climas) {
        this.climas = climas;
    }

    public List<Integer> getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(List<Integer> persistencia) {
        this.persistencia = persistencia;
    }

    public List<Integer> getFormas() {
        return formas;
    }

    public void setFormas(List<Integer> formas) {
        this.formas = formas;
    }
}
