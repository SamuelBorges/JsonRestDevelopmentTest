package br.com.mediafrequenciaAluno.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.frequenciaAluno.Model.AlunoPersistence;
import br.com.frequenciaAluno.Model.ResultadoAluno;
import br.com.frequenciaAluno.Model.RootGravaResultado;
import br.com.frequenciaAluno.Model.RootRecuperaAlunos;

@RestController
public class RestRequestController {

	// SERVICOS
	private static final String ENRERECO_REST = "http://desenvolvimento.edusoft.com.br/desenvolvimentoMentorWebG5/rest/servicoexterno/";
	private static final String RECUPERA_ALUNO = "execute/recuperaAlunos";
	private static final String GRAVA_RESULTADO_ALUNO = "execute/gravaResultado";
	private static final String RECUPERA_ALUNO_TOKEN = "token/recuperaAlunos";
	private static final String RESULTADO_ALUNO_TOKEN = "token/gravaResultado";

	// PARAMETROS
	private static final String TOKEN = "token";
	private static final String NAME_USUARIO_HEADER = "usuario";
	private static final String VALUE_USUARIO_HEADER = "mentor";
	private static final String NAME_SENHA_HEADER = "senha";
	private static final String VALUE_SENHA_HEADER = "123456";

	public static List<AlunoPersistence> recuperarInformacaoAlunos() {

		ObjectMapper mapper = new ObjectMapper();
		RootRecuperaAlunos[] rootAlunos = null;
		AlunoPersistence aluno = new AlunoPersistence();
		List<AlunoPersistence> alunos = new ArrayList<>();

		String token = recuperarTokenAcesso(ENRERECO_REST, RECUPERA_ALUNO_TOKEN);

		String jsonRetorno = recuperaObjetoJsonAutenticado(token, aluno, RECUPERA_ALUNO);

		try {

			rootAlunos = mapper.readValue(jsonRetorno, RootRecuperaAlunos[].class);

		} catch (JsonProcessingException e) {

			e.printStackTrace();

		}

		for (RootRecuperaAlunos root : rootAlunos) {
			root.getAlunos().forEach(alu -> alunos.add(alu));
		}

		return alunos;

	}

	private static String recuperarTokenAcesso(String endereco, String uri) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity;
		String token = "";
		String enderecoCompleto = endereco + uri;

		headers.set(NAME_USUARIO_HEADER, VALUE_USUARIO_HEADER);
		headers.set(NAME_SENHA_HEADER, VALUE_SENHA_HEADER);

		entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(enderecoCompleto, HttpMethod.GET, entity, String.class);

		token = response.getBody();
		return token;
	}

	private static String recuperaObjetoJsonAutenticado(String token, Object classe, String recuperaAluno) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity;
		ResponseEntity<String> response;
		String uri = "";
		String informacao = "";

		headers.set(TOKEN, token);
		headers.setContentType(MediaType.APPLICATION_JSON);

		uri = ENRERECO_REST + recuperaAluno;
		entity = new HttpEntity<String>(headers);

		response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		informacao = response.getBody();
		
		return informacao;
	}

	public static List<ResultadoAluno> calcularResultadoAlunos(List<AlunoPersistence> alunos) {

		List<ResultadoAluno> resultadosAlunos = new ArrayList<>();

		for (AlunoPersistence aluno : alunos) {

			ResultadoAluno resultadoAluno = new ResultadoAluno();
			
			resultadoAluno.setNotas(aluno.getNotas());
			resultadoAluno.setMedia(aluno.getMedia());
			resultadoAluno.setResultado(aluno.getResultado());
			resultadoAluno.setCodigo(aluno.getCodigo());
			resultadoAluno.setNome(aluno.getNome());
			resultadosAlunos.add(resultadoAluno);
		}

		return resultadosAlunos;
	}

	public static String enviarResultadoAluno(List<ResultadoAluno> resultadosAlunos) {

		HttpHeaders headers = new HttpHeaders();
		RootGravaResultado rootGravaResultado = new RootGravaResultado();

		String token = "";
		String jsonQuery = "";
		String resultadoJson = "";
		String enderecoCompleto = "";
		
		rootGravaResultado.setResultados(resultadosAlunos);
		enderecoCompleto = ENRERECO_REST + GRAVA_RESULTADO_ALUNO;
		
		token = recuperarTokenAcesso(ENRERECO_REST, RESULTADO_ALUNO_TOKEN);

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(TOKEN, token);

		jsonQuery = gerarJsonObject(rootGravaResultado);
		
		resultadoJson = enviarObjetoJsonAutenticado(jsonQuery, headers, enderecoCompleto);
		
		return resultadoJson;
	}

	private static String enviarObjetoJsonAutenticado(String jsonQuery, HttpHeaders headers, String enderecoCompleto) {
		HttpEntity<String> entity;
		RestTemplate restTemplate = new RestTemplate();
		String resultadoJson = "";
		
		entity = new HttpEntity<String>(jsonQuery, headers);

		ResponseEntity<String> jsonResult = restTemplate.postForEntity(enderecoCompleto, entity, String.class);
		resultadoJson = jsonResult.getBody();
		
		return resultadoJson;
	}

	private static String gerarJsonObject(Object object) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonQuery = "";
		
		try {
			
			jsonQuery = mapper.writeValueAsString(object);
			
		} catch (JsonProcessingException e1) {
			
			e1.printStackTrace();
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		return jsonQuery;
	}
}
