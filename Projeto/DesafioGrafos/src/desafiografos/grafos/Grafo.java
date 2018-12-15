/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.naming.InvalidNameException;

/**
 *
 * @author Henrique
 */
public class Grafo
{
    public enum OpcoesDeBusca
    {
        MenorDistancia,
        MaiorDistancia,
        TodasAsRotas
    };

    private final Vertices vertices = new Vertices();
    private final Arestas arestas = new Arestas();

    /**
     * @return Retorna o conjunto de vértices do grafo
     */
    public Vertices getVertices()
    {
        return vertices;
    }

    /**
     * @return Retorna o conjunto de arestas do grafo
     */
    public Arestas getArestas()
    {
        return arestas;
    }

    /**
     * Permite obter uma aresta a partir dos seus componentes. Caso a aresta ainda
     * não exista ela será criada.
     * @param vertice1 Vértice de origem da aresta
     * @param vertice2 Vértice de destino da aresta
     * @param distancia Distância entre os vértices da aresta
     * @return Retorna a aresta que foi encontrada ou criada
     * @throws InvalidNameException Ocorre se o nome fornecido para algum dos vértices for inválido
     */
    public Aresta criarOuObterAresta(String vertice1, String vertice2, int distancia) throws InvalidNameException
    {
        Vertice v1 = this.vertices.criarOuObter(vertice1);
        Vertice v2 = this.vertices.criarOuObter(vertice2);
        return this.arestas.criarOuObter(v1,v2, distancia);
    }

    /**
     * Permite obter um vértice a partir do seu nome. Caso o vértice não exista
     * ele será criado.
     * @param vertice Nome do vértice
     * @return Retorna o vértice que foi encontrado ou criado
     * @throws InvalidNameException Ocorre se o nome fornecido para o vértice for inválido
     */
    public Vertice criarOuObterVertice(String vertice) throws InvalidNameException
    {
        return this.vertices.criarOuObter(vertice);
    }

    /**
     * Obtém uma lista de caminhos que correspondam a um conjunto de critérios especificados
     * @param origem Nome do vértice de origem (obrigatório)
     * @param destino Nome do vértice de destino (obrigatório)
     * @param passandoPor Nome de um vértice intermediário entre a origem e o destino (Pode ser nulo ou vazio)
     * @param distancia Distânicia máxima entre a origem e o destino (Zero para distância ilimitada)
     * @param paradas Número máximo de paradas enyre a origem e o destino (Zero para quantidade ilimitada)
     * @param opcoes Tipo de busca que será execurada (Todas as rotas, Menor distância, Maior distância)
     * @return Retorna uma lista contendo os caminhos que correspondem aos critérios
     * @throws InvalidNameException Ocorre quando o nome fornecido para algum dos vértices é inválido ou quando algum dos vértices não pertence ao grafo
     * Os nomes dos vértices não podem ser strings vazias, não podem ter somente
     * espaços em branco e não podem conter o caractere nulo. Espaços em branco no
     * início ou no fim do nome serão removidos.
     */
    public List<Caminho> getCaminhos(String origem, String destino, String passandoPor, int distancia, int paradas, OpcoesDeBusca opcoes) throws InvalidNameException
    {
        Vertice vOrigem = new Vertice(origem);
        Vertice vDestino= new Vertice(destino);
        Vertice vPassandoPor = passandoPor == null || passandoPor.trim().equals("") ?
                                    null :
                                    new Vertice(passandoPor);
        
        return this.getCaminhos(vOrigem, vDestino, vPassandoPor,distancia,paradas, opcoes);
    }

    /**
     * Obtém uma lista de caminhos que correspondam a um conjunto de critérios especificados
     * @param origem Vértice de origem (obrigatório)
     * @param destino Vértice de destino (obrigatório)
     * @param passandoPor Vértice intermediário entre a origem e o destino (Pode ser nulo)
     * @param distancia Distânicia máxima entre a origem e o destino (Zero para distância ilimitada)
     * @param paradas Número máximo de paradas enyre a origem e o destino (Zero para quantidade ilimitada)
     * @param opcoes Tipo de busca que será execurada (Todas as rotas, Menor distância, Maior distância)
     * @return Retorna uma lista contendo os caminhos que correspondem aos critérios
     * @throws InvalidNameException Ocorre quando um dos vértices fornecidos não pertence ao grafo
     */
    public List<Caminho> getCaminhos(Vertice origem, Vertice destino, Vertice passandoPor, int distancia, int paradas, OpcoesDeBusca opcoes) throws InvalidNameException
    {
        if (!this.vertices.contem(origem))
        {
            throw new InvalidNameException("O vértice \"" + origem.getNome() + "\" não pertence ao grafo.");
        }
        else if (!this.vertices.contem(destino))
        {
            throw new InvalidNameException("O vértice \"" + destino.getNome() + "\" não pertence ao grafo.");
        }
        else if (passandoPor != null && !this.vertices.contem(passandoPor))
        {
            throw new InvalidNameException("O vértice \"" + passandoPor.getNome() + "\" não pertence ao grafo.");
        }

        Queue<Caminho> fila = new LinkedBlockingQueue<>();
        List<Caminho> result = new ArrayList<>();
        int auxiliar = opcoes == OpcoesDeBusca.MaiorDistancia ? 0 : Integer.MAX_VALUE;
        /**
         * Começaremos a busca criando um caminho inicial contendo apenas
         * os vértices de origem e destino.
         * 
         * O número de níveis de profundidade será igual ao número de vértices + 1.
         * Esse vértice extra é uma prevenção para o caso do vértice de origem
         * ser igual ao vértice de destino
         */
        Caminho caminho = new Caminho(origem, destino, this.vertices.contagem()+1);
        /**
         * Utilizaremos uma fila para manter controle do que ainda precisa ser feito.
         * Cada caminho que precisa ser analisado deve ser adicionado a essa fila
         * e o processamento acaba quando a fila estiver vazia.
         */
        fila.add(caminho);
        
        while (!fila.isEmpty())
        {
            /**
             * Cada um dos caminhos da fila deverá ser removido e analisado.
             * Caso o caminho tenha chegado ao limite sem encontrar o destino,
             * será descartado.
             * Caso o destino tenha sido encontrado, o caminho será adicionado
             * na lista de resposta e não voltará à fila.
             * Caso o caminho ainda precise de análise, continuará retornando
             * para a fila.
             */
            caminho = fila.remove();
            if (caminho.concluido())
            {
                /**
                 * Sabemos que o caminho chegou a um desfecho e não volratá mais
                 * para a fila. Agora vamos decidir se ele deve ou não ser incluído
                 * na lista de resposta.
                 */
                if (
                        caminho.getEncontrado() &&
                        (distancia == 0 || caminho.getDistancia() <= distancia) &&
                        (paradas == 0 || caminho.getParadas()<= paradas) &&
                        (passandoPor==null || caminho.contemVertice(passandoPor))
                   )
                {
                    switch (opcoes)
                    {
                        case MaiorDistancia:
                            if (caminho.getDistancia() > auxiliar)
                            {
                                auxiliar = caminho.getDistancia();
                                result.clear();
                                result.add(caminho);
                            }
                            else if (caminho.getDistancia() == auxiliar)
                            {
                                result.add(caminho);
                            }
                            break;
                        case MenorDistancia:
                            if (caminho.getDistancia() < auxiliar)
                            {
                                auxiliar = caminho.getDistancia();
                                result.clear();
                                result.add(caminho);
                            }
                            else if (caminho.getDistancia() == auxiliar)
                            {
                                result.add(caminho);
                            }
                            break;
                        default:
                            result.add(caminho);
                    }
                }
            }
            else
            {
                /**
                 * Neste ponto, sabemos que a busca ainda não terminou.
                 * Precisamos pegar todas as arestas que partem do último vértice
                 * onde o caminho parou.
                 */
                List<Aresta> arestasEncontradas = this.arestas.getPorOrigem(caminho.getUltimoVertice());
                /* Agora obteremos as ramificações resultantes destas arestas */
                List<Caminho> ramos =caminho.Ramificar(arestasEncontradas);
                /* Todas as ramificações devem ser adicionadas na fila para análise */
                fila.addAll(ramos);
            }
        }                
        return result;
    }
}
