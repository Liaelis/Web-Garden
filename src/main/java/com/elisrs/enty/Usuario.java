package com.elisrs.enty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;

	private String email;

	private String nome;

	private String senha;

	private String telefone;

	//bi-directional many-to-one association to Projeto
	@OneToMany(mappedBy="usuario")
	private List<Projeto> projetos;

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumn(name="idpermissao")
	private Permissao permissao;

	@OneToMany(mappedBy="usuario")
	private List<RecuperacaoSenha> recuperacaoSenhas;

	public Usuario() {
	}
	public List<RecuperacaoSenha> getRecuperacaoSenhas() {
		return recuperacaoSenhas;
	}

	public void setRecuperacaoSenhas(List<RecuperacaoSenha> recuperacaoSenhas) {
		this.recuperacaoSenhas = recuperacaoSenhas;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Projeto> getProjetos() {
		return this.projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Projeto addProjeto(Projeto projeto) {
		getProjetos().add(projeto);
		projeto.setUsuario(this);

		return projeto;
	}

	public Projeto removeProjeto(Projeto projeto) {
		getProjetos().remove(projeto);
		projeto.setUsuario(null);

		return projeto;
	}

	public Permissao getPermissao() {
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}