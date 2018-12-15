/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.graficos;

/**
 *
 * @author Henrique
 */
public class Ponto2D
{
    public float x;
    public float y;

    public Ponto2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Ponto2D(float x, float y)
    {
        this.x = Graficos.arredondar2(x);
        this.y = Graficos.arredondar2(y);
    }

    public Ponto2D(double x, double y)
    {
        this.x = Graficos.arredondar2(x);
        this.y = Graficos.arredondar2(y);
    }

    public Ponto2D()
    {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String toString()
    {
        return String.format("Ponto2D(x=%f;y=%f)", this.x,this.y);
    }

    /**
     * Obtém uma cópia do ponto deslocada conforme os incrementos fornecidos
     * @param incrementoX Incremento (positivo ou negativo) no eixo X
     * @param incrementoY Incremento (positivo ou negativo) no eixo Y
     * @return 
     */
    public Ponto2D getDeslocamento(double incrementoX, double incrementoY)
    {
        return new Ponto2D(this.x+incrementoX, this.y+incrementoY);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Ponto2D))
        {
            return false;
        }
        else
        {
            Ponto2D outro = (Ponto2D)obj;
            return this.x == outro.x && this.y == outro.y;
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

}
