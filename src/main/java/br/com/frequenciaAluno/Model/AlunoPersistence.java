package br.com.frequenciaAluno.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.mediafrequenciaAluno.Controller.ResultadoController;

public class AlunoPersistence {

    @JsonProperty("COD") 
    public int codigo;
    
    @JsonProperty("NOME") 
    public String nome;
    
    @JsonProperty("TOAL_AULAS") 
    public int totalAulas;
    
    public List<NotaFaltasPersistence> nota;

    //getters
    public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public int getTotalAulas() {
		return totalAulas;
	}

	public List<NotaFaltasPersistence> getNotas() {
		return nota;
	}

	public String getResultado() {
		double mediaAritmetrica = this.getMedia();
		double percentualFrequencia = this.getFrequencia();
		String resultado = ResultadoController.calcularResultado(mediaAritmetrica, percentualFrequencia);
		
    	return resultado;
	}

	private double getFrequencia() {
		double frequencia = ResultadoController.calcularFrequenciaAluno(getTotalAulas(), getTotalFaltas());
		return frequencia;
	}
	
	private int getTotalFaltas() {
		int faltas = 0;
		
		for (NotaFaltasPersistence notaFaltas: nota) {
			faltas += notaFaltas.getFaltas();
		}
		
		return faltas;
	}

	public double getMedia() {

		double mediaAritmetica = ResultadoController.calcularMediaAritmeticaAluno(nota);
		
		return mediaAritmetica;
	}
}
