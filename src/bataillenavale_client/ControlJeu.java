package bataillenavale_client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Zefren
 */
public class ControlJeu extends JPanel implements ActionListener {

    // attributs
    private Color tabColor[] = new Color[100];
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 60;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    private int tabMissileX[] = new int[100];
    private int tabMissileY[] = new int[100];
    private int compteurMissile = 0;
    int bodyParts = 6;
    int bateauxTués = 0;

    char direction = 'R';
    boolean running = false;
    static boolean gameOn = false;
    Timer timer;
    Random random;
    boolean text = true;

    // Constructeur
    ControlJeu() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        startGame();

    }

    // Méthodes
    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * On enregistre les missiles envoyés
     *
     * @param missileX
     * @param missileY
     */
    public void setMissileEnvoi(int missileX, int missileY) {
        this.tabMissileX[compteurMissile] = missileX * UNIT_SIZE;
        this.tabMissileY[compteurMissile] = missileY * UNIT_SIZE;
        compteurMissile++;
    }

    /**
     * Permet de dessiner les cercles et le plateau de jeu ( lignes / fond )
     *
     * @param g
     */
    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            for (int i = 0; i < compteurMissile; i++) {
                g.setColor(tabColor[i]);
                g.fillOval(tabMissileX[i], tabMissileY[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Coulés: " + bateauxTués, (SCREEN_WIDTH - metrics.stringWidth("Coulés: " + bateauxTués)) / 2, g.getFont().getSize());
        }
    }

    /**
     * On redessine en boucle
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (running) {
        }
        repaint();
    }

    /**
     * On récupère la couleur rouge ou verte suivant si la cible a été touchée
     *
     * @param tabColor
     */
    public void setCouleur(Color[] tabColor) {
        this.tabColor = tabColor;
    }

    public void setBateauxTués() {
        this.bateauxTués += 1;
    }

}
