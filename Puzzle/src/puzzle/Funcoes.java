/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Renan
 */
public class Funcoes {
    public static boolean nodeExist(List<Nodo> fechado, Nodo nodoBusca){
        boolean result = false;
        for (int i = 0; i < fechado.size(); i++) {
            Nodo get = fechado.get(i);
            if (arraysIguais(get.conteudo,nodoBusca.conteudo)){
                result = true;
                return result;
            }    
        }
        return result;
    }   
    
    public static void fecharNode(List<Nodo> fechado, List<Nodo> aberto, Nodo nodoBusca){
        fechado.add(nodoBusca);
        // remover da lista de abertos
        for (int i = 0; i < aberto.size(); i++) {
            Nodo get = aberto.get(i);
            if (arraysIguais(get.conteudo,nodoBusca.conteudo)){
                aberto.remove(i); 
            }
        }
    }
    
    // método para calcular o valor do estado
    public static double calcHeuristica(int[][] origem, int[][] destino){
        double result = 0;
        for(int i = 0; i < origem.length; i++){
            for(int j = 0; j < origem[i].length; j++){
                if(origem[i][j] != destino[i][j]){
                    double pi = (origem[i][j]-1)/3;	// calcula linha correta, baseado no valor da pe�a
                    double pj = origem[i][j]-pi*3-1;			// calcula coluna correta
                    double d = Math.abs(j-pj) + Math.abs(i-pi);// Manhattan Distance
                    result += d;
                }
            }
        }
        return result;
    }
//    public static double calcHeuristica(int[][] origem, int[][] destino){
//        double result = 0;
//        for(int i = 0; i < origem.length; i++){
//            for(int j = 0; j < origem[i].length; j++){
//                if(origem[i][j] != destino[i][j]){
//                    result++;
//                }
//            }
//        }
//        return result;
//    }
    
    // função utilizada para criar o novo registro
    // depois será adicionada na lista
    public static Nodo novoRegistro(Nodo pai,int x_nulo, int y_nulo, int x_valor, int y_valor){
        int[][] conteudo = new int[3][3];
        for (int i = 0; i < pai.conteudo.length; i++) {
            System.arraycopy(pai.conteudo[i], 0, conteudo[i], 0, pai.conteudo[i].length);
        }
        conteudo[x_nulo][y_nulo] = conteudo[x_valor][y_valor];
        conteudo[x_valor][y_valor] = 0;
        Nodo result = new Nodo(pai,conteudo,(double) 0,pai.nivel+1);
        result.texto = "Mova: " + String.valueOf(conteudo[x_nulo][y_nulo]);
        return result;
    }
    
    // Função utilizada para listar os movimentos possiveis na tela
    public static List<Nodo> movimentosPossiveis(Nodo atual){
        List<Nodo> lista = new ArrayList();
        for (int i = 0; i < atual.conteudo.length; i++) {
            for (int j = 0; j < atual.conteudo[i].length; j++) {
                if(atual.conteudo[i][j] == 0){
                    if(i > 0){
                        lista.add(novoRegistro(atual,i,j,i-1,j));
                    }
                    if(i < 2){
                        lista.add(novoRegistro(atual,i,j,i+1,j));
                    }
                    if(j > 0){
                        lista.add(novoRegistro(atual,i,j,i,j-1));
                    }
                    if(j < 2){
                        lista.add(novoRegistro(atual,i,j,i,j+1));
                    }
                }
            }
        }
        return lista;
    }
    
    // Função utilizada para ordenação da lista e retorno do primeiro item
    public static Nodo getProximo(List<Nodo> lista){
        // ordenação da lista;
        Comparador c = new Comparador();
        lista.sort(c);
        Nodo retorno = lista.get(0);
        return retorno;
    }

    // Função utilizada para comparar os arrays
    // Visto que o Java interpreta uma matriz como um objeto
    public static boolean arraysIguais(int[][] a1, int[][] a2) {
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                if(a1[i][j] != a2[i][j]){
                    return false;
                }
            }
        }
         return true;
    }
    
}
