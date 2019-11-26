package br.gov.rj.faetec.saed.curso.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(name = "conteudo_prog")
	private String conteudoProg;
	@Column(name = "carga_horaria")
	private String cargaHoraria;
	@Column(name = "cadastrado_por")
	private Long cadastradoPor;
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getConteudoProg() {
		return conteudoProg;
	}
	public void setConteudoProg(String conteudoProg) {
		this.conteudoProg = conteudoProg;
	}
	public String getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Long getCadastradoPor() {
		return cadastradoPor;
	}
	public void setCadastradoPor(Long cadastradoPor) {
		this.cadastradoPor = cadastradoPor;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public boolean isNovo() {
		
		return id == null;
	}
}
