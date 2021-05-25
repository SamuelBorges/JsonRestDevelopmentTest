package br.com.frequenciaAluno.mediaFrequanciaAlunos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.mediafrequenciaAluno.Controller.RestRequestController;

@SpringBootTest
class MediaFrequanciaAlunosApplicationTests {

	@Test
	void contextLoads() {
		RestRequestController controller = new RestRequestController();
		//controller.executar(); 
	}

}
