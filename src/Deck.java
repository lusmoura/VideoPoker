/**
 * Um deque de cartas, contém todas as 52 cartas do baralho
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List <Carta> baralho = new ArrayList<Carta>();

    /**
     * Construtor do Deck - cria todas as cartas, as quais são inseridas numa lista (baralho)
     */
    public Deck() {
        for(int i = 0; i < 4; ++i) {
            for (int j = 2; j < 15; ++j) {
                Carta c = new Carta(i, j);
                baralho.add(c);
            }
        }

        // embaralha o baralho para não inicializa-lo na ordem em que foi gerado
        Collections.shuffle(baralho);
    }

    /**
     * Retira a carta do topo do baralho e a retorna
     * @return carta retirada
     */
    public  Carta pegarCarta(){
        if(baralho.isEmpty()){
            throw new IllegalArgumentException("Baralho vazio");
        }
        Carta retorno = baralho.get(0);
        baralho.remove(0);
        return retorno;
    }

    /**
     * Insere uma carta no final do baralho
     * @param card carta a ser inserida
     */
    public void inserirCarta(Carta card){
        baralho.add(card);
    }

    /**
     * Embaralha o baralho de cartas
     */
    public void embaralhar() {
        Collections.shuffle(baralho);
    }
}