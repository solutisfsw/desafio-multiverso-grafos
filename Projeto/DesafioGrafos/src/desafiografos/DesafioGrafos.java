/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos;

import desafiografos.grafos.Caminho;
import desafiografos.grafos.Grafo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;

/**
 *
 * @author Henrique
 */
public class DesafioGrafos {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo grafo = new Grafo();
        try
        {
            List<Caminho> caminhos;
            grafo.criarOuObterAresta("A", "B", 50);
            grafo.criarOuObterAresta("A", "D", 50);
            grafo.criarOuObterAresta("A", "E", 70);
            grafo.criarOuObterAresta("B", "C", 40);
            grafo.criarOuObterAresta("C", "D", 40);
            grafo.criarOuObterAresta("C", "E", 20);
            grafo.criarOuObterAresta("D", "C", 40);
            grafo.criarOuObterAresta("D", "E", 80);
            grafo.criarOuObterAresta("E", "B", 30);
            
            System.out.println("Distância de A a C passando por B");
            caminhos = grafo.getCaminhos("A", "C", "B",0,0,Grafo.OpcoesDeBusca.TodasAsRotas);
            if (caminhos.size()>0)
            {
                caminhos.sort(Caminho.COMPARADOR_DE_DISTANCIA);
                System.out.println(String.format("\tMenor distância: %d\r\n\tMaior distância: %d",caminhos.get(0).getDistancia(),caminhos.get(caminhos.size()-1).getDistancia()));
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem caminhos de A a C passando por B");
            }
            System.out.println("==================================================================");

            System.out.println("Distância de A a D");
            caminhos = grafo.getCaminhos("A", "D", null,0,0,Grafo.OpcoesDeBusca.TodasAsRotas);
            if (caminhos.size()>0)
            {
                caminhos.sort(Caminho.COMPARADOR_DE_DISTANCIA);
                System.out.println(String.format("\tMenor distância: %d\r\n\tMaior distância: %d",caminhos.get(0).getDistancia(),caminhos.get(caminhos.size()-1).getDistancia()));
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem caminhos de A a D");
            }
            System.out.println("==================================================================");
            
            System.out.println("Distância de A a C passando por D");
            caminhos = grafo.getCaminhos("A", "C", "D",0,0,Grafo.OpcoesDeBusca.TodasAsRotas);
            if (caminhos.size()>0)
            {
                caminhos.sort(Caminho.COMPARADOR_DE_DISTANCIA);
                System.out.println(String.format("\tMenor distância: %d\r\n\tMaior distância: %d",caminhos.get(0).getDistancia(),caminhos.get(caminhos.size()-1).getDistancia()));
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem rotas para de A a C passando por D");
            }
            System.out.println("==================================================================");
            
            System.out.println("Rotas de C a C com até 3 paradas");
            caminhos = grafo.getCaminhos("C", "C", null,0,3,Grafo.OpcoesDeBusca.TodasAsRotas);
            System.out.println(String.format("\tNúmero de rotas: %d",caminhos.size()));
            if (caminhos.size()>0)
            {
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem rotas para de C a C com 3 paradas ou menos");
            }
            System.out.println("==================================================================");
            
            System.out.println("Rotas de A a C com até 4 paradas");
            caminhos = grafo.getCaminhos("A", "C", null,0,4,Grafo.OpcoesDeBusca.TodasAsRotas);
            System.out.println(String.format("\tNúmero de rotas: %d",caminhos.size()));
            if (caminhos.size()>0)
            {
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem rotas para de A a C com 4 paradas ou menos");
            }
            System.out.println("==================================================================");
            
            System.out.println("Menor Rota de A a C");
            caminhos = grafo.getCaminhos("A", "C", null,0,0,Grafo.OpcoesDeBusca.MenorDistancia);
            if (caminhos.size()>0)
            {
                System.out.println(String.format("\tDistância da menor rota: %d",caminhos.get(0).getDistancia()));
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem rotas para de A a C");
            }
            System.out.println("==================================================================");
            
            System.out.println("Menor Rota de B a B");
            caminhos = grafo.getCaminhos("B", "B", null,0,0,Grafo.OpcoesDeBusca.MenorDistancia);
            if (caminhos.size()>0)
            {
                System.out.println(String.format("\tDistância da menor rota: %d",caminhos.get(0).getDistancia()));
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem rotas para de B a B");
            }
            System.out.println("==================================================================");
            
            System.out.println("Rotas de C a C com distância até 300");
            caminhos = grafo.getCaminhos("C", "C", null,300,0,Grafo.OpcoesDeBusca.TodasAsRotas);
            System.out.println(String.format("\tNúmero de rotas: %d",caminhos.size()));
            if (caminhos.size()>0)
            {
                mostrarRotas(caminhos);
            }
            else
            {
                System.out.println("\tNão existem rotas para de C a C com distância até 300");
            }
            System.out.println("==================================================================");

            System.out.println("Pronto.");
        }
        catch (InvalidNameException ex)
        {
            Logger.getLogger(DesafioGrafos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*ListaNula<Integer> lista = new ListaNula<>();
        for(int x:lista)
        {
            System.out.println(x);
        }
        lista.clear();
        */
    }

    private static void mostrarRotas(List<Caminho> caminhos)
    {
        System.out.println("Rotas: ");
        System.out.println("\t----------------------------------------------------------");
        System.out.println("\tDistância | Paradas | Trajeto");
        System.out.println("\t----------------------------------------------------------");
        for(Caminho c:caminhos)
        {           
            System.out.println(String.format("\t%9d | %7d | %s", c.getDistancia(),c.getParadas(),c.getTrajeto()));
        }
        System.out.println("\t----------------------------------------------------------");
    }
    
}
