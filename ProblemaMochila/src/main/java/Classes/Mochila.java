/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
public class Mochila {

    
    
    private int pesoMax;
    private Inventario inventario;
    private ArrayList<Elemento> melhorSolucao;
    private ArrayList<List> solucoes;
    
    
    public Mochila(){
        this.pesoMax = -1;
        this.inventario = new Inventario();
        this.melhorSolucao = new ArrayList<>();
        this.solucoes = new ArrayList<>();
    }
    
    /**
     * @return the pesoMax
     */
    public int getPesoMax() {
        return pesoMax;
    }

    /**
     * @param pesoMax the pesoMax to set
     */
    public void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }

    /**
     * @return the inventario
     */
    public Inventario getInventario() {
        return inventario;
    }

    /**
     * @param inventario the inventario to set
     */
    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    /**
     * @return the melhorSolucao
     */
    public ArrayList<Elemento> getMelhorSolucao() {
        return melhorSolucao;
    }

    /**
     * @param melhorSolucao the melhorSolucao to set
     */
    public void setMelhorSolucao(ArrayList<Elemento> melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    /**
     * @return the solucoes
     */
    public ArrayList<List> getSolucoes() {
        return solucoes;
    }

    /**
     * @param solucoes the solucoes to set
     */
    public void setSolucoes(ArrayList<List> solucoes) {
        this.solucoes = solucoes;
    }
    
}
