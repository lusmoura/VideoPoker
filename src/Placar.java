/**
 * Gera valores que o jogador ganha de acordo à mão que ele possui
 */

import java.util.Arrays;
import java.util.Comparator;

public class Placar {
    /**
     * Construtor
     */
    public Placar() {
    }

    /**
     * Confere a mão do jogador e gera valor que ele deve receber
     * @param aposta valor da aposta do jogador
     * @param mao conjunto de cartas da mao do joagdor
     * @return o valor obtido com a mão atual
     */
    public int adicionar(int aposta, Mao mao){
        boolean isFlush = true, isStraight = true;
        int firstFreq = 0, medianFreq = 0, lastFreq = 0;

        if(mao == null || aposta == 0) return 0;

        // ordena cartas da mão
        Arrays.sort(mao.getCartas(), 0, 5);

        // checa se a mao é um Flush, ou seja, se todas as cartas tem mesmo naipe
        for(int i = 0; i < 5; ++i) {
            if(mao.getCartas()[0].getNaipe() != mao.getCartas()[i].getNaipe())
                isFlush = false;
        }

        //Checa se é um Straight(sequencia)
        for(int i = 1; i < 5; ++i) {
            if(mao.getCartas()[i].getNum() - mao.getCartas()[i - 1].getNum() != 1)
                isStraight = false;
        }

        //Computa a frequencia da carta de menor valor
        for(int i = 0; i < 5; ++i)
            if(mao.getCartas()[0].getNum() == mao.getCartas()[i].getNum())
                firstFreq += 1;
        //Computa a frequencia da carta de valor médio
        for(int i = 0; i < 5; ++i)
            if(mao.getCartas()[2].getNum() == mao.getCartas()[i].getNum())
                medianFreq += 1;
        //Computa a frequencia da carta de maior valor
        for(int i = 0; i < 5; ++i)
            if(mao.getCartas()[4].getNum() == mao.getCartas()[i].getNum())
                lastFreq += 1;

        //Checa se temos um Straight Flush
        if(isFlush && isStraight){
            if(mao.getCartas()[0].getNum() == 10)    //Se a menor for 10, tem que ser Royal Straight Flush
                return 200*aposta;
            else
                return 100*aposta;
        }

        //Checa se tem uma quadra
        if(firstFreq == 4 || lastFreq == 4)
            return 50*aposta;

        //Checa se tem um full house
        if(firstFreq + lastFreq == 5)
            return 20*aposta;

        // Checa se é um flush
        if(isFlush)
            return 10*aposta;

        //Checa se é um straight
        if(isStraight)
            return 5*aposta;

        //Checa se é uma trinca
        if(firstFreq == 3 || medianFreq == 3 || lastFreq == 3)
            return 2*aposta;

        //Checa se é 2 pares
        if((firstFreq == 2 && medianFreq == 2)  || (firstFreq == 2 && lastFreq == 2) || (medianFreq == 2 && lastFreq == 2))
            return aposta;

        return 0;
    }

    public String getResult(Mao mao) {
        boolean isFlush = true, isStraight = true;
        int firstFreq = 0, medianFreq = 0, lastFreq = 0;

        if(mao == null) return "Algo está errado";

        // ordena cartas da mão
        Arrays.sort(mao.getCartas(), 0, 5);

        // checa se a mao é um Flush, ou seja, se todas as cartas tem mesmo naipe
        for(int i = 0; i < 5; ++i) {
            if(mao.getCartas()[0].getNaipe() != mao.getCartas()[i].getNaipe())
                isFlush = false;
        }

        //Checa se é um Straight(sequencia)
        for(int i = 1; i < 5; ++i) {
            if(mao.getCartas()[i].getNum() - mao.getCartas()[i - 1].getNum() != 1)
                isStraight = false;
        }

        //Computa a frequencia da carta de menor valor
        for(int i = 0; i < 5; ++i)
            if(mao.getCartas()[0].getNum() == mao.getCartas()[i].getNum())
                firstFreq += 1;
        //Computa a frequencia da carta de valor médio
        for(int i = 0; i < 5; ++i)
            if(mao.getCartas()[2].getNum() == mao.getCartas()[i].getNum())
                medianFreq += 1;
        //Computa a frequencia da carta de maior valor
        for(int i = 0; i < 5; ++i)
            if(mao.getCartas()[4].getNum() == mao.getCartas()[i].getNum())
                lastFreq += 1;

        //Checa se temos um Straight Flush
        if(isFlush && isStraight){
            if(mao.getCartas()[0].getNum() == 10)    //Se a menor for 10, tem que ser Royal Straight Flush
                return "Royal Straight Flush! Top demaaais!";
            else
                return "Straight Flush! yaay";
        }

        //Checa se tem uma quadra
        if(firstFreq == 4 || lastFreq == 4)
            return "Quadra! Boa!";

        //Checa se tem um full house
        if(firstFreq + lastFreq == 5)
            return "Woow, Full House!";

        // Checa se é um flush
        if(isFlush)
            return "Oloco, Flush!";

        //Checa se é um straight
        if(isStraight)
            return "Straight, aí sim!";

        //Checa se é uma trinca
        if(firstFreq == 3 || medianFreq == 3 || lastFreq == 3)
            return "Trincaa!";

        //Checa se é 2 pares
        if((firstFreq == 2 && medianFreq == 2)  || (firstFreq == 2 && lastFreq == 2) || (medianFreq == 2 && lastFreq == 2))
            return "Dois Pares! Melhor que nada...";

        return "Poxa, nadinha... =(";
    }
}