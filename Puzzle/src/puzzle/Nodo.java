/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

/**
 *
 * @author Renan
 */
public class Nodo{
    public Nodo pai;
    public int[][] conteudo;
    public double valorH;
    public String texto;
    public int nivel;

    Nodo(Nodo pai, int[][] conteudo, double valorH,int nivel) {
        this.pai = pai;
        this.conteudo = conteudo;
        this.valorH = valorH;
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        return (int) this.valorH ;
    }
    
}
