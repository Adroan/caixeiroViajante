/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Image;

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
    public Image imagem;

    public Cidade(int DIST, String nome, int x, int y) {
        this.INDICE = DIST;
        this.nome = nome;
        this.foiVisitado = foiVisitado;
        this.x = x;
        this.y = y;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isFoiVisitado() {
        return foiVisitado;
    }

    public void setFoiVisitado(boolean foiVisitado) {
        this.foiVisitado = foiVisitado;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getINDICE() {
        return INDICE;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
    
    
    
    
    
    
}
