/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.problemamochila;

import Classes.AlgoritimoGenetico;

/**
 *
 * @author felipe
 */
public class ProblemaMochila {

    public static void main(String[] args) {
        
        int maxItens = 20;
        int pesoMaxMochila = 50;
        int pesoMaxItem = 25;
        int valorMax = 1000;
        AlgoritimoGenetico algoritimoGenetico = new AlgoritimoGenetico(maxItens,pesoMaxMochila,valorMax,pesoMaxItem);
        
        
        algoritimoGenetico.listarInventario();
        
        algoritimoGenetico.start(30);
        algoritimoGenetico.printarGenomas();
        System.out.println("----------Melhor Solução-----------------");
        algoritimoGenetico.printMelhorSolucao();
    }
}
