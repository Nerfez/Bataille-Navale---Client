package bataillenavale_client;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Zefren
 */
public class Jeu extends JFrame {

    // Lien de terminaison d'association
    private ControlJeu controljeu;
    private IHM_Commandes ihm_commande;

    // Attributs
    private Color tabColor[] = new Color[100];
    private Color rougeFonce = new Color(240, 0, 32);
    private Color vertFonce = new Color(34, 120, 15);

    private int tabBateaux[][] = new int[10][10];
    private int missileX, missileY;
    private int compteurCouleur = 0;

    // Constructeur
    Jeu() {
        this.add(controljeu = new ControlJeu());
        this.setTitle("Bataille Navale - Jeu | Client v1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    // Méthodes
    /**
     * On rentre dans un tableau de Couleur du vert si la cible est touchée, et
     * ainsi, du rouge si le contraire
     *
     * @param lec
     */
    public void setCouleur(String lec) {
        if (lec.equals("green")) {
            tabColor[compteurCouleur] = vertFonce;
            compteurCouleur++;
            ihm_commande.affichage("La cible a été touchée ! \n");
        }
        if (lec.equals("red")) {
            tabColor[compteurCouleur] = rougeFonce;
            compteurCouleur++;
            ihm_commande.affichage("La cible n'a été touchée ! \n");
        }
        controljeu.setCouleur(tabColor);
    }

    /**
     * On rentre les coordoonées où l'on souhaite tirer
     *
     * @param x
     * @param y
     */
    public void setMissile(int x, int y) {
        this.missileX = x;
        this.missileY = y;
        controljeu.setMissileEnvoi(missileX, missileY);
    }

    /**
     * On envoi qu'un bateau est coulé
     * @param reponse
     */
    public void setBateauxTués(String reponse) {
        ihm_commande.affichage("Le bateau numéro " + reponse + " a été coulé ! \n");
        controljeu.setBateauxTués();
    }

    // référence de notre lien
    public void setIHM_Commande(IHM_Commandes ihm_commande) {
        this.ihm_commande = ihm_commande;
    }

}
