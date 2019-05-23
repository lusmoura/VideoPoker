import java.util.Calendar;

/**
 * Gera numeros aleatorios
 */
public class Random {
    private long p = 2147483648L;
    private long m = 843314861;
    private long a = 453816693;
    public long semente;

    /**
     * Gera uma semente aleatoria
     */
    public Random(){
        semente = Calendar.getInstance().getTimeInMillis();
        try{
            Thread.sleep(117);
        } catch (InterruptedException e){ }
        semente %= p;
    }

    /**
     * Setter da semente
     * @param semente - valor da semente
     */
    public void setSeed(int semente) {
        this.semente = semente;
        return;
    }

    /**
     * Gera numero entre 0 e 1
     * @return numero gerado
     */
    public double getRand() {
        semente = (a + m * semente) % p;
        double d = semente;
        return d/p;
    }

    /**
     * Gera numero inteiro aleatorio de 1 ate max
     * @param max - numero maximo
     * @return numero gerado
     */
    public int getIntRand(int max){
        double n = getRand() * max;
        return (int)n;
    }

    /**
     * Gera numero inteiro aleatorio num range (de min ate max)
     * @param min - numero minino
     * @param max - mumero maximo
     * @return numero gerado
     */
    public int getIntRand(int min, int max){
        // checa se parametros sao validoss
        if(max <= min) throw new IllegalArgumentException("Parâmetros inválidos");
        return min + getIntRand(max - min);
    }

    /**
     * Gera numero aleatorio de 0 ao valor maximo de um inteiro
     * @return numero gerado
     */
    public int getIntRand() {
        return getIntRand(Integer.MAX_VALUE);
    }

    /**
     * Gera string para representar o objeto (semente)
     * @return string gerada
     */
    @Override
    public String toString() {
        return semente + "";
    }

    /**
     * Main para testes
     * @param args
     */
    public static void main(String[] args) {
        Random x  = new Random();
        int a = x.getIntRand(1, 7);

        for(int i = 0; i < 10; i++){
            System.out.println(a);
            a = x.getIntRand(1, 7);
        }

        System.out.println(a);
    }
}