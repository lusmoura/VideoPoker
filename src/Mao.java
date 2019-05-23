/**
 * Conjunto de cartas da mão do jogador
 */
public class Mao {

    Deck d;
    private Carta auxiliar;
    private Carta[] cartas;
    private int quantidade;

    /**
     * Construtor que inicializa as n cartas da mão
     * @param n quantidade de cartas na mão
     */
    public Mao(int n, Deck d) {
        this.d = d;
        this.quantidade = n;
        cartas = new Carta[n];

        for(int i = 0; i <  quantidade; i++) {
            cartas[i] = d.pegarCarta();
        }
    }

    /**
     * Getter das cartas
     * @return vetor de cartas da mão
     */
    public Carta[] getCartas() {
        return cartas;
    }

    /**
     * Getter de uma carta na posição específica
     * @param i posição da carta
     * @return carta na posição
     */
    public Carta getCartas(int i) {
        return cartas[i];
    }

    public void setCartas(int i, int num, int naipe) {
        cartas[i].setNum(num);
        cartas[i].setNaipe(naipe);
    }

    /**
     * Devolve as cartas que estao na mao, embaralha o baralho e em seguida
     * Puxa novas cartas para a mão, gerando novos valores para todas as n cartas da mão
     * @return vetor de cartas da mão
     */
    public Carta[] puxar(){
        for(int i = 0; i < 5; ++i) {
            auxiliar = cartas[i];
            cartas[i] = d.pegarCarta();
            d.inserirCarta(auxiliar);

        }
        d.embaralhar();
        for(int i = 0; i < quantidade; i++) {
            cartas[i] = d.pegarCarta();
        }
        return cartas;
    }

    /**
     * Puxa novas cartas para a mão, de acordo às posições insicadas na string
     * E retorna as cartas antigas para o deck
     * @param s string de cartas que devem ser sobrescritas
     * @return vetor de cartas da mão
     */
    public Carta[] puxar (String s) {
        String quais[] = s.split(" ");

        for(int i = 0; i < quais.length; i++) {
            int pos = Integer.parseInt(quais[i]);
            if(pos <= quantidade && pos >= 1) {
                pos--;
                auxiliar = cartas[pos];
                cartas[pos] = d.pegarCarta();
                d.inserirCarta(auxiliar);
            }
            else
                throw new IllegalArgumentException("Parâmetros inválidos");


        }

        return cartas;
    }

    public Carta[] puxar(boolean[] status) {
        for(int i = 0; i < 5; i++) {
            if(status[i]) {
                auxiliar = cartas[i];
                cartas[i] = d.pegarCarta();
                d.inserirCarta(auxiliar);
            }
        }
        System.out.println();

        return cartas;
    }


}