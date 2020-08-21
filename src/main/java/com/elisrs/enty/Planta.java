package com.elisrs.enty;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


/**
 * The persistent class for the planta database table.
 * 
 */
@Entity
@NamedQuery(name="Planta.findAll", query="SELECT p FROM Planta p")
public class Planta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;

	private BigDecimal altura;

	private BigDecimal diametro;

	private String nomecientifico;

	private String nomepopular;
	
	private boolean status;


	//bi-directional many-to-one association to Foto
	@ManyToOne
	@JoinColumn(name="idfoto")
	private Foto foto;

	//bi-directional many-to-one association to Persistencia
	@ManyToOne
	@JoinColumn(name="idpersistencia")
	private Persistencia persistencia;

	//bi-directional many-to-many association to Categoriavegetal
	@ManyToMany
	@JoinTable(
		name="plantacateogiria"
		, joinColumns={
			@JoinColumn(name="idplanta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcategoria")
			}
		)
	private List<Categoriavegetal> categoriavegetals;

	//bi-directional many-to-many association to Clima
	@ManyToMany
	@JoinTable(
		name="plantaclima"
		, joinColumns={
			@JoinColumn(name="idplanta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idclima")
			}
		)
	private List<Clima> climas;

	//bi-directional many-to-many association to Cordaflor
	@ManyToMany
	@JoinTable(
		name="plantacordaflor"
		, joinColumns={
			@JoinColumn(name="idplanta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcordaflor")
			}
		)
	private List<Cordaflor> cordaflors;

	//bi-directional many-to-many association to Cordafolha
	@ManyToMany
	@JoinTable(
		name="plantacordafolha"
		, joinColumns={
			@JoinColumn(name="idplanta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcordafolha")
			}
		)
	private List<Cordafolha> cordafolhas;

	//bi-directional many-to-many association to Exposicaosolar
	@ManyToMany
	@JoinTable(
		name="plantaexposicaosolar"
		, joinColumns={
			@JoinColumn(name="idplanta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idexposicaosolar")
			}
		)
	private List<Exposicaosolar> exposicaosolars;

	//bi-directional many-to-many association to Formadaplanta
	@ManyToMany
	@JoinTable(
		name="plantaforma"
		, joinColumns={
			@JoinColumn(name="idplanta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idforma")
			}
		)
	private List<Formadaplanta> formadaplantas;
	@ManyToMany
	@JoinTable(
			name="plantatexturafolha"
			, joinColumns={
			@JoinColumn(name="idplanta")
	}
			, inverseJoinColumns={
			@JoinColumn(name="idtexturafolha")
	}
	)
	private List<Texturafolha> texturafolhas;

	@ManyToMany
	@JoinTable(
			name="plantatexturatronco"
			, joinColumns={
			@JoinColumn(name="idplanta")
	}
			, inverseJoinColumns={
			@JoinColumn(name="idtexturatronco")
	}
	)
	private List<Texturatronco> texturatroncos;

	//bi-directional many-to-one association to Plantaprojeto
	@OneToMany(mappedBy="planta")
	private List<Plantaprojeto> plantaprojetos;

	public Planta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAltura() {
		return this.altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getDiametro() {
		return this.diametro;
	}

	public void setDiametro(BigDecimal diametro) {
		this.diametro = diametro;
	}

	public String getNomecientifico() {
		return this.nomecientifico;
	}

	public void setNomecientifico(String nomecientifico) {
		this.nomecientifico = nomecientifico;
	}

	public String getNomepopular() {
		return this.nomepopular;
	}

	public void setNomepopular(String nomepopular) {
		this.nomepopular = nomepopular;
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Foto getFoto() {
		return this.foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Persistencia getPersistencia() {
		return this.persistencia;
	}

	public void setPersistencia(Persistencia persistencia) {
		this.persistencia = persistencia;
	}

	public List<Categoriavegetal> getCategoriavegetals() {
		return this.categoriavegetals;
	}

	public void setCategoriavegetals(List<Categoriavegetal> categoriavegetals) {
		this.categoriavegetals = categoriavegetals;
	}

	public List<Clima> getClimas() {
		return this.climas;
	}

	public void setClimas(List<Clima> climas) {
		this.climas = climas;
	}

	public List<Cordaflor> getCordaflors() {
		return this.cordaflors;
	}

	public void setCordaflors(List<Cordaflor> cordaflors) {
		this.cordaflors = cordaflors;
	}

	public List<Cordafolha> getCordafolhas() {
		return this.cordafolhas;
	}

	public void setCordafolhas(List<Cordafolha> cordafolhas) {
		this.cordafolhas = cordafolhas;
	}
	public List<Exposicaosolar> getExposicaosolars() {
		return this.exposicaosolars;
	}

	public void setExposicaosolars(List<Exposicaosolar> exposicaosolars) {
		this.exposicaosolars = exposicaosolars;
	}

	public List<Formadaplanta> getFormadaplantas() {
		return this.formadaplantas;
	}

	public void setFormadaplantas(List<Formadaplanta> formadaplantas) {
		this.formadaplantas = formadaplantas;
	}

	public List<Plantaprojeto> getPlantaprojetos() {
		return this.plantaprojetos;
	}

	public void setPlantaprojetos(List<Plantaprojeto> plantaprojetos) {
		this.plantaprojetos = plantaprojetos;
	}

	public Plantaprojeto addPlantaprojeto(Plantaprojeto plantaprojeto) {
		getPlantaprojetos().add(plantaprojeto);
		plantaprojeto.setPlanta(this);

		return plantaprojeto;
	}

	public Plantaprojeto removePlantaprojeto(Plantaprojeto plantaprojeto) {
		getPlantaprojetos().remove(plantaprojeto);
		plantaprojeto.setPlanta(null);

		return plantaprojeto;
	}

	public List<Texturafolha> getTexturafolhas() {
		return this.texturafolhas;
	}

	public void setTexturafolhas(List<Texturafolha> texturafolhas) {
		this.texturafolhas = texturafolhas;
	}

	public List<Texturatronco> getTexturatroncos() {
		return this.texturatroncos;
	}

	public void setTexturatroncos(List<Texturatronco> texturatroncos) {
		this.texturatroncos = texturatroncos;
	}

	public String base64Encode() {
		System.out.println(Arrays.toString(Base64.encodeBase64(this.foto.getImagemplanta())));
		return Arrays.toString(Base64.encodeBase64(this.foto.getImagemplanta()));
	}
}