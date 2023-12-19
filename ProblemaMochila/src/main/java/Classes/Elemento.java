/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author felipe
 */
public class Elemento {
    private int peso;
    private int valor;
    private int id;
    
    
    public Elemento(){
        this.peso = -1;
        this.valor = -1;
        this.id = -1;
    }
    
    public void print(){
        System.out.println("----------------");
        System.out.println("ID: "+this.id);
        System.out.println("Valor: "+this.valor);
        System.out.println("Peso: "+this.peso);
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
