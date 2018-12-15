/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.componentes;

import desafiografos.grafos.Caminho;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Henrique
 */
public class TabelaDeRotas extends AbstractTableModel
{
    private List<Caminho> dados;
    private static final String[] NOMES_DAS_COLUNAS = {"Rota","Distância","Paradas","Trajeto"};
    
    public TabelaDeRotas(List<Caminho> dados)
    {
        if (dados==null)
        {
            this.dados =new ArrayList<>();
        }
        else
        {
            this.dados=dados;
        }
    }

    @Override
    public int getRowCount()
    {
        return this.dados.size();
    }

    @Override
    public int getColumnCount()
    {
        return getColunas().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Caminho caminho = this.dados.get(rowIndex);
        //"Rota","Distância","Paradas","Trajeto"
        switch (columnIndex)
        {
            case 0:
                return "Rota " + (rowIndex +1);
            case 1:
                return caminho.getDistancia();
            case 2:
                return caminho.getParadas();
            case 3:
                return caminho.getTrajeto();
            default:
                return null;
        }
    }
    
    /**
     * @return the dados
     */
    public List<Caminho> getDados()
    {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(List<Caminho> dados)
    {
        this.dados = dados;
    }

    /**
     * @return the NOMES_DAS_COLUNAS
     */
    public static String[] getColunas()
    {
        return NOMES_DAS_COLUNAS;
    }

    @Override
    public String getColumnName(int column)
    {
        return NOMES_DAS_COLUNAS[column];
    }
    
    /**
     * Retorna o caminho correspondente à linha especificada
     * @param rowIndex
     * @return 
     */
    public Caminho getValueAt(int rowIndex)
    {
        return this.dados.get(rowIndex);
    }
}
