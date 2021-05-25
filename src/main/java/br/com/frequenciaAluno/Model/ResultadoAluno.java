package br.com.frequenciaAluno.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoAluno {

	public List<NotaFaltasPersistence> notas;
	public int totalFaltas;
	
	@JsonProperty("MEDIA")
	public double media;

	@JsonProperty("RESULTADO")
	public String resultado;

	@JsonProperty("COD_ALUNO")
	public int codigo;

	@JsonProperty("SEU_NOME")
	public String nome;

	public double getMedia() {
		return media;
	}
	
	public void getNotasAluno(){
		
	}
	public void setMedia(double media) {
		this.media = media;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeAluno) {
		this.nome = nomeAluno;
	}

	public List<NotaFaltasPersistence> getNotas() {
		return notas;
	}

	public void setNotas(List<NotaFaltasPersistence> notas) {
		notas.forEach( n -> setTotalFaltas(n.getFaltas()));
		this.notas = notas;
	}

	public int getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(int totalFaltas) {
		this.totalFaltas += totalFaltas;
	}

}
