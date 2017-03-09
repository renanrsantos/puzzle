/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renan
 */
public class Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // depois implementar get para estes métodos
        int[][] o = {{2,6,0},{7,4,5},{3,1,8}};
        int[][] d = {{1,2,3},{4,5,6},{7,8,0}};
        Nodo resultado = new Nodo(null,o, 0,1);
        resultado.texto = "Início";
        Nodo destino = new Nodo(null,d,0,1);
        List<Nodo>a = new ArrayList(); // lista de abertos
        List<Nodo>f = new ArrayList(); // lista de fechados
        int nodosTestados = 0;
        int profundidade = 0;
        
        while(!Funcoes.arraysIguais(resultado.conteudo, destino.conteudo)){
            // Se for o primeiro registro adiciona na lista para fechá-lo depois
            if(nodosTestados ==0){
                a.add(resultado);
            }
            if(profundidade < resultado.nivel){
                profundidade = resultado.nivel;
            }
            nodosTestados++;
            // processamento dos filhos para retornar a melhor opcao
            if (resultado.nivel < 30){ // define o nivel maximo de profundidade
                List<Nodo> listaMovimentos = Funcoes.movimentosPossiveis(resultado);
                for (Nodo movimento : listaMovimentos) {
                    if(!Funcoes.nodeExist(f, movimento)){
                        // calcular a heuristica
                        movimento.valorH = Funcoes.calcHeuristica(movimento.conteudo, destino.conteudo);
                        // adicionar na lista dos baertos
                        a.add(movimento);
                    }
                }
            }
            Funcoes.fecharNode(f, a, resultado); // apenas para fechar o atual.
            resultado = Funcoes.getProximo(a); // ordenará a lista e pegará o próximo
        }
        String texto = "";
        int movimentos = -1;
        // Após o término monta o texto a ser impresso
        while(resultado != null){
            movimentos++;
            texto = resultado.texto+ " -> "+"\n" + texto;
            resultado = resultado.pai;
        }
        texto += "Acabou!";
        System.out.println("Total de nodos testados: " + String.valueOf(nodosTestados));
        System.out.println("Profundidade: "+ String.valueOf(profundidade));
        System.out.println("Total de movimentos: "+ String.valueOf(movimentos));
        System.out.println(texto);
    }
    
}
