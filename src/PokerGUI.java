import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PokerGUI extends JFrame {

    private boolean[] status = new boolean[6];
    private int betValue = 0;
    private int creditValue = 200;
    private JButton restart;
    private JButton bet;
    private JButton changeCards;
    private JButton finish;
    private JButton[] cardButton;
    private JLabel infoStr;
    private JLabel amountStr;
    private JLabel betStr;
    private JTextField getBet;
    private Container mainFrame;
    private Container infoPanel;
    private Container cardsPanel;
    private Container betting;
    private Container playing;
    private Mao mao;
    private Deck deck;
    private Placar placar;
    private boolean changed;

    /**
     * Cria um ícone
     * @param num numero da carta
     * @param naipe naipe da carta
     * @return ícone da carta com numero e naipes especificados
     */
    private Icon getIcon(String num, Character naipe) {
        String file = "./cards/PNG/" + num + naipe + ".png";

        ImageIcon icon = new ImageIcon();
        Image img;

        try {
            icon = new ImageIcon(file);
        } catch (Exception e) {
            icon = new ImageIcon("./cards/PNG/00.png");
        } finally {
            img = icon.getImage().getScaledInstance(120, 180, Image.SCALE_DEFAULT);
        }

        return new ImageIcon(img);
    }

    /**
     * Reinicia status das cartas
     */
    private void resetStatus() {
        status = new boolean[5];
    }

    /**
     * Mostra cartas
     */
    private void displayCards() {
        for(int i = 0; i < 5; i++) {
            cardButton[i].setIcon(getIcon(mao.getCartas(i).getValue(), mao.getCartas(i).getNaipe()));
            cardButton[i].setBorder(BorderFactory.createEmptyBorder());
            cardsPanel.add(cardButton[i]);
        }
    }

    /**
     * Atualiza créditos
     */
    private void updateCredit() {
        creditValue += placar.adicionar(betValue, mao);
        betValue = 0;

        if(creditValue <= 0)
            noCredit();
    }

    /**
     * Mostra resultado
     */
    private void showResult() {
        infoStr.setText(placar.getResult(mao));
        infoStr.setVisible(true);
    }

    /**
     * Desabilita botões das cartas
     */
    void disableCards() {
        for(JButton b : cardButton) {
            b.setEnabled(false);
        }
    }

    /**
     * Habilita botões das cartas
     */
    void enableCards() {
        for(JButton b : cardButton) {
            b.setEnabled(true);
        }
    }

    /**
     * Prepara jogo para reiniciar - desabilita botões
     */
    private void prepareRestart() {
        playing.setVisible(true);

        disableCards();
        getBet.setEnabled(false);
        finish.setEnabled(false);
        changeCards.setEnabled(false);
        bet.setEnabled(false);
        restart.setVisible(true);
    }

    /**
     * Realiza ações quando os créditos acabam
     */
    private void noCredit() {
        infoStr.setText("Acabaram seus créditos =(");
        infoStr.setVisible(true);

        prepareRestart();
    }

    /**
     * Altera estado do jogo para Jogando
     */
    private void changeToPlaying() {
        enableCards();
        changed = false;
        displayCards();
        resetStatus();

        betting.setVisible(false);
        playing.setVisible(true);
    }

    /**
     * Altera estado do jogo para Apostando
     */
    private void changeToBetting() {
        playing.setVisible(false);

        updateCredit();

        amountStr.setText("Saldo: " + creditValue);
        betStr.setText("Aposta: " + betValue);
        getBet.setText("Digite a aposta: ");

        disableCards();
        betting.setVisible(true);
    }

    /**
     * Ações tomadas ao selecionar uma carta
     * @param id id da carta selecionada
     */
    private void cardButtonClicked(int id) {
        status[id] = !status[id];

        if(status[id]) cardButton[id].setIcon(getIcon("0", '0'));
        else cardButton[id].setIcon(getIcon(mao.getCartas(id).getValue(), mao.getCartas(id).getNaipe()));
    }

    /**
     * Ações tomadas ao selecionar opção de apostar
     */
    private void betButtonClicked() {
        infoStr.setVisible(false);
        mao = new Mao(5, deck);
        try{
            betValue = Integer.parseInt(getBet.getText());

            if(betValue > creditValue) {
                infoStr.setText("Você não pode apostar mais do que tem!");
                infoStr.setVisible(true);
                return;
            }

        } catch (Exception e) {
            infoStr.setText("Digite um valor válido!");
            infoStr.setVisible(true);

            getBet.setText("Digite a aposta: ");
            betting.add(getBet);
            return;
        }

        infoStr.setVisible(false);
        betStr.setText("Aposta: " + betValue);

        creditValue -= betValue;
        amountStr.setText("Saldo: " + creditValue);

        changeToPlaying();
    }

    /**
     * Ações tomadas ao mudar as cartas
     */
    private void changeCardsButtonClicked() {
        mao.puxar(status);

        displayCards();
        resetStatus();

        if(changed) {
            showResult();
            changeToBetting();
        } else {
            changed = true;
        }
    }

    /**
     * Ações tomadas ao finalizar turno
     */
    private void finishButtonClicked(){
        showResult();
        displayCards();
        resetStatus();
        changeToBetting();
    }

    /**
     * Reinicia jogo
     */
    private void restartButtonClicked() {
        new PokerGUI();
    }

    /**
     * Inicializa botão de reiniciar
     */
    private void setRestartButton() {
        restart = new JButton("Reiniciar");
        playing.add(restart);

        restart.addActionListener((ActionEvent e) ->
            restartButtonClicked()
        );

        restart.setVisible(false);
    }

    /**
     * Inicializa botão de finalizar
     */
    private void setFinishButton() {
        finish = new JButton("Finalizar");
        playing.add(finish);

        finish.addActionListener((ActionEvent e) ->
            finishButtonClicked()
        );
    }

    /**
     * Inicializa botão de mudar cartas
     */
    private void setChangeCardButton() {
        changeCards = new JButton("Trocar cartas");

        changeCards.addActionListener((ActionEvent e) ->
            changeCardsButtonClicked()
        );

        playing.add(changeCards);
    }

    /**
     * Inicializa último painel da interface - botões de trocar, finalizar e reiniciar
     */
    private void setPlayingPanel() {
        playing = new JPanel();
        playing.setLayout(new FlowLayout());

        setChangeCardButton();
        setFinishButton();
        setRestartButton();

        mainFrame.add(playing);
    }

    /**
     * Inicializa terceiro painel da interface - botão de apostar e textField
     */
    private void setBettingPanel() {
        betting = new JPanel();
        betting.setLayout(new FlowLayout());

        bet = new JButton("Apostar");
        betting.add(bet);

        bet.addActionListener((ActionEvent e)->
                betButtonClicked()
        );

        getBet = new JTextField("Digite a aposta: ");
        getBet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getBet.setText("");
            }
        });

        betting.add(getBet);

        mainFrame.add(betting);
    }

    /**
     * Configura um botão/carta
     * @param id id da carta a configurar
     */
    private void setCardButton(int id) {
        cardButton[id] = new JButton();
        cardButton[id].setIcon(getIcon("0", '0'));
        cardButton[id].setBorder(BorderFactory.createEmptyBorder());

        cardButton[id].addActionListener((ActionEvent e)->
            cardButtonClicked(id)
        );

        cardsPanel.add(cardButton[id]);
    }

    /**
     * Inicializa segundo painel da interface - painel de cartas
     */
    private void setCardsPanel() {
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout());

        cardButton = new JButton[5];
        for(int i = 0; i < 5; i++) {
            setCardButton(i);
        }

        mainFrame.add(cardsPanel);
    }

    /**
     * Inicializa painel superior da interface
     */
    private void setInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        amountStr = new JLabel("Saldo: " + creditValue);
        infoPanel.add(BorderLayout.WEST, amountStr);

        betStr = new JLabel("Aposta: " + betValue);
        infoPanel.add(BorderLayout.EAST, betStr);

        infoStr = new JLabel("");
        infoStr.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(BorderLayout.CENTER, infoStr);
        infoStr.setVisible(false);

        mainFrame.add(infoPanel);
    }

    /**
     * Inicializa frame principal da interface
     */
    private void setMainFrame() {
        mainFrame = getContentPane();
        mainFrame.setLayout(new GridLayout(4, 1));
        mainFrame.setVisible(true);
    }

    /**
     * "Liga" tela - setta tamanho e como visível
     */
    private void turnOn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 850);
        setVisible(true);
    }

    /**
     * Initializa variáveis importantes para a execução
     */
    private void setup() {
        placar = new Placar();
        deck = new Deck();
    }

    /**
     * Construtor da interface
     */
    public PokerGUI() {
        super("Super Mega Vídeo Poker");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // no problem
        }

        setup();

        setMainFrame();
        setInfoPanel();
        setCardsPanel();
        setBettingPanel();
        setPlayingPanel();

        changeToBetting();

        turnOn();
    }

    public static void main(String[] args) {
        new PokerGUI();
    }
}