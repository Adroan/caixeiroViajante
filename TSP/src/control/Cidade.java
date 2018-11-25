/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Adroan
 */
public class Cidade {

    /**
     * 
     */
    public final int INDICE;
    public String nome;
    public boolean foiVisitado;
    public int x;
    public int y;

    public Cidade(int DIST, String nome, int x, int y) {
        this.INDICE = DIST;
        this.nome = nome;
        this.foiVisitado = foiVisitado;
        this.x = x;
        this.y = y;
    }
    
    
}
