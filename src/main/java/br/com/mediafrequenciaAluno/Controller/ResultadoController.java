package br.com.mediafrequenciaAluno.Controller;

import java.util.ArrayList;
import java.util.List;

import br.com.frequenciaAluno.Model.AlunoPersistence;
import br.com.frequenciaAluno.Model.NotaFaltasPersistence;
import br.com.frequenciaAluno.Model.ResultadoAluno;

public class ResultadoController {

	private static final int PORCENTAGEM_TOTAL = 100;
	private static final int MEDIA_MINIMA = 7;
	private static final int FREQUENCIA_MINIMA = 70;
	private static final String APROVADO = "AP";
	private static final String REPROVADO_MEDIA = "RM";
	private static final String REPROVADO_FREQUENCIA = "RF";
	
	
	public static double calcularMediaAritmeticaAluno(List<NotaFaltasPersistence> notas) {
		int qtdNotasCount = 0;
		double notasTotal = 0, media = 0;

		for (NotaFaltasPersistence notaFaltas : notas) {
			notasTotal += notaFaltas.getNota();
			qtdNotasCount++;
		}
		media = (notasTotal / qtdNotasCount);
		return media;
	}

	public static double calcularFrequenciaAluno(int totalAulas, int totalFaltas) {

		double frequencia = PORCENTAGEM_TOTAL - ((PORCENTAGEM_TOTAL * totalFaltas) / totalAulas);

		return frequencia;
	}

	public static String calcularResultado(double mediaAritmetica, double percentualFrequencia) {

		if (mediaAritmetica < MEDIA_MINIMA) {
			return REPROVADO_MEDIA;
		} else if (percentualFrequencia < FREQUENCIA_MINIMA) {
			return REPROVADO_FREQUENCIA;
		} 
		return APROVADO;
	}
}
