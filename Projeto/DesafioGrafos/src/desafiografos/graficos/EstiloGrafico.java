/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

/**
 *
 * @author Henrique
 */
public class EstiloGrafico
{
    private final Color texto;
    private final Color borda;
    private final Color preenchimento;
    private final Stroke estiloDoTexto;
    private final Stroke estiloDaBorda;
    private final Font fonteDoRotulo;
    private final Font fonteDoVertice;
    
    public EstiloGrafico(Color texto,Color borda,Color preenchimento, Stroke estiloDoTexto,Stroke estiloDaBorda, Font fonteDoRotulo, Font fonteDoVertice)
    {
        this.texto=texto;
        this.borda=borda;
        this.preenchimento=preenchimento;
        this.estiloDoTexto=estiloDoTexto;
        this.estiloDaBorda=estiloDaBorda;
        this.fonteDoRotulo=fonteDoRotulo;
        this.fonteDoVertice=fonteDoVertice;
    }
    
    /**
     * @return the texto
     */
    public Color getTexto()
    {
        return texto;
    }

    /**
     * @return the borda
     */
    public Color getBorda()
    {
        return borda;
    }

    /**
     * @return the preenchimento
     */
    public Color getPreenchimento()
    {
        return preenchimento;
    }

    /**
     * @return the estiloDoTexto
     */
    public Stroke getEstiloDoTexto()
    {
        return estiloDoTexto;
    }

    /**
     * @return the estiloDaBorda
     */
    public Stroke getEstiloDaBorda()
    {
        return estiloDaBorda;
    }

    /**
     * @return the fonteDoRotulo
     */
    public Font getFonteDoRotulo()
    {
        return fonteDoRotulo;
    }

    /**
     * @return the fonteDoRotulo
     */
    public Font getFonteDoVertice()
    {
        return fonteDoVertice;
    }
}
