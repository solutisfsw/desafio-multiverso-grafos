/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.graficos;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.QuadCurve2D;

/**
 * Assistente de processamento gráfico 2D
 * Consiste em um conjunto de funções estáticas para manipulação de vetores e segmentos de reta
 * criados especificamente para a renderização do grafo.
 * @author Henrique
 */
public final class Graficos
{
    private static final int DISTANCIA_ENTRE_ARESTAS=30;
    private static final int ANGULO_DA_SETA=200;
    private static final int COMPRIMENTO_DA_SETA=20;
    private static final int MARGEM_DO_ROTULO=2;
    /**
     * Obtém o ponto médio entre dois pontos fornecidos
     * @param ponto1
     * @param ponto2
     * @return 
     */
    public static final Ponto2D getCentro(Ponto2D ponto1, Ponto2D ponto2) 
    {
        /* Centro do segmento */
        return new Ponto2D((ponto1.x + ponto2.x) / 2, (ponto1.y + ponto2.y) / 2);
    }

    /**
     * Obtém um segmento de reta tangente à reta que cruza os dois pontos fornecidos.
     * A tangente resultante será posicionada no ponto médio entre os dois pontos.
     * @param ponto1
     * @param ponto2
     * @param comprimento
     * @return 
     */
    public static final Ponto2D getTangente(Ponto2D ponto1, Ponto2D ponto2, int comprimento)
    {
        /* Centro do segmento */
        Ponto2D centro = getCentro(ponto1, ponto2);
        /* Vetor tangente ao segmento (centro na origem do sistema de coordenadas) */
        Ponto2D vetor = getVetorTangente(ponto1, ponto2, comprimento);
        /* Novo vetor perpendicular ao segmento e com origem no seu centro */
        Ponto2D result = new Ponto2D(centro.x + vetor.x, centro.y + vetor.y);
        return result;
    }

    /**
     * Obtém o vetor tangente do um segmento de reta formado pelos dois pontos fornecidos
     * @param ponto1 Ponto de inicial do segmento
     * @param ponto2 Ponto final do segmento
     * @param comprimento Comprimento do vetor tangente
     * @return 
     */
    public static final Ponto2D getVetorTangente(Ponto2D ponto1, Ponto2D ponto2, int comprimento)
    {
        /* Transforma o segmento em vetor */
        Ponto2D vetor = Graficos.getVetor(ponto1, ponto2, comprimento);
        /* Gira o vetor do segimento em 90 graus para obter o vetor tangente */
        return girarVetor(vetor, -90);
    }

    /**
     * Obtém um vetor correspondente ao segmento de reta formado por dois pontos
     * @param ponto1 Ponto de origem do vetor
     * @param ponto2 Ponto que indica o sentido do vetor
     * @return 
     */
    public static final Ponto2D getVetor(Ponto2D ponto1, Ponto2D ponto2)
    {
        /* Calcula os pontos do vetor */
        double x = (ponto2.x - ponto1.x);
        double y = (ponto2.y - ponto1.y);
        /* Cria o vetor e devolve */
        return new Ponto2D(x, y);
    }

    /**
     * Obtém um vetor corredpondente ao segmento de reta formado por dois pontos
     * @param ponto1 Ponto de origem do vetor
     * @param ponto2 Ponto que indica o sentido do vetor
     * @param comprimento Multiplicador de comprimento do vetor resultante
     * @return 
     */
    public static final Ponto2D getVetor(Ponto2D ponto1, Ponto2D ponto2, int comprimento)
    {
        /* Transforma o segmento em vetor */
        Ponto2D vetor  = getVetor(ponto1, ponto2);
        /* Obtém o módulo (comprimento) do vetor */
        double modulo = getModulo(vetor);
        /* Se o módulo do vetor for 0, então os pontos de origem e destino são iguais */
        if (modulo == 0)
        {
            return new Ponto2D(0, 0);
        }
        else
        {
            /**
             * Ao dividir cada componente do vetor pelo seu módulo, obtemos um
             * vetor unário (mesma direção e com o mesmo sentido com módulo 1).
             * Isso permite criar um novo vetor com o comprimento que foi especificado.
             */
            return new Ponto2D(vetor.x / modulo * comprimento, vetor.y / modulo * comprimento);
        }
    }

    /**
     * Obtém um vetor rotacionado a partir de um vetor de referência
     * @param vetor Vetor de referência
     * @param angulo Ângulo de rotação em graus
     * @return Retorna o vetor rotacionado
     */
    private static Ponto2D girarVetor(Ponto2D vetor, int angulo)
    {
        /* Obtém a matriz de rotação 2D para o ângulo especificado */
        double[][] matriz = getMatrizDeRotacao(angulo);
        /* Calcula, monta e devolve o vetor rotacionado */
        return new Ponto2D(vetor.x * matriz[0][0] + vetor.y * matriz[0][1], vetor.x * matriz[1][0] + vetor.y * matriz[1][1]);
    }

    /**
     * Calcula o módulo (comprimento) do vetor
     * @param vetor
     * @return Retorna o módulo do vetor
     */
    private static double getModulo(Ponto2D vetor)
    {
        /**
         * Basicamente o teorema de ptágoras:
         * 
         * O quadrado da hipotenusa é igual à soma dos quadrados dos catetos.
         * 
         * O módulo do vetor é justamente a hipotenusa
         */
        return Math.sqrt(vetor.x * vetor.x + vetor.y * vetor.y);
    }

    /**
     * Obtém a matriz derotação para o ângulo especificado
     * @param angulo Ângulo em graus
     * @return Retorna uma matriz de rotação 2D
     */
    private static double[][] getMatrizDeRotacao(int angulo)
    {
        /* Converte o ângulo para radianos */
        double anguloRad = angulo * Math.PI / 180;
        /* Obtém Seno e Cosseno do ângulo */
        double seno = Math.sin(anguloRad);
        double cosseno = Math.cos(anguloRad);
        /* Monta a matriz e devolve */
        double [][] matriz = {{cosseno, -seno}, {seno, cosseno}};
        return matriz;
    }

    /**
     * Desenha um arco em forma de seta partindo de P1 em direção a P2
     * @param p1 Ponto de origem do arco
     * @param p2 Ponto final do arco
     * @param g Superfície de desenho
     * @param estilo Conjunto de estilos que serão usados no desenho
     * @param rotulo Texto que será usado como rótulo do arco
     * @param raioDoVertice Distância que a ponta da seta deverá ter em relação ao centro do ponto final
     */
    public static final void desenharArco(Ponto2D p1, Ponto2D p2, Graphics2D g, EstiloGrafico estilo, String rotulo, int raioDoVertice)
    {
        /* Obtém um vetor a partir dos pontos fornecidos com comprimento igual ao raio do vértice */
        Ponto2D vetor = getVetor(p1, p2, raioDoVertice);
        /* Calcula novos pontos para o arco, afastando as extremidades dos centros dos vértices */
        Ponto2D  pa = new Ponto2D(p1.x + vetor.x, p1.y + vetor.y);
        Ponto2D pb = new Ponto2D (p2.x - vetor.x, p2.y - vetor.y);
        /**
         * Calcula um ponto tangente ao segmento PA/PB pelo seu centro.
         * Esse ponto será usado como referência para fazer a curva do arco.
         */
        Ponto2D tangente = Graficos.getTangente(pa, pb, DISTANCIA_ENTRE_ARESTAS);

        /* Desenha o arco */
        g.setColor(estilo.getBorda());
        g.setStroke(estilo.getEstiloDoTexto());
        QuadCurve2D arco = new QuadCurve2D.Float();
        arco.setCurve
            (
                pa.x  , pa.y,
                tangente.x, tangente.y,
                pb.x , pb.y
            );
        g.draw(arco);

        /* Pinta a seta na extremidade */
        g.fillPolygon(montarSeta(getVetor(tangente, pb, COMPRIMENTO_DA_SETA), pb));
        
        /* Calcula a posição do centro do rótulo no ponto médio entre a tangente e o centro do segmento */
        Ponto2D posicaoDoRotulo = getCentro(tangente, getCentro(p1, p2));
        /* Desenha a caixa do rótulo e o texto */
        desenharRotulos(g, rotulo, posicaoDoRotulo, estilo);
    }

    /**
     * Monta o polígono correspondente à seta que ficará na extremidade do arco
     * @param vetor Vetor de sentido oposto à ponta da seta
     * @param ponto Ponto correspondente à ponta da seta
     * @return 
     */
    private static Polygon montarSeta(Ponto2D vetor, Ponto2D ponto)
    {
        Polygon result = new Polygon();
        
        /* Ponto da primeira quina */
        Ponto2D p1 = girarVetor(vetor, -ANGULO_DA_SETA);
        /* Ponto da segunda quina */
        Ponto2D p2 = girarVetor(vetor, ANGULO_DA_SETA);
        /* Adiciona todos os pontos ao polígono e devolve */
        result.addPoint(arredondar(p1.x + ponto.x), arredondar(p1.y + ponto.y));
        result.addPoint(arredondar(ponto.x),  arredondar(ponto.y));
        result.addPoint(arredondar(p2.x + ponto.x), arredondar(p2.y + ponto.y));
        return result;
    }

    /**
     * Desenha os rótulos dos arcos na posição especificada
     * @param g
     * @param rotulo Texto do rótulo
     * @param posicao Posição onde a caixa do rótulo será centralizada
     * @param estilo  Conjunto de estilos de fontes e cores que serão usados
     */
    private static void desenharRotulos(Graphics2D g, String rotulo, Ponto2D posicao, EstiloGrafico estilo)
    {
        /* Obtém as dimensões do texto */
        Ponto2D tamanho = mensurarTexto(g, rotulo, estilo, estilo.getFonteDoRotulo());
        /* Cria um retângulo para o rótulo centralizado na posição fornecida */
        Rectangle area = new Rectangle
            (
                arredondar(posicao.x - tamanho.x/2 - MARGEM_DO_ROTULO),
                arredondar(posicao.y - tamanho.y/2 - MARGEM_DO_ROTULO),
                arredondar(tamanho.x + MARGEM_DO_ROTULO * 2),
                arredondar(tamanho.y + MARGEM_DO_ROTULO * 2)
            );
        /* Ajusta a cor do fundo e desenha o retângulo preenchido */
        g.setColor(estilo.getPreenchimento());
        g.fillRect(area.x, area.y, area.width, area.height);
        /* Ajusta a cor e o estilo de traço da borda e desenha o contorno do retângulo */
        g.setColor(estilo.getBorda());
        g.setStroke(estilo.getEstiloDaBorda());
        g.drawRect(area.x, area.y, area.width, area.height);
        //g.drawRect(area.x-1, area.y-1, area.width+2, area.height+2);
        /* Ajusta a cor e os parâmetros de traço do texto */
        g.setColor(estilo.getTexto());
        g.setFont(estilo.getFonteDoRotulo());
        /**
         * O java desenha o texto com base no canto inferior esquerdo da fonte,
         * porém todo o sistema de coordenadas parte do canto superior esquerdo.
         * Não encontrei uma forma de posicionar o texto com precisão usando os
         * recursos que o Java oferece, por isso apliquei uma solução temporária
         * que é adequada particularmente para o desafio dos grafos, mas ainda
         * não é uma solução universal.
         * Caso o tamamnho do grafo seja alterado esses parâmetros podem precisar
         * de revisão.
         */
        g.drawString(rotulo, arredondar(area.x + MARGEM_DO_ROTULO), arredondar(area.y + tamanho.y));
    }

    /**
     * Desenha um vértice centralizado na posição especificada
     * @param posicao Ponto central do vértice
     * @param g Susperfície de desenho
     * @param estilo Conjunto de estilos de texto, cores e bordas
     * @param raio Raio do vértice
     * @param rotulo Texto que será usado como rótulo para o vértice
     */
    public static final void desenharVertice(Ponto2D posicao, Graphics2D g, EstiloGrafico estilo, int raio, String rotulo)
    {
        /**
         * O java desenha formas ovais circunscritas em um retângulo.
         * Para um círculo, tanto a largura quanto a algura medirão 2
         * vezes o raio.
         */
        Rectangle area = new Rectangle(arredondar(posicao.x - raio), arredondar(posicao.y - raio), arredondar(raio * 2), arredondar(raio * 2));
        /* obtém as dimensões do texto */
        Ponto2D tamanho = mensurarTexto(g,rotulo,estilo,estilo.getFonteDoVertice());
        /* aplica a cor de preenchimento e desenha o fundo */
        g.setColor(estilo.getPreenchimento());
        g.fillOval(area.x, area.y, area.width, area.height);
        /* aplica a cor e o estilo da borda e depois desenha o contorno */
        g.setColor(estilo.getBorda());
        g.setStroke(estilo.getEstiloDaBorda());
        g.drawOval(area.x, area.y, area.width, area.height);
        /* aplica a cor e o estilo do texto e depois escreve no centro do círculo*/
        g.setStroke(estilo.getEstiloDoTexto());
        g.setColor(estilo.getTexto());
        g.setFont(estilo.getFonteDoVertice());
        /**
         * O java desenha o texto com base no canto inferior esquerdo da fonte,
         * porém todo o sistema de coordenadas parte do canto superior esquerdo.
         * Não encontrei uma forma de posicionar o texto com precisão usando os
         * recursos que o Java oferece, por isso apliquei uma solução temporária
         * que é adequada particularmente para o desafio dos grafos, mas ainda
         * não é uma solução universal.
         * Caso o tamamnho do grafo seja alterado esses parâmetros podem precisar
         * de revisão.
         */
        g.drawString(rotulo,arredondar(area.x + raio - tamanho.x/2), arredondar(posicao.y + tamanho.y/3));
    }

    /**
     * Obtém as dimensões do texto na superfície de desenho especificada, conforme
     * as configurações de texto e estilo fornecidas
     * @param g Superfície de desenho onde o texto deverá ser plotado
     * @param rotulo texto que será mensurado
     * @param estilo conjunto de estilos que serão utilizados na plotagem do texto
     * @param fonte fonte que será usada para a plotagem
     * @return 
     */
    private static Ponto2D mensurarTexto(Graphics2D g, String rotulo,EstiloGrafico estilo, Font fonte)
    {
        /* aplica o estilo antes de obter as dimensões, mesmo que ele nao vá ser usado agora */
        g.setFont(fonte);
        g.setStroke(estilo.getEstiloDoTexto());
        /**
         * O java não mensura o texto por completo. Ele calcula apenas a largura
         * do texto. A altura é retornada em função da fonte e não do texto.
         * Isso é um problema sério, pois dificulta a centralização vertical,
         * especialmente pelo fato do sistema de coordenadas para plotagem de
         * textos ser invertido verticalmente.
         */
        return new Ponto2D
            (
                g.getFontMetrics().stringWidth(rotulo),
                g.getFontMetrics().getHeight()
            );
    }

    /**
     * Arredonda números e devolve um inteiro (int)
     * @param valor Número que será arredondado
     * @return 
     */
    public static final int arredondar(double valor)
    {
        return (int)Math.round(valor);
    }

    /**
     * Arredonda o número preservando duas casas decimais
     * @param valor Número que será arredondado
     * @return 
     */
    public static float arredondar2(double valor)
    {
        return ((float)Math.round(valor *100) / 100);
    }
}
