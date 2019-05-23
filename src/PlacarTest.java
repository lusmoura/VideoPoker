import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class PlacarTest {
    Placar p;
    Deck d;
    Mao mao;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p = new Placar();
        d = new Deck();
        mao = new Mao(5, d);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        p = null;
        d = null;
        mao = null;
    }

    @org.junit.jupiter.api.Test
    void adicionarNull() {
        int res = p.adicionar(0, null);
        assertEquals(res, 0);
    }


    @org.junit.jupiter.api.Test
    void getResultNull() {
        String res = p.getResult(null);
        assertEquals(res, "Algo está errado");

    }

    @org.junit.jupiter.api.Test
    void adicionarNada() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 10, 2);

        mao.setCartas(2, 2, 1);
        mao.setCartas(3, 12, 2);

        int ret = p.adicionar(1, mao);
        assertEquals(ret, 0);
    }


    @org.junit.jupiter.api.Test
    void getResultNada() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 10, 2);

        mao.setCartas(2, 2, 1);
        mao.setCartas(3, 12, 2);


        String ret = p.getResult(mao);
        assertEquals(ret, "Poxa, nadinha... =(");

    }


    @org.junit.jupiter.api.Test
    void adicionarDupla() {
        mao.puxar();
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);

        mao.setCartas(2, 2, 1);
        mao.setCartas(3, 2, 2);

        mao.setCartas(4, 3, 2);


        int ret = p.adicionar(1, mao);
        assertEquals(1, ret);
    }

    @org.junit.jupiter.api.Test
    void getResultDupla() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);

        mao.setCartas(2, 2, 1);
        mao.setCartas(3, 2, 2);

        mao.setCartas(4, 3, 2);


        String ret = p.getResult(mao);
        assertEquals(ret, "Dois Pares! Melhor que nada...");
    }

    @org.junit.jupiter.api.Test
    void adicionarTrinca() {
        mao.puxar();
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);
        mao.setCartas(2, 1, 3);
        mao.setCartas(3, 5, 3);
        mao.setCartas(4, 6, 3);


        int ret = p.adicionar(1, mao);
        assertEquals(ret, 2);
    }

    @org.junit.jupiter.api.Test
    void getResultTrinca() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);
        mao.setCartas(2, 1, 3);
        mao.setCartas(3, 5, 3);
        mao.setCartas(4, 6, 3);

        String ret = p.getResult(mao);
        assertEquals(ret, "Trincaa!");
    }


    @org.junit.jupiter.api.Test
    void adicionarQuadra() {
        mao.puxar();
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);
        mao.setCartas(2, 1, 3);
        mao.setCartas(3, 1, 0);
        mao.setCartas(4, 6, 3);


        int ret = p.adicionar(1, mao);
        assertEquals(ret, 50);
    }

    @org.junit.jupiter.api.Test
    void getResultQuadra() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);
        mao.setCartas(2, 1, 3);
        mao.setCartas(3, 1, 0);
        mao.setCartas(4, 6, 3);

        String ret = p.getResult(mao);
        assertEquals(ret, "Quadra! Boa!");
    }

    @org.junit.jupiter.api.Test
    void adicionarFullHouse() {
        mao.puxar();
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);
        mao.setCartas(2, 1, 3);
        mao.setCartas(3, 2, 0);
        mao.setCartas(4, 2, 3);


        int ret = p.adicionar(1, mao);
        assertEquals(ret, 20);
    }

    @org.junit.jupiter.api.Test
    void getResultFullHouse() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 1, 2);
        mao.setCartas(2, 1, 3);
        mao.setCartas(3, 2, 0);
        mao.setCartas(4, 2, 3);

        String ret = p.getResult(mao);
        assertEquals(ret, "Woow, Full House!");
    }

    @org.junit.jupiter.api.Test
    void adicionarStraight() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 2, 2);
        mao.setCartas(2, 3, 3);
        mao.setCartas(3, 4, 0);
        mao.setCartas(4, 5, 3);


        int ret = p.adicionar(1, mao);
        assertEquals(5, ret);
    }

    @org.junit.jupiter.api.Test
    void getResultStraight() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 2, 2);
        mao.setCartas(2, 3, 3);
        mao.setCartas(3, 4, 0);
        mao.setCartas(4, 5, 3);

        String ret = p.getResult(mao);
        assertEquals(ret, "Straight, aí sim!");
    }

    @org.junit.jupiter.api.Test
    void adicionarFlush() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 2, 1);
        mao.setCartas(2, 8, 1);
        mao.setCartas(3, 4, 1);
        mao.setCartas(4, 5, 1);


        int ret = p.adicionar(1, mao);
        assertEquals(10, ret);
    }

    @org.junit.jupiter.api.Test
    void getResultFlush() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 2, 1);
        mao.setCartas(2, 8, 1);
        mao.setCartas(3, 4, 1);
        mao.setCartas(4, 5, 1);

        String ret = p.getResult(mao);
        assertEquals(ret, "Oloco, Flush!");
    }

    @org.junit.jupiter.api.Test
    void adicionarStraightFlush() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 2, 1);
        mao.setCartas(2, 3, 1);
        mao.setCartas(3, 4, 1);
        mao.setCartas(4, 5, 1);


        int ret = p.adicionar(1, mao);
        assertEquals(100, ret);
    }

    @org.junit.jupiter.api.Test
    void getResultStraightFlush() {
        mao.setCartas(0, 1, 1);
        mao.setCartas(1, 2, 1);
        mao.setCartas(2, 3, 1);
        mao.setCartas(3, 4, 1);
        mao.setCartas(4, 5, 1);

        String ret = p.getResult(mao);
        assertEquals(ret, "Straight Flush! yaay");
    }

    @org.junit.jupiter.api.Test
    void adicionarRoyalStraightFlush() {
        mao.setCartas(0, 10, 1);
        mao.setCartas(1, 11, 1);
        mao.setCartas(2, 12, 1);
        mao.setCartas(3, 13, 1);
        mao.setCartas(4, 14, 1);


        int ret = p.adicionar(1, mao);
        assertEquals(200, ret);
    }

    @org.junit.jupiter.api.Test
    void getResultRoyalStraightFlush() {
        mao.setCartas(0, 10, 1);
        mao.setCartas(1, 11, 1);
        mao.setCartas(2, 12, 1);
        mao.setCartas(3, 13, 1);
        mao.setCartas(4, 14, 1);

        String ret = p.getResult(mao);
        assertEquals(ret, "Royal Straight Flush! Top demaaais!");
    }

    @org.junit.jupiter.api.Test
    void puxarString() {
        Carta[] cartas = new Carta[5];
        cartas = mao.getCartas();
        mao.puxar("2 3");

        boolean ret = cartas[0].getValue().equals(mao.getCartas(0).getValue());
        ret &= cartas[0].getNaipe() ==  mao.getCartas(0).getNaipe();
        assertTrue(ret);
    }

    @org.junit.jupiter.api.Test
    void puxarBoolean() {
        Carta carta = new Carta();
        carta = mao.getCartas(3);


        boolean[] change = new boolean[5];
        change[3] = true;
        mao.puxar(change);

        assertNotEquals(carta, mao.getCartas(3));
    }

    @org.junit.jupiter.api.Test
    void puxarInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mao.puxar("12");
        });
    }

    @org.junit.jupiter.api.Test
    void baralhoEmpty() {
        for(int i = 0; i < 47; ++i)
            d.pegarCarta();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            d.pegarCarta();
        });
    }


}