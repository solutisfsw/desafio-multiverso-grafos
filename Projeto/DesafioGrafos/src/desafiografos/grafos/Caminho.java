/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import desafiografos.grafos.Comparadores.ComparadorDeDistancia;
import desafiografos.grafos.Comparadores.ComparadorDeParadas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class Caminho
{
    private ArrayList<Vertice> vertices;
    private final Vertice destino;
    private NoInterno ultimoNo;
    private int niveis;
    private int nivel;
    private boolean encontrado;
    private String trajetoCompleto="";
    private String trajetoSimplificado="";
    private final ArrayList<Aresta> rota = new ArrayList<>();
    private int custo=0;
    private int paradas=0;
    private boolean calculado = false;
    public static final ComparadorDeDistancia COMPARADOR_DE_DISTANCIA = new ComparadorDeDistancia();
    public static final ComparadorDeParadas COMPARADOR_DE_PARADAS = new ComparadorDeParadas();
    
    /**
     * Cria uma lista a partir de um conjunto de vértices
     * @param itens Vértices que deverão ser adicionados à lista
     * @return Retorna uma nova lista de vértices já populada com os elementos fornecidos
     */
    private static ArrayList<Vertice> criarLista(Vertice ...itens)
    {
        ArrayList<Vertice> result = new ArrayList<>();
        result.addAll(Arrays.asList(itens));
        return result;
    }

    public Caminho()
    {
        this.calculado=true;
        this.ultimoNo=null;
        this.vertices = new ArrayList<>();
        this.nivel=0;
        this.niveis=0;
        this.encontrado = false;
        this.destino = null;
    }

    /**
     * Inclui um vértice e sua respectiva aresta na pilha interna do caminho,
     * atualizando a contagem e verificando se o fim do caminho foi alcançado
     * @param verticeAtual
     * @param arestaAtual 
     */
    private void incluirVertice(Vertice verticeAtual, Aresta arestaAtual)
    {
        /**
         * O fornecimento da aresta seria suficiente para a execução deste método
         * já que o vértice é um dos seus membros, porém é mais eficiente passar
         * os dois como argumentos em vez de fazer uma nova chamada de método cada
         * vez que esse vértice for necessário.
         * 
         * Apesar de parecer pouco, em um processamento iterativo cada ponto de
         * economia faz diferença na redução do custo da operação.
         */
        this.calculado=false;
        this.ultimoNo=new NoInterno(this.ultimoNo, verticeAtual, arestaAtual);
        if (!this.vertices.contains(verticeAtual))
        {
            this.vertices.add(verticeAtual);
        }
        this.nivel++;
        this.encontrado = this.destino.equals(verticeAtual);
        if (this.encontrado)
        {
            this.calcular();
        }
    }
    
    public Vertice getUltimoVertice()
    {
        return this.ultimoNo == null ? null : this.ultimoNo.vertice;
    }    
    
    public boolean concluido()
    {
        return this.encontrado || this.nivel >= this.niveis;
    }
    
    public boolean getEncontrado()
    {
        return this.encontrado;
    }
    
    private class NoInterno
    {
        private NoInterno anterior;
        private Vertice vertice;
        private Aresta aresta;

        private NoInterno(NoInterno anterior, Vertice ultimoVertice)
        {
            this.anterior=anterior;
            this.vertice=ultimoVertice;
            this.aresta=null;
        }

        private NoInterno(NoInterno anterior, Vertice vertice, Aresta aresta)
        {
            this.anterior=anterior;
            this.vertice=vertice;
            this.aresta=aresta;
        }

        private NoInterno(Vertice ultimoVertice)
        {
            this(null,ultimoVertice);
        }
    }
    
    /**
     * Cria um novo caminho definindo todos os parâmetros internos
     * @param vertices Lista de vértices para controle dos nós que ja foram percorridos
     * @param destino Vértice destino para onde o caminho deve seguir
     * @param ultimoNo Último nó da pilha interna que armazena os vértices por onde o caminho passa
     * @param niveis Número máximo de passos que o caminho óde dar até o destino
     * @param nivel Passo atual desde a origem até o destino
     * @param encontrado Flag que identifica se o vértice destino já foi alcançado
     */
    private Caminho(ArrayList<Vertice> vertices, Vertice destino, NoInterno ultimoNo, int niveis, int nivel, boolean encontrado)
    {
        this.vertices = vertices;
        this.destino = destino;
        this.ultimoNo=ultimoNo;
        this.niveis=niveis;
        this.nivel=nivel;
        this.encontrado = encontrado;
    }

    /**
     * Cria um novo caminho para um destino específico
     * @param origem Vértice de origem do caminho
     * @param destino Vértice de destino para onde o caminho deve seguir
     * @param niveis Número máximo de passos que o caminho pode dar até o destino, deve ser igual ao número de vértices do grafo + 1
     */
    public Caminho(Vertice origem, Vertice destino, int niveis)
    {
        this.vertices = criarLista(origem);
        this.destino = destino;
        this.ultimoNo=new NoInterno(origem);
        this.niveis=niveis;
        this.nivel=0;
        this.encontrado = false;
    }
    
    /**
     * Clona um caminho existente, adicionando um novo nó ao final
     * @param caminho Caminho de referência para a clonagem
     * @param proximoVertice Vértice que será adicionado ao final do caminho
     */
    private Caminho(Caminho caminho,Vertice proximoVertice, Aresta proximaAresta)
    {
        this
        (
                new ArrayList<>(caminho.vertices),
                caminho.destino,
                caminho.ultimoNo,
                caminho.niveis,
                caminho.nivel,
                caminho.encontrado
        );
        this.incluirVertice(proximoVertice,proximaAresta);
    }
    
    /**
     * Permite que a busca possa ser ramificada para caminhos distintos, a partir
     * do mesmo ponto de origem, sem perder o histórico e sem esforço extra para
     * tratar asquilo que já foi tratado por outro caminho da mesma árvore.
     * 
     * O caminho a partir de onde a ramificação foi criada será sempre o últmo
     * item da lista resultante.
     * 
     * @param arestas Lista de arestas que serão utilizadas para ramificar o caminho
     * @return Retorna uma lista de caminhos contendo pelo menos um item, conforme a análise dos vértices
     */
    public List<Caminho> Ramificar(List<Aresta> arestas)
    {
        ArrayList<Caminho> result = new ArrayList<>();
        /**
         * Antes de criar as ramificações, precisamos limpar a lista de arestas, pois:
         * 
         * 1: Se o vértice final de uma aresta já foi encontrado antes, significa
         * que retornamos a um ponto do grafo que já foi analisado. Isso indica
         * que fechamos um ciclho e existe motivo para prosseguir, pois estaríamos
         * repetindo os mesmos passos indefinidamente.
         * 
         * 2: Mesmo esse vértice ainda não tenha sido encontrado, temos um número
         * finito de nós para percorrer. Se o número de nós analisados chegou a
         * esse limite então não existe mais nada a ser analisado, pois já
         * analisamos todos os nós existentes e mais um nó extra, por segurança.
         * 
         * verificamos primeiro se a busca já terminou.
         */
        if (this.nivel < this.niveis && !this.encontrado)
        {
            int pos=0; /* Posição dentro da lista */
            int dif; /* Diferença entre a posição que estamos e o tamanho da ista */
            /**
             * Agora temos que eliminar os nós que já foram alcançados, pois
             * são irrelevantes, exceto quando esse nó em particular é o nó de
             * destino (ele pode coincidir com o nó de origem).
             * 
             * Nesse ponto faremos apenas uma limpeza prévia, mas ainda não
             * criaremos as ramificações.
             * 
             * Isso pode parecer um passo desnecessário, já que vai gastar um
             * pouco de processamento iterativo que já poderia ser usado para
             * processar as ramificações do caminho.
             * 
             * Essa impressão inicial é enganosa, pois é mais custoso criar um
             * clone do caminho do que fazer uma iteração prévia para eliminar
             * os caminhos inválidos.
             * 
             * Isso vai permitir que o caminho a partir de onde o método foi
             * chamado seja reutilizado na última iteração.
             */
            while(pos<arestas.size())
            {
                Aresta arestaAtual = arestas.get(pos);
                Vertice verticeAtual = arestaAtual.getDestino();
                /**
                 * precisamos verificar se o nó que encontramos é o nó de destino.
                 * 
                 * É importante fazer esse teste primeiro, pois o nó de destino
                 * pode ser o mesmo nó de origem. Isso significa que ele pode
                 * estar na lista e poderia ser descartado erroneamente.
                 * 
                 * Vale observar que o fato de ter encontrado um caminho não
                 * significa que o algoritmo acaba, pois não estamos buscando um
                 * caminhnho qualquer e sim todos os caminhos.
                 * 
                 * Cabe a quem solicitou a pesquisa decidir o que fazer com o
                 * resultado.
                 */
                if (verticeAtual.equals(this.destino))
                {
                    /**
                     * Nesse momento não faremos nada, mas o teste é necessário
                     * para que o item não seja removido erroneamente
                     */
                    pos++;
                }
                /**
                 * Agora sabemos que esse nó não é o de destino, portanto podemos
                 * descartá-lo caso já tenha sido visitato. Esse passo é
                 * importante, pois evita que a busca entre em loop, o que
                 * causaria desperdício de processamento e memória.
                 */
                else if (this.vertices.contains(verticeAtual))
                {
                    arestas.remove(pos);
                }
                /**
                 * Se o nó não está repetido e não é o nó de destino então
                 * devemos mantê-lo
                 */
                else
                {
                    pos++;
                }
            }

            /**
             * Agora a lista está limpa. Para cada vértice restante criaremos
             * uma duplicata do caminho, exceto para o último item que usará
             * o próprio caminho a partir de onde a ramificação foi criada.
             * 
             * Nessa nova iteração não precisamos mais testar quem entra e quem
             * sai, pois todos entram. No entanto, precisamos controlar se é o
             * último item para reaproveitar o caminho.
             */
            pos=0;
            while((dif = (arestas.size() - pos)) > 0)
            {
                Aresta arestaAtual = arestas.get(pos);
                Vertice verticeAtual = arestaAtual.getDestino();
                /**
                 * Se estamos no último item da lista, podemos usar
                 * o caminho corrente em vez de criar um clone.
                 */
                if (dif==1)
                {
                    this.incluirVertice(verticeAtual,arestaAtual);
                    result.add(this);
                }
                /* Ainda existem mais itens na lista, precisamos de um clone */
                else
                {
                    result.add(new Caminho(this,verticeAtual,arestaAtual));
                }
                pos++;
            }
        }
        /* Se chegamos aqui, significa que o número máximo de passos foi atingido */
        else
        {
            result.add(this);
        }
        return result;
    }

    @Override
    public String toString()
    {
        if (!this.calculado)
        {
            this.calcular();
        }
        return this.trajetoCompleto;
    }
    
    
    /**
     * Recalcula as informações do caminho
     * Só deve ser chamado quando a busca de rotas estiver pronta e só precisa
     * ser chamado uma vez para cada instância.
     */
    private void calcular()
    {
        this.rota.clear();
        int custoAcumulado =0;
        this.paradas = 0;
        StringBuilder sbCompleto = new StringBuilder();
        StringBuilder sbSimplificado = new StringBuilder();
        String separador = "";
        NoInterno no = this.ultimoNo;
        while (no!=null)
        {
            if (no.aresta==null)
            {
                sbSimplificado.insert(0, no.vertice.toString());
            }
            else
            {
                this.paradas++;
                this.rota.add(0, no.aresta);
                sbSimplificado.insert(0, String.format("↦[%d]→%s", no.aresta.getDistancia(), no.vertice.getNome()));
                custoAcumulado+=no.aresta.getDistancia();
            }
            no=no.anterior;
        }
        sbCompleto.append("Caminho: ");
        sbCompleto.append(sbSimplificado.toString());
        sbCompleto.append(" | Distância total = ").append(custoAcumulado);
        sbCompleto.append(" | Paradas = ").append(this.paradas);
        sbCompleto.append(" | Concluido = ").append(this.concluido());
        sbCompleto.append(" | Encontrado = ").append(this.encontrado);
        this.trajetoCompleto = sbCompleto.toString();
        this.trajetoSimplificado = sbSimplificado.toString();
        this.custo = custoAcumulado;
    }
    
    public List<Aresta> getRota()
    {
        if (!this.calculado)
        {
            this.calcular();
        }
        return this.rota;
    }
    
    public int getDistancia()
    {
        if (!this.calculado)
        {
            this.calcular();
        }
        return this.custo;
    }
    
    public int getParadas()
    {
        if (!this.calculado)
        {
            this.calcular();
        }
        return this.paradas;
    }
    
    public boolean contemAresta(Aresta aresta)
    {
        if (!this.calculado)
        {
            this.calcular();
        }
        return this.rota.contains(aresta);
    }
    
    public boolean contemVertice(Vertice vertice)
    {
        if (!this.calculado)
        {
            this.calcular();
        }
        return this.vertices.contains(vertice);
    }

    public String getTrajeto()
    {
        if (!this.calculado)
        {
            this.calcular();
        }
        return this.trajetoSimplificado;
    }
}
