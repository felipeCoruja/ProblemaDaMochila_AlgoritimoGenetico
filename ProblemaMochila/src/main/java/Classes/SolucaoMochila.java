/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author felipe
 */
public class SolucaoMochila {

    
    private int valorTotal;
    private int pesoTotal; 
    private ArrayList<Elemento> listaElementos;
    
    
    public SolucaoMochila(){
        this.setListaElementos(new ArrayList<>());
        this.setValorTotal(-1);
        this.setPesoTotal(-1);
    }
    
    /**
     * @return the valorTotal
     */
    public int getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the pesoTotal
     */
    public int getPesoTotal() {
        return pesoTotal;
    }

    /**
     * @param pesoTotal the pesoTotal to set
     */
    public void setPesoTotal(int pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    /**
     * @return the listaElementos
     */
    public ArrayList<Elemento> getListaElementos() {
        return listaElementos;
    }

    /**
     * @param listaElementos the listaElementos to set
     */
    public void setListaElementos(ArrayList<Elemento> listaElementos) {
        this.listaElementos = listaElementos;
    }
}
