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
public class AlgoritimoGenetico {
    private Mochila mochila;
    private ArrayList<Elemento> genoma1;
    private ArrayList<Elemento> genoma2;
    private ArrayList<Elemento> genoma3;
    private ArrayList<Elemento> genoma4;
    private ArrayList<SolucaoMochila> solucoes;
    
    private ArrayList<Elemento> novoGenoma1;
    private ArrayList<Elemento> novoGenoma2;
    private ArrayList<Elemento> novoGenoma3;
    private ArrayList<Elemento> novoGenoma4;
    
    private int qtdGeracoes = 0;

   

    public AlgoritimoGenetico(int maxItens, int pesoMaxMochila, int valorMax, int pesoMaxItem){
        this.mochila = new Mochila();
        this.mochila.setPesoMax(pesoMaxMochila);
        this.mochila.getInventario().criarInventario(maxItens, pesoMaxItem, valorMax);  
        this.solucoes = new ArrayList<>();
    }
    
    public void start(int geracoes){
        int i=0;
        criarGenomasIniciais();
        
        while(i<geracoes){
            createCrossover();
            torneio();
            i++;
        }
        
    }
    
    
    private void criarGenomasIniciais(){
        System.out.println("=========== Criando genomas iniciais ===========");
        
        System.out.println("");
        //gerando 4 genomas
        for(int i=0;i<4;i++){
            ArrayList<Elemento> gen = new ArrayList<>();
            
            //gerando solucao respeitando limite de peso
            int peso = 0;
            for(int k=0;peso<this.mochila.getPesoMax();k++){
                Elemento e = this.mochila.getInventario().getElementoRandon();
                
                
                //caso a soma do peso for menor que o limite da mochila, add ao genoma
                if((peso+e.getPeso())<=this.mochila.getPesoMax()){
                    gen.add(e);
                    peso = peso+e.getPeso();
                }else{
                    break;
                }
            }
            
            if(i==0){
                this.genoma1 = gen;
                System.out.println("Itens em gen1: "+this.genoma1.size());
            }else if(i==1){
                this.genoma2 = gen;
                System.out.println("Itens em gen2: "+this.genoma2.size());
            }else if(i==2){
                this.genoma3 = gen;
                System.out.println("Itens em gen3: "+this.genoma3.size());
            }else if(i==3){
                this.genoma4 = gen;
                System.out.println("Itens em gen4: "+this.genoma4.size());
            }
            
            //limpando a lista de elementos selecionados, para que o novo genoma possa ter todas as opçoes iniciais possiveis 
            this.mochila.getInventario().getListaElementosSelecionados().clear();
        }
        
    }
    
   
    private void createCrossover(){
        /*  CROSSOVER:
                1° elemento com o 3°
                2° elemento com o 4°
                3° elemento com o 1°
                4° elemento com o 2°
        
                // ponto de corte do genoma é no meio aproximado, caso tamanho impar, primeira parte é maior
        */
        
        for(int i=1;i<=4;i++){//
            
            if(i==1){
                this.novoGenoma1 = crossover(this.genoma1, this.genoma3);
            }else if(i==2){
                this.novoGenoma2 = crossover(this.genoma2, this.genoma4);
            }else if(i==3){
                this.novoGenoma3 = crossover(this.genoma3, this.genoma1);
            }else if(i==4){
                this.novoGenoma4 = crossover(this.genoma4, this.genoma2);
            }
        }
    }
    
    private void torneio(){
        
        
        //REORDENAR GENOMAS POR EFICIÊNCIA: o novoGenoma cuja soma dos valores é maior 
        
        int valorTotalGen1 = 0;
        int valorTotalGen2 = 0;
        int valorTotalGen3 = 0;
        int valorTotalGen4 = 0;
        
        for(int j=1;j<=4;j++){
            ArrayList<Elemento> gen = new ArrayList<>();
           
            
            if(j==1){
                gen = this.novoGenoma1;
            }else if(j==2){
                gen = this.novoGenoma2;
            }else if(j==3){
                gen = this.novoGenoma3;
            }else if(j==4){
                gen = this.novoGenoma4;
            }
            
            for(int k=0;k<gen.size();k++){
                if(j==1){
                    valorTotalGen1 = valorTotalGen1 + gen.get(k).getValor();
                }else if(j==2){
                    valorTotalGen2 = valorTotalGen2 + gen.get(k).getValor();
                }else if(j==3){
                    valorTotalGen3 = valorTotalGen3 + gen.get(k).getValor();
                }else if(j==4){
                    valorTotalGen4 = valorTotalGen4 + gen.get(k).getValor();
                }
            }
        }
        
        //ordenado pelo valor com indiceGenoma-valor, ex: 3-957; 0-702; 2-353; 1-014
        ArrayList<String> ordemGenomas = ordenaGenomas(valorTotalGen1,valorTotalGen2,valorTotalGen3,valorTotalGen4); 
        /*
            COMPETIR:
            
                1° gen permanece
                Competir o 1° com o 2°
                Competir o 2° com o 3°
                Competir o 3° com o 4°
        */
        
        
        
        this.genoma1 = this.novoGenoma1;
        
        //competindo o 1° com o 2°
        if(valorTotalGen1 > valorTotalGen2){
            this.genoma2 = this.novoGenoma1;
        }else{
            this.genoma2 = this.novoGenoma2;
        }
        
        //Competir o 2° com o 3°
        if(valorTotalGen2 > valorTotalGen3){
           this.genoma3 = this.novoGenoma2; 
        }else{
            this.genoma3 = this.novoGenoma3; 
        }
        
        //Competir o 3° com o 4°
        if(valorTotalGen3 > valorTotalGen4){
            this.genoma4 = this.novoGenoma3;
        }else{
            this.genoma4 = this.genoma4;
        }
        
        // Guardando a melhor solução dessa geração
        
        String str = ordemGenomas.get(0);
        String[] vet = str.split("-");
        int aux = Integer.parseInt(vet[0]);
        
        SolucaoMochila solucao = new SolucaoMochila();
        
        switch (aux) {
            case 0:
                solucao.setListaElementos(this.novoGenoma1);
                solucao.setValorTotal(Integer.parseInt(vet[1]));
                solucao.setPesoTotal(calculaPesoDaSolucao(this.novoGenoma1));
                this.solucoes.add(solucao);
                break;
            case 1:
                solucao.setListaElementos(this.novoGenoma2);
                solucao.setValorTotal(Integer.parseInt(vet[1]));
                solucao.setPesoTotal(calculaPesoDaSolucao(this.novoGenoma2));
                this.solucoes.add(solucao);
                break;
            case 2:
                solucao.setListaElementos(this.novoGenoma3);
                solucao.setValorTotal(Integer.parseInt(vet[1]));
                solucao.setPesoTotal(calculaPesoDaSolucao(this.novoGenoma3));
                this.solucoes.add(solucao);
                break;
            case 3:
                solucao.setListaElementos(this.novoGenoma4);
                solucao.setValorTotal(Integer.parseInt(vet[1]));
                solucao.setPesoTotal(calculaPesoDaSolucao(this.novoGenoma4));
                this.solucoes.add(solucao);
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    //ex: 3-957; 0-702; 2-353; 1-014, sendo a =0, b=1,c=2,d=3
    private ArrayList<String>ordenaGenomas(int a, int b, int c,int d){
        ArrayList<String> lista = new ArrayList<>();
        // valor-genoma
        lista.add(a+"-0");
        lista.add(b+"-1");
        lista.add(c+"-2");
        lista.add(d+"-3");
        
        ArrayList<String> listaAux = new ArrayList<>();
        
        int maiorValor = -9999;
        int valor;
        int indice = -1;
        
        while(lista.size()>0){
            String genoma = "";
            for(int i=0;i<lista.size();i++){//percorrendo a lista de elementos
                String[] vet = lista.get(i).split("-");
                valor = Integer.parseInt(vet[0]);
                if(valor > maiorValor){
                    maiorValor = valor;
                    indice = i;
                    genoma = vet[1];
                }
            }
            
            //guardando o indice do genoma e o valor dele em ordem decrescente do valor, ex: 3-957; 0-702; 2-353; 1-014
            listaAux.add(genoma+"-"+maiorValor);
            
            if(lista.size()==0){
                break;
            }
           
            lista.remove(indice);
            maiorValor = -999;
            
        }
        
        
        return listaAux;
    }
    
    //preservando sempre a primeira metade do genA e cruzando com a segunda metade do genB
    private ArrayList<Elemento> crossover(ArrayList<Elemento> genA, ArrayList<Elemento> genB){ 
        ArrayList<Elemento> crossover = new ArrayList<>();
        
        int peso = 0;
        
        int indiceMeioA = (int)genA.size()/2;
        if(genA.size()%2==1){//caso o tamanho seja impar, ex: size = 5, indiceMeio = 2
            indiceMeioA++;// as alteraçoes comecarão a partir do proximo indice, ou seja o 3
        }//caso par o indice onde começará as alterações já está correto
        
        int indiceMeioB = (int) genB.size()/2;
        if(genB.size()%2==1){
            indiceMeioB++;
        }
        
        //copiando a primeira metade do genA
        for(int i =0;i<indiceMeioA;i++){
            crossover.add(genA.get(i));
            peso = peso + genA.get(i).getPeso();
        }
        
        //adicionando a segunda metade do genB respeitando o limite de peso da mochila
        for(int k =indiceMeioB;k<genB.size();k++){//percorrendo segunda metade de genB
            
            //EVITANDO ELEMENTOS DUPLICADOS
            
            //verificando se o elemento k de genB já está em genA
            Elemento e = genB.get(k);
            Elemento a = new Elemento();
            boolean existe = false;
            for(int p =0;p<indiceMeioA;p++){
                a = genA.get(p);
                if(a.getId() == e.getId()){
                    existe = true;
                }
            }
            
            //caso o elemento de genB exista em genA ele nao será atribuido no novo genoma
            if(existe ==false){
                peso = peso + genB.get(k).getPeso();
                if(peso <= this.mochila.getPesoMax()){//vendo se ao add este elemento ele excede o limite da mochila
                    crossover.add(genB.get(k));
                }
            }
            
        }
        
        return crossover;
    }
    
    
    private int calculaPesoDaSolucao(ArrayList<Elemento> lista){
        int pesoTotal =0;
        
        for(int i=0;i<lista.size();i++){
            pesoTotal = pesoTotal + lista.get(i).getPeso();
        }
        
        return pesoTotal;
    }
    
    public void printMelhorSolucao(){
        SolucaoMochila solucao = getMelhorSolucao();
        
        for(int i=0;i<solucao.getListaElementos().size();i++){
            solucao.getListaElementos().get(i).print();
        }
        System.out.println("O peso total é: "+solucao.getPesoTotal());
        System.out.println("O valor total é "+solucao.getValorTotal());
    }
    
    public void listarInventario(){
        this.mochila.getInventario().listarInventario();
    }
    
   
    
    private SolucaoMochila getMelhorSolucao(){
       
        int maiorValor = -999;
        int indice = -1;
        
        for(int i=0;i<this.solucoes.size();i++){
            if(this.solucoes.get(i).getValorTotal() > maiorValor){
                maiorValor = this.solucoes.get(i).getValorTotal();
                indice = i;
            }
        }
        
        return this.solucoes.get(indice);
    }
    
    public void printarGenomas(){
        System.out.println("________________ULTIMA GERAÇÃO____________________");
        for(int i=0;i<4;i++){
            ArrayList<Elemento> gen = new ArrayList<>();
           
            
            if(i==0){
                gen = this.novoGenoma1;
            }else if(i==1){
                gen = this.novoGenoma2;
            }else if(i==2){
                gen = this.novoGenoma3;
            }else if(i==3){
                gen = this.novoGenoma4;
            }
            System.out.println("------ GENOMA "+i+": ------");
            for(int k=0;k<gen.size();k++){
                
                gen.get(k).print();
            }
        }
    }
}
