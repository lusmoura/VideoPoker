import java.util.Comparator;

/**
 * Uma carta, possui um naipe e um valor
 */
public class Carta implements Comparable<Carta>{

    private int num;
    private Character naipe;
    private static Character[] naipes = {'C', 'D', 'H', 'S'};
    private static String[] valores = {"0", "0", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    /**
     * Construtor para uma carta aleatória
     */
    public Carta() {
        num = new Random().getIntRand(2, 15);
        int pos = new Random().getIntRand(0, 4);
        naipe = naipes[pos];
    }

    /**
     * Construtor para uma carta específica
     * @param naipe numero da posição do naipe da carta no array de naipes
     * @param num valor da carta
     */
    public Carta(int naipe, int num) {
        this.num = num;
        this.naipe = naipes[naipe];
    }

    /**
     * Getter do valor da carta
     * @return valor da carta
     */
    public int getNum() {
        return num;
    }

    public void setNum(int newNum) {
        num = newNum;
    }

    public String getValue() {
        return valores[num];
    }

    /**
     * Getter do naipe da carta
     * @return naipe da carta
     */
    public Character getNaipe() {
        return naipe;
    }

    public void setNaipe(int newNaipe) {
        naipe = naipes[newNaipe];
    }


    /**
     * Compara duas cartas e diz qual é maior valor
     * @param outraCarta carta para ser comparada
     * @return Positivo se a carta que chamou o método é maior que a outra, negativo caso contrário
     * e 0 se iguais
     */
    @Override
    public int compareTo(Carta outraCarta) {
        return this.getNum() - outraCarta.getNum();
    }

}