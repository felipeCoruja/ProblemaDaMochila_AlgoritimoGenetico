/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author felipe
 */
public class Inventario {
    private ArrayList<Elemento> listaDeElementos;
    private ArrayList<String> listaElementosSelecionados;
    
    
    public Inventario(){
        this.setListaDeElementos(new ArrayList<>());
        this.listaElementosSelecionados = new ArrayList<>();
        
    }
    
    public void criarInventario(int quantElementos, int maxPeso, int maxValor){
        
        for(int i=0;i<quantElementos;i++){
            Elemento e = new Elemento();
            int pesoGerado = gerarNumero(maxPeso);
            int valorGerado = gerarNumero(maxValor);
            e.setId(i);
            e.setPeso(pesoGerado);
            e.setValor(valorGerado);
            this.listaDeElementos.add(e);
        }
        
        System.out.println("Inventario Criado! Quantidade de itens: " +this.listaDeElementos.size());
    }
    
    public void listarInventario(){
        for(int i=0;i<this.listaDeElementos.size();i++){
            this.listaDeElementos.get(i).print();
        }
    }
    
    //retorna um elemento do inventario que ainda não foi selecionado
    public Elemento getElementoRandon(){
        /*
            cria uma varavel auxiliar do inventario
            sorteia um indice do inventario auxiliar
            verifica se esse indice está no vetor de itens já selecionados
            caso estiver
                remove o item do inventario auxiliar
                sorteia novamente do inventario auxiliar
            caso nao
                retorna o item
        */
        
        
        Elemento aux = new Elemento();
        int numeroGerado;
        boolean flag = false;//significa que o ID nao esta em listaElementosSelecionados
        
        ArrayList<Elemento> listaElementosAux = this.listaDeElementos;
        
        while(true){
            
                Random random = new Random();
                //gerando numero inteiro entre 0 e (max de elementos -1) do inventario
                
                numeroGerado = gerarNumero(listaElementosAux.size());

                //verificando se o elemento já foi escolhido antes
                String strAux = "";
                flag = false;//resetando a variavel

                //percorrendo a lista de selecionados
                for(int j=0;j<this.listaElementosSelecionados.size();j++){
                    strAux = this.listaElementosSelecionados.get(j);
                    int numeroAux = Integer.parseInt(strAux);
                    if(numeroGerado == numeroAux){//verificando se o id gerado esta na lista de selecionados
                        flag = true;//significa que está na lista

                        if(listaElementosAux.size()>0){
                            listaElementosAux.remove(numeroGerado);//removendo elemento para sortear novamente sem risco de cair nele denovo

                        }

                    }
                }
            
            if(flag ==false){
                aux = this.listaDeElementos.get(numeroGerado); 
                this.listaElementosSelecionados.add(aux.getId()+"");
               
                break;
            }
            
            
        }
       
        return aux;
       
    }
  

    private int gerarNumero(int max){
        double randomNumber = Math.random() * max;
        int aux = (int)randomNumber;
        return aux;
    }
    /**
     * @return the listaDeElementos
     */
    public ArrayList<Elemento> getListaDeElementos() {
        return listaDeElementos;
    }

    /**
     * @param listaDeElementos the listaDeElementos to set
     */
    public void setListaDeElementos(ArrayList<Elemento> listaDeElementos) {
        this.listaDeElementos = listaDeElementos;
    }

    /**
     * @return the listaElementosSelecionados
     */
    public ArrayList<String> getListaElementosSelecionados() {
        return listaElementosSelecionados;
    }

    /**
     * @param listaElementosSelecionados the listaElementosSelecionados to set
     */
    public void setListaElementosSelecionados(ArrayList<String> listaElementosSelecionados) {
        this.listaElementosSelecionados = listaElementosSelecionados;
    }
}
