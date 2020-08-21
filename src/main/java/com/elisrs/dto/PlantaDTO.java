package com.elisrs.dto;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class PlantaDTO {
    private String nomepopular;
    private String nomecientifico;
    private BigDecimal altura;
    private BigDecimal diametro;
    private List<Integer> categorias;
    private List<Integer> cordasfolhas;
    private List<Integer> cordasflores;
    private List<Integer> exposicoes;
    private List<Integer> texturasdotronco;
    private List<Integer> texturasdafolha;
    private List<Integer> climas;
    private Integer persistencia;
    private List<Integer> formas;

    public String getNomepopular() {
        return nomepopular;
    }

    public void setNomepopular(String nomepopular) {
        this.nomepopular = nomepopular;
    }

    public String getNomecientifico() {
        return nomecientifico;
    }

    public void setNomecientifico(String nomecientifico) {
        this.nomecientifico = nomecientifico;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public BigDecimal getDiametro() {
        return diametro;
    }

    public void setDiametro(BigDecimal diametro) {
        this.diametro = diametro;
    }

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

    public Integer getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(Integer persistencia) {
        this.persistencia = persistencia;
    }

    public List<Integer> getFormas() {
        return formas;
    }

    public void setFormas(List<Integer> formas) {
        this.formas = formas;
    }
}
