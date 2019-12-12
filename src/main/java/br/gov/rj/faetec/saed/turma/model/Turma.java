package br.gov.rj.faetec.saed.turma.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "idcurso")
	private Long idCurso;
	@Column(name = "idinstrutor" )
	private Long idInstrutor;
	@Column(name = "turno")
	private String turno;
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




	public Long getIdCurso() {
		return idCurso;
	}




	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}




	public Long getIdInstrutor() {
		return idInstrutor;
	}




	public void setIdInstrutor(Long idInstrutor) {
		this.idInstrutor = idInstrutor;
	}




	public String getTurno() {
		return turno;
	}




	public void setTurno(String turno) {
		this.turno = turno;
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
//`id` int(10) 
//`idcurso` int(10) 
//`idinstrutor` int(10) 
//`turno` varchar(45) 
//`cadastrado_por` int(10) 
//`data_cadastro` timestamp 
