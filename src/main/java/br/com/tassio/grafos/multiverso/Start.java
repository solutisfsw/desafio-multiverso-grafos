package br.com.tassio.grafos.multiverso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import br.com.tassio.grafos.multiverso.pojo.Caminho;
import br.com.tassio.grafos.multiverso.pojo.Deslocamento;
import br.com.tassio.grafos.multiverso.pojo.RequisicaoCaminho;
import br.com.tassio.grafos.multiverso.pojo.Solucao;
import br.com.tassio.grafos.multiverso.pojo.Vertice;

public class Start {

	// ****************************************************************
	// Declaração dos atributos estáticos
	// ****************************************************************

	// -> Atributo auxiliar para leitura e conversao de JSONs
	private static Gson gson = new Gson();

	// -> Definição do tipo contido no arquivo JSON para armazenamento do Grafo
	private static final Type TYPE_GRAFO = new TypeToken<HashMap<String, Vertice>>() {
	}.getType();

	// -> Definição do tipo contido no arquivo JSON para requisições de rotas
	private static final Type TYPE_REQUISICAO = new TypeToken<List<RequisicaoCaminho>>() {
	}.getType();

	// -> Atributo para guardar o grafo em memória carregado a partir do arquivo json
	public static HashMap<String, Vertice> grafo = new HashMap<String, Vertice>();

	// -> Atributo para guardar a lista de requisições carragadas a partir do arquivo json
	public static List<RequisicaoCaminho> listaRequisicao = new ArrayList<RequisicaoCaminho>();

	// ****************************************************************
	// Método de início
	// ****************************************************************
	public static void main(String[] args) throws FileNotFoundException {

		// ****************************************************************
		// Validar os parâmetros necessários para inicialização
		// ****************************************************************
		if (args.length != 3) {
			System.out.println("=================================================================\nExecucao incorreta!\n=================================================================\n\n");
			System.out.println("Para iniciar o sistema utilize os parametros abaixo.");
			System.out.println();
			System.out.println("Ex.: java -jar [nome_do_jar] [caminho_json_grafo] [caminho_json_requisicao] [caminho_pasta_solucao]");
			System.out.println();
			System.out.println("[nome_do_jar] : Nome do jar desta aplicacao.");
			System.out.println("[caminho_json_grafo] : Caminho completo do arquivo json que contem a definicao formal do grafo, incluindo o nome do arquivo.");
			System.out.println("[caminho_json_requisicao] : Caminho completo do arquivo json que contem as requisicoes das rotas desejadas, incluindo o nome do arquivo.");
			System.out.println("[caminho_pasta_solucao] : Caminho completo da pasta onde as solucoes das requisicoes devem ser salvas.");
			System.exit(1);
		}

		// ****************************************************************
		// Verfificar se os arquivos e pasta informados existem
		// ****************************************************************

		// -> Validar o arquivo json do grafo
		File arquivoJsonGrafo = new File(args[0]);
		if (!arquivoJsonGrafo.exists()) {
			System.out.println("=================================================================\nFalha na execucao!\n=================================================================\n\n");
			System.out.println("O arquivo referente ao grafo nao existe: " + args[0]);
			System.exit(2);
		}

		// -> Validar o arquivo json das requisicoes
		File arquivoJsonRequisicoes = new File(args[1]);
		if (!arquivoJsonRequisicoes.exists()) {
			System.out.println("=================================================================\nFalha na execucao!\n=================================================================\n\n");
			System.out.println("O arquivo referente as requisicoes nao existe: " + args[1]);
			System.exit(2);
		}

		// -> Validar se a pasta de solucoes existe
		File pastaDeSolucoes = new File(args[2]);
		if (!pastaDeSolucoes.exists()) {
			System.out.println("=================================================================\nFalha na execucao!\n=================================================================\n\n");
			System.out.println("A pasta para armazenamento das solucoes nao existe: " + args[2]);
			System.exit(2);
		} else if (!pastaDeSolucoes.isDirectory()) {
			System.out.println("=================================================================\nFalha na execucao!\n=================================================================\n\n");
			System.out.println("O caminho informado para a pasta de solucoes nao eh um diretorio: " + args[2]);
			System.exit(2);
		}

		// ****************************************************************
		// Carregamento das informações em memória
		// ****************************************************************

		// -> Carregamento e validação do Grafo
		JsonReader readerJsonGrafo = new JsonReader(new FileReader(arquivoJsonGrafo));
		try {
			grafo = gson.fromJson(readerJsonGrafo, TYPE_GRAFO);
		} catch (Exception e) {
			System.out.println("=================================================================\nFalha na execucao!\n=================================================================\n\n");
			System.out.println("Existe uma inconsistencia no arquivo de definicao do Grafo. Arquivo: " + arquivoJsonGrafo.getAbsolutePath());
			System.out.println();
			System.out.println();
			e.printStackTrace();
			System.exit(2);
		}

		// Carregamento e validação das requisições de rotas
		JsonReader readerJsonRequisicaoRota = new JsonReader(new FileReader(arquivoJsonRequisicoes));
		try {
			listaRequisicao = gson.fromJson(readerJsonRequisicaoRota, TYPE_REQUISICAO);
		} catch (Exception e) {
			System.out.println("=================================================================\nFalha na execucao!\n=================================================================\n\n");
			System.out.println("Existe uma inconsistencia no arquivo de definicao das requisicoes. Arquivo: " + arquivoJsonRequisicoes.getAbsolutePath());
			System.out.println();
			System.out.println();
			e.printStackTrace();
			System.exit(2);
		}

		// ****************************************************************
		// Processar requisições de caminhos
		// ****************************************************************
		ProcessadorRequisicao processador = new ProcessadorRequisicao();

		List<Solucao> listaSolucoes = new ArrayList<Solucao>();
		for (RequisicaoCaminho requisicao : listaRequisicao) {

			Solucao solucao = processador.processar(requisicao);

			// -> Ordenar os caminhos do menor para o maior
			Collections.sort(solucao.getListaCaminho());

			// -> Armazenar a solução com o conjunto de caminhos possíveis
			listaSolucoes.add(solucao);

			// debugSolucao(requisicao, solucao);
		}

		// -> Gerar o JSON com as soluções
		String jsonListaSolucao = gson.toJson(listaSolucoes);

		try {
			File arquivoSolucao = new File(pastaDeSolucoes.getAbsolutePath() + "/solucao-" + Calendar.getInstance().getTimeInMillis() + ".json");
			Writer writer = new FileWriter(arquivoSolucao);
			writer.write(jsonListaSolucao);
			writer.close();
			System.out.println("=================================================================\nExecucao concluida com SUCESSO!\n=================================================================\n");
			System.out.println();
			System.out.println("Solucao armazenada em: " + arquivoSolucao.getAbsolutePath());
		} catch (Exception e) {
			System.out.println("=================================================================\nFalha na execucao!\n=================================================================\n\n");
			System.out.println("Nao foi possivel criar um novo arquivo no diretorio " + pastaDeSolucoes.getAbsolutePath());
			System.out.println("o JSON contendo a solucao sera exibido abaixo: ");
			System.out.println(jsonListaSolucao);
			System.exit(2);
		}

	}

	private static void debugSolucao(RequisicaoCaminho requisicao, Solucao solucao) {

		System.out.println(requisicao);
		int idCaminho = 0;
		Collections.sort(solucao.getListaCaminho());

		for (Caminho caminho : solucao.getListaCaminho()) {

			System.out.println("\tCaminho " + ++idCaminho + ": distancia " + caminho.getDistanciaTotal());

			for (Deslocamento deslocamento : caminho.getListaDeslocamento()) {
				System.out.println("\t\t" + deslocamento);
			}

			if (solucao.getListaCaminho().size() > 1) {
				System.out.println("-----------------------------------------------");
				System.out.println();
			}

		}

		System.out.println();
		System.out.println("===============================================================================================");
		System.out.println();
		System.out.println();

	}

}
