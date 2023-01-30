package org.generation.blogvagalume.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temasvagalume")
public class TemaVagalume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo Tipo Descrição é Obrigatório")
	private String tipodescricao;
	
	@NotNull(message = "O atributo Descrição é Obrigatório")
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("tema")
	private List<PostagemVagalume> postagem;
	


	public List<PostagemVagalume> getPostagem() {
		return this.postagem;
	}

	public void setPostagem(List<PostagemVagalume> postagem) {
		this.postagem = postagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipodescricao() {
		return tipodescricao;
	}

	public void setTipodescricao(String tipodescricao) {
		this.tipodescricao = tipodescricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
