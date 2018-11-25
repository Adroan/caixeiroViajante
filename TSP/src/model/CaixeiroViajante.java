/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.Vertice;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Adroan
 */
public class CaixeiroViajante {

    private static int km = 0;
    private static ArrayList<Vertice> vertices = new ArrayList<Vertice>();

    private static int cidadeInicial = 0;

    private static String[] nomes = {"Al Kharid", "Brimhaven", "Camelot", "Darkmeyer", "Essim", "Faladore", "Georgopol","Hollow's","IO", "Jotunheim","kiev","Los Santos","Mount Chiliad","Normandia","Orpheus"};

//    private static int[][] matrizAdjacentes
//            = {{0, 494, 366, 266, 173, 140, 146},
//              {494, 0, 160, 138, 146, 200, 313},
//              {366, 160, 0, 101, 193, 310, 380},
//              {266, 138, 101, 0, 97, 153, 271},
//              {173, 146, 193, 97, 0, 80, 212},
//              {140, 200, 310, 153, 80, 0, 151},
//              {146, 313, 380, 271, 212, 151, 0}};
    private static int[][] matrizAdjacentes = {{0  ,42 ,418,966,236,829,859,115,30 ,211,59 ,322,427,880,938},
                                                {42 ,0  ,785,314,866,64 ,191,54 ,588,419,862,563,758,62 ,820},
                                                {418,785,0  ,377,967,833,718,80 ,567,978,89 ,562,275,431,622},
                                                {966,314,377,0  ,498,207,953,961,657,668,896,184,58 ,292,842},
                                                {236,866,967,498,0  ,908,299,48 ,552,178,535,164,297,453,350},
                                                {829,64 ,833,207,908,0  ,621,507,664,635,104,426,272,252,655},      
                                                {859,191,718,953,299,621,0  ,900,12 ,939,998,110,846,56 ,348},   
                                                {115,54 ,80 ,961,48 ,507,900,0  ,458,868,407,950,515,591,736},
                                                {30 ,588,567,657,552,664,12 ,458,0  ,196,828,325,65 ,714,890},  
                                                {211,419,978,668,178,635,939,868,196,0  ,481,228,36 ,361,571},    
                                                {59 ,862,89 ,896,535,104,998,407,828,481,0  ,97 ,138,818,203},   
                                                {322,563,562,184,164,426,110,950,325,228,97 ,0  ,857,683,891},    
                                                {427,758,275,58 ,297,272,846,515,65 ,36 ,138,857,0  ,803,49 },   
                                                {880,62 ,431,292,453,252,56 ,591,714,361,818,683,803,0  ,15 },
                                                {938,820,622,842,350,655,346,736,890,571,203,891,49 ,15 ,0  }};
                                                    
    private static ArrayList<Vertice> acharVizinho(Vertice nodo) {
        ArrayList<Vertice> vizinho = new ArrayList<Vertice>();
        for (int i = 0; i < matrizAdjacentes.length; i++) {
            if (matrizAdjacentes[nodo.INDICE][i] != 0) {
                vizinho.add(vertices.get(i));
            }
        }
        return vizinho;
    }

    private static Vertice foiVisitado(ArrayList<Vertice> vizinho) {
        for (Vertice n : vizinho) {
            if (n.foiVisitado == false) {
                return n;
            }
        }
        return null;
    }

    private static Vertice maisPerto(ArrayList<Vertice> vizinho, Vertice inicio) {
        Vertice n = foiVisitado(vizinho);
        if (n == null) {
            return null;
        }
        for (int i = 1; i < vizinho.size(); i++) {
            if (matrizAdjacentes[inicio.INDICE][n.INDICE] > matrizAdjacentes[inicio.INDICE][vizinho.get(i).INDICE] && vizinho.get(i).foiVisitado == false) {
                n = vizinho.get(i);
            }
        }
        km += matrizAdjacentes[inicio.INDICE][n.INDICE];
        return n;
    }

    private static void printaCaminho(Vertice entrada) {
        Vertice cauda = entrada;
        Vertice inicio = entrada;
        Vertice atual = entrada;
        System.out.print(inicio.nome);
        inicio.foiVisitado = true;
        while (atual != null) {
            ArrayList<Vertice> vizinho = acharVizinho(atual);
            atual = maisPerto(vizinho, atual);
            if (atual != null) {
                atual.foiVisitado = true;
                System.out.print("  ->  " + atual.nome);
                cauda = atual;
            }
        }
        System.out.print("  ->  " + inicio.nome+ "\n");
        km += matrizAdjacentes[cauda.INDICE][inicio.INDICE];
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < matrizAdjacentes.length; i++) {
            vertices.add(new Vertice(i, nomes[i]));
            
        }
        cidadeInicial = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número correspondente a cidade inicial: \n" + 
                                                        "0 - Absolut \n" + 
                                                        "1 - Brahma \n" +
                                                        "2 - Corote \n" +
                                                        "3 - Dreher \n" +
                                                        "4 - Eisenbahn \n" +
                                                        "5 - Fanta uva \n" +
                                                        "6 - Graspa \n"));
        Date inicial = new Date();
        System.out.println("Caminho:");
        printaCaminho(vertices.get(cidadeInicial));
        System.out.print("\n\n Kilometros viajados:\t "+ km+"\n");
        Date acabou = new Date();
        System.out.println(acabou.getTime() - inicial.getTime()+" ms");
    }
}
