package br.com.frequenciaAluno.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotaFaltasPersistence {
    @JsonProperty("NOTA") 
    private double nota;
    
    @JsonProperty("FALTAS") 
    private int faltas;

	public double getNota() {
		return nota;
	}

	public int getFaltas() {
		return faltas;
	}
    
    
}
