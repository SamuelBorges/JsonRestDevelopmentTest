package br.com.frequenciaAluno.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootGravaResultado {

	@JsonProperty("resultadoAluno")
	public List<ResultadoAluno> resultados;

	public List<ResultadoAluno> getResultados() {
		return resultados;
	}

	public void setResultados(List<ResultadoAluno> resultados) {
		this.resultados = resultados;
	}

}
