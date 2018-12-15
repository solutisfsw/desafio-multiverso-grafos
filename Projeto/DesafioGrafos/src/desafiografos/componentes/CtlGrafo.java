/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.componentes;

import desafiografos.graficos.EstiloGrafico;
import desafiografos.graficos.Graficos;
import desafiografos.graficos.Ponto2D;
import desafiografos.grafos.Aresta;
import desafiografos.grafos.Caminho;
import desafiografos.grafos.Grafo;
import desafiografos.grafos.Vertice;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Iterator;
import javax.naming.InvalidNameException;
import javax.swing.JComponent;

/**
 * Componente responsável pela plotagem dos grafos na tela
 * @author Henrique
 */
public class CtlGrafo extends JComponent implements ComponentListener
{
    private final static Caminho CAMINHO_VAZIO = new Caminho();
    private Grafo grafo;
    private Caminho caminhoAtivo = CAMINHO_VAZIO;
    private final HashMap<Vertice,VerticeInfo> mapeamento = new HashMap<>();
    private Rectangle areaDoGrafo = new Rectangle();
    private boolean alterado = false;
    private boolean redimensionado = false;
    /* Informações de estilo gráfico */
    private static final EstiloGrafico ESTILO_NORMAL_PADRAO = new EstiloGrafico
        (
            new Color(238, 212, 69),
            new Color(199, 183, 199),
            new Color(104, 174, 186),
            new BasicStroke(1f,BasicStroke .CAP_ROUND,BasicStroke.JOIN_ROUND),
            new BasicStroke(1f,BasicStroke .CAP_ROUND,BasicStroke.JOIN_ROUND),
            new Font("Verdana",Font.BOLD, 14),
            new Font("Verdana",Font.BOLD, 16)
        );
    private static final EstiloGrafico ESTILO_ATIVO_PADRAO = new EstiloGrafico
        (
            new Color(255, 255, 255),
            new Color(127, 64, 127),
            new Color(191, 0, 127),
            new BasicStroke(2f,BasicStroke .CAP_ROUND,BasicStroke.JOIN_ROUND),
            new BasicStroke(2f,BasicStroke .CAP_ROUND,BasicStroke.JOIN_ROUND),
            new Font("Verdana",Font.BOLD, 14),
            new Font("Verdana",Font.BOLD, 16)
        );
    private static final int RAIO_DO_VERTICE = 20;
    private EstiloGrafico ESTILO_NORMAL = ESTILO_NORMAL_PADRAO;
    private EstiloGrafico ESTILO_ATIVO = ESTILO_ATIVO_PADRAO;
    
    private Color corDoFundo = Color.WHITE;
    private long quadro = 0;
    private Ponto2D margem;

    public CtlGrafo()
    {
        this.initComponents();
    }

    private void initComponents()
    {
        this.addComponentListener(this);
    }
    
    @Override
    public void componentResized(ComponentEvent e)
    {
        this.redimensionado = true;
    }
    
    @Override
    public void componentMoved(ComponentEvent e)
    {
        
    }

    @Override
    public void componentShown(ComponentEvent e)
    {
        
    }

    @Override
    public void componentHidden(ComponentEvent e)
    {
        
    }
    
    private class VerticeInfo extends Ponto2D
    {
        public Vertice vertice;

        public VerticeInfo(Vertice vertice,int x,int y)
        {
            super(x,y);
            this.vertice = vertice;
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        this.paint2d((Graphics2D )g);
    }
    
    private void paint2d(Graphics2D g)
    {
        EstiloGrafico estilo;
        this.quadro++;
        
        /**
         * Para desenhar o grafo precisamos primeiro determinar a área do
         * componente e depois posicionar o grafo no centro.
         */
        Dimension tamanho = this.getSize();
        /**
         * Se houve alteração nas dimensões do componente ou do grafo,
         * precisaremos recalcular
         */
        if (this.alterado) 
        {
            this.ajustarDimensoes();
        }
        else if (this.redimensionado)
        {
            this.reposicionarGrafo();
        }
        /* Começaremos o desenho limpando o fundo e fazendo alguns ajustes básicos */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.clearRect(0, 0, tamanho.width, tamanho.height);
        /* Ativa o antialiasing para suavização do serrilhado */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /* precisamos determinas se existe um grafo para ser desenhado */
        if (this.grafo!=null)
        {
            /* Primeiro desenharemos as arestas */
            for (Aresta a:this.grafo.getArestas())
            {
                /**
                 * Se o caminho ativo contém a aresta então ela precisa ser
                 * desenhada com uma cor de destaque
                 */
                if (this.caminhoAtivo.contemAresta(a))
                {
                    estilo = ESTILO_ATIVO;
                }
                else
                {
                    estilo = ESTILO_NORMAL;
                }
                /**
                 * Para desenhar as arestas, precisamos primeiro obter as
                 * posições dos seus vértices. Por questões de eficiência e
                 * estética, não desenharemos os vértices ainda, mas precisamos
                 * deles para montar o vetor.
                 */
                Ponto2D origem = this.getInfo(a.getOrigem()).getDeslocamento(this.margem.x, this.margem.y);
                Ponto2D destino = this.getInfo(a.getDestino()).getDeslocamento(this.margem.x, this.margem.y);
                Graficos.desenharArco(origem, destino, g, estilo, a.getDistancia()+"", RAIO_DO_VERTICE);
            }
            
            /**
             * Agora que jpa desenhamos as arestas, podemos desenhar os vértices.
             * Isso cobrirá qualquer falha eventual, deixando o desenho limpo
             */
            for (Vertice v:this.grafo.getVertices())
            {
                /* Assim como as arestas, os vértices selecionados devem ter destaque*/
                if (this.caminhoAtivo.contemVertice(v))
                {
                    estilo = ESTILO_ATIVO;
                }
                else
                {
                    estilo = ESTILO_NORMAL;
                }
                Ponto2D vertice = this.getInfo(v).getDeslocamento(this.margem.x, this.margem.y);
                Graficos.desenharVertice(vertice, g, estilo, RAIO_DO_VERTICE, v.getNome());
            }
        }
    }    
    
    /**
     * @return the grafo
     */
    public Grafo getGrafo()
    {
        return grafo;
    }

    /**
     * @param grafo the grafo to set
     */
    public void setGrafo(Grafo grafo)
    {
        this.grafo = grafo;
        this.repaint();
    }

    /**
     * @return the caminhoAtivo
     */
    public Caminho getCaminhoAtivo()
    {
        return this.caminhoAtivo == CAMINHO_VAZIO ? null : this.caminhoAtivo;
    }

    /**
     * @param caminhoAtivo the caminhoAtivo to set
     */
    public void setCaminhoAtivo(Caminho caminhoAtivo)
    {
        /* Precisamos garantir que o caminho ativo nunca seja nulo */
        if (caminhoAtivo == null)
        {
            this.caminhoAtivo = CAMINHO_VAZIO;
        }
        else
        {
            this.caminhoAtivo = caminhoAtivo;
        }
        this.repaint();
    }
    
    /**
     * Define a posição de cada vértice no grafo
     * @param nome Nome do vértice que deve ser posicionado
     * @param x Posição horizontal em pixels
     * @param y Posição vertical em pixels
     * @throws InvalidNameException 
     * Os nomes dos vértices não podem ser strings vazias, não podem ter somente
     * espaços em branco e não podem conter o caractere nulo. Espaços em branco no
     * início ou no fim do nome serão removidos.
     */
    public void posicionarVertice(String nome, int x, int y) throws InvalidNameException
    {
        Vertice vertice = new Vertice(nome);
        this.posicionarVertice(vertice, x, y);
    }
    
    /**
     * Define a posição de cada vértice no grafo
     * @param vertice Vértice que deve ser posicionado
     * @param x Posição horizontal em pixels
     * @param y Posição vertical em pixels
     */
    public void posicionarVertice(Vertice vertice, int x, int y)
    {
        VerticeInfo info = this.getInfo(vertice);
        info.x = x;
        info.y = y;
        this.alterado = true;
    }
    
    /**
     * Obtém as informações de posicionamento de cada vértice
     * @param vertice Vértice cuja informação deve ser obtida
     * @return 
     */
    private VerticeInfo getInfo(Vertice vertice)
    {
        if (this.mapeamento.containsKey(vertice))
        {
            return this.mapeamento.get(vertice);
        }
        else
        {
            VerticeInfo info = new VerticeInfo(vertice, 0, 0);
            this.mapeamento.put(vertice, info);
            return info;
        }
    }
    
    /**
     * Calcula as dimensões absolutas do grafo
     */
    private void ajustarDimensoes()
    {
        double minX, minY,maxX, maxY;
        /**
         * Se não existem vértices definidos, não tem o que calcular
         */
        if (this.mapeamento.isEmpty())
        {
            minX=0;
            minY=0;
            maxX=0;
            maxY=0;
        }
        else
        {
            /**
             * O cálculo deve determinar o tamanho do grafo com base em todos
             * os vértices mapeados. O ponto de partida será a posição do
             * primeiro vértice
             */
            Iterator<VerticeInfo> it = this.mapeamento.values().iterator();
            VerticeInfo item = it.next();

            minX=item.x;
            minY=item.y;
            maxX=item.x;
            maxY=item.y;
            
            while(it.hasNext())
            {
                item = it.next();
                if (item.x<minX) minX = item.x;
                if (item.y<minY) minY = item.y;
                if (item.x>maxX) maxX = item.x;
                if (item.y>maxY) maxY = item.y;
            }
        }
        this.areaDoGrafo = new Rectangle(Graficos.arredondar(minX), Graficos.arredondar(minY), Graficos.arredondar(maxX), Graficos.arredondar(maxY));
        this.alterado = false;
        this.reposicionarGrafo();
    }

    /**
     * Calcula a posição do grago em relação ao componente para mantê-lo centralizado.
     */
    private void reposicionarGrafo()
    {
        Dimension tamanho = this.getSize();
        int larg=(tamanho.width-this.areaDoGrafo.width)/2-this.areaDoGrafo.x;
        int alt=(tamanho.height-this.areaDoGrafo.height)/2-this.areaDoGrafo.y;
        this.margem = new Ponto2D(larg, alt);
        this.redimensionado=false;
    }

    /**
     * @return the areaDoGrafo
     */
    public Rectangle getAreaDoGrafo()
    {
        return areaDoGrafo;
    }

    /**
     * @param areaDoGrafo the areaDoGrafo to set
     */
    public void setAreaDoGrafo(Rectangle areaDoGrafo)
    {
        this.areaDoGrafo = areaDoGrafo;
    }

    /**
     * @return the corDoFundo
     */
    public Color getCorDoFundo()
    {
        return corDoFundo;
    }

    /**
     * @param novaCor the corDoFundo to set
     */
    public void setCorDoFundo(Color novaCor)
    {
        this.corDoFundo = novaCor;
    }

    /**
     * @return Retorna o estilo gráfico dos elementos que não estão ativos
     */
    public EstiloGrafico getEstiloNormal()
    {
        return this.ESTILO_NORMAL;
    }

    /**
     * @param novoEstilo novo estilo gráfico para os elementos que não estão ativos
     */
    public void setEstiloNormal(EstiloGrafico novoEstilo)
    {
        if (novoEstilo== null)
        {
            this.ESTILO_NORMAL = CtlGrafo.ESTILO_NORMAL_PADRAO;
        }
        else
        {
            this.ESTILO_NORMAL=novoEstilo;
        }
    }

    /**
     * @return Retorna o estilo gráfico dos elementos que estão ativos
     */
    public EstiloGrafico getEstiloAtivo()
    {
        return this.ESTILO_ATIVO;
    }

    /**
     * @param novoEstilo novo estilo gráfico para os elementos que estão ativos
     */
    public void setEstiloAtivo(EstiloGrafico novoEstilo)
    {
        if (novoEstilo== null)
        {
            this.ESTILO_ATIVO = CtlGrafo.ESTILO_ATIVO_PADRAO;
        }
        else
        {
            this.ESTILO_ATIVO=novoEstilo;
        }
    }
}
