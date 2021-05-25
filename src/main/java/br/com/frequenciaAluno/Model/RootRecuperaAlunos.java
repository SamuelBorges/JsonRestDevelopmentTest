package br.com.frequenciaAluno.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootRecuperaAlunos{
	 @JsonProperty("resultado") 
    public String resultado;
	 
	 @JsonProperty("alunos") 
    public List<AlunoPersistence> alunos;

	 
	 //getters	
	public List<AlunoPersistence> getAlunos() {
		return alunos;
	}
			
	//setters
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public void setAlunos(List<AlunoPersistence> alunos) {
		this.alunos = alunos;
	}
    
	
}
