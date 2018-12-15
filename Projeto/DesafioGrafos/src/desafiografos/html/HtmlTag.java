/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.html;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class HtmlTag
{
    public enum TipoDeNo
    {
        html,
        body,
        ul,
        li
    }
    /**
     * Lista de nós filhos
     */
    public final List<HtmlTag> filhos = new ArrayList<>();
    public String texto;
    public final TipoDeNo tipo;
    
    public HtmlTag(TipoDeNo tipo)
    {
        this.tipo = tipo;
        this.texto=null;
    }
    
    public HtmlTag(TipoDeNo tipo, String texto)
    {
        this.tipo = tipo;
        this.texto = texto;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<%s>", this.tipo.toString()));
        if (this.texto!=null) sb.append(this.texto);
        for (HtmlTag filho:this.filhos)
        {
            sb.append(filho.toString());
        }
        sb.append(String.format("</%s>", this.tipo.toString()));
        return sb.toString();
    }
    
    
}
