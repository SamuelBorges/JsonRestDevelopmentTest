package br.com.mediafrequenciaAluno.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;

import br.com.frequenciaAluno.Model.NotaFaltasPersistence;
import br.com.frequenciaAluno.Model.ResultadoAluno;

public class RelatorioController {
	
	public static void gerarRelatorio(List<ResultadoAluno> resultadosAlunos) {
		
		
		
		try {
			FileWriter fileCreator = new FileWriter(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\relatorio.txt" ,false);
			BufferedWriter buffer = new BufferedWriter(fileCreator);
			PrintWriter writter = new PrintWriter(buffer);
			
			writter.append(modeloRelatorioArquivoTxt(resultadosAlunos));
			writter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static StringBuilder modeloRelatorioArquivoTxt(List<ResultadoAluno> resultadosAlunos) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("			--------------------------------------------------								\n");
		builder.append("			|	Should call jasper reports, stopped developing bcause ireport is too old	\n");
		builder.append("			--------------------------------------------------								\n");
		builder.append("																							\n");
		builder.append(buildInformacoesAluno(builder, resultadosAlunos));

		return builder;
	}

	private static Object buildInformacoesAluno(StringBuilder builder, List<ResultadoAluno> resultadosAlunos) {

		for (ResultadoAluno resultadoAluno : resultadosAlunos) {
			
			builder.append("		  _________________________________________________________					\n");
			builder.append("				NOME DO ALUNO:	" + resultadoAluno.getNome() + "");
			builder.append("																							\n");
			
			buildNotasFaltas(resultadoAluno.getNotas(), builder);

			builder.append("																							\n");
			builder.append("				FALTAS:			" + resultadoAluno.getTotalFaltas() + "					\n");
			builder.append("																						\n");
			builder.append("				MÉDIA:			" + String.format("%.2f", resultadoAluno.getMedia()) + "		\n");
			builder.append("																							\n");
			builder.append("				RESULTADO:		" + resultadoAluno.getResultado() + "					\n");
			builder.append("		 _________________________________________________________					\n");
			builder.append("																							\n");
			builder.append("																							\n");
		}
		return null;
	}

	private static void buildNotasFaltas(List<NotaFaltasPersistence> notas, StringBuilder builder) {
		int contador = 0;
		
		for (NotaFaltasPersistence nota : notas) {
			if(contador != 0) {
				builder.append("			 	->	" + String.format("%.2f", nota.getNota()) + "										\n");
			}else {
				builder.append("				NOTAS:	" + String.format("%.2f", nota.getNota()) + "										\n");
			}
			contador++;
		}
	}
}
