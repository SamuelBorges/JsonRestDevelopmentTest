import java.util.ArrayList;
import java.util.List;

import br.com.frequenciaAluno.Model.AlunoPersistence;
import br.com.frequenciaAluno.Model.ResultadoAluno;
import br.com.mediafrequenciaAluno.Controller.RelatorioController;
import br.com.mediafrequenciaAluno.Controller.RestRequestController;

public class testeEdusoft {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			List<AlunoPersistence> informacaoAlunos = new ArrayList<>();
			List<ResultadoAluno> resultadosAlunos = new ArrayList<>();
			String resultado = "";

			// 1 - consumir web-service
			informacaoAlunos = RestRequestController.recuperarInformacaoAlunos();

			// 2 - Calcular resultado dos alunos
			resultadosAlunos = RestRequestController.calcularResultadoAlunos(informacaoAlunos);

			// 3 - Enviar resultado dos alunos p/ web service
			resultado = RestRequestController.enviarResultadoAluno(resultadosAlunos);
			
			//4 Gerar relatório
			RelatorioController.gerarRelatorio(resultadosAlunos);
	}

}
