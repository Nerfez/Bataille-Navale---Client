package bataillenavale_client;

/**
 *
 * @author Zefren
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_Socket extends Thread {

    // Liens de terminaisons d'assocations
    private Socket s;
    private Jeu lancement_jeu;

    /**
     * Lecture en continue d'un message provenant du serveur concernant la
     * cible, si elle est touchée on reçoit "green" autrement "red", on le fait
     * savoir à notre client
     */
    @Override
    public void run() {
        while (true) {
            String lec = Lecture();
            if (lec.equals("green")) {
                lancement_jeu.setCouleur(lec);
            }
            if (lec.equals("red")) {
                lancement_jeu.setCouleur(lec);
            }
            if (lec.startsWith("bateau")) {
                String reponse = "";
                if (lec.equals("bateau:3a")) {
                    reponse = "3 - a ";
                } else if (lec.equals("bateau:3b")) {
                    reponse = "3 - b ";
                } else {
                    reponse = lec.substring(7, lec.length());
                }
                lancement_jeu.setBateauxTués(reponse);
            }
        }
    }

    /**
     * pour se connecter
     *
     * @param IPServeur
     * @param portServeur
     * @return
     */
    public boolean seConnecter(String IPServeur, int portServeur) {
        try {
            s = new Socket(IPServeur, portServeur);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Client_Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Pour fermer la connexion
     */
    public void fermerConnexion() {
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Pour lire un message reçu du serveur
     *
     * @return
     */
    public String Lecture() {
        try {
            InputStreamReader ips = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(ips);
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client_Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "echec de lecture";
    }

    /**
     * Pour ecrire un message au serveur
     *
     * @param envoi
     */
    public void Ecriture(String envoi) {
        try {
            PrintStream pr = new PrintStream(s.getOutputStream());
            pr.println(envoi);
        } catch (IOException ex) {
            Logger.getLogger(Client_Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // référence de notre lien
    public void setLancement_Jeu(Jeu lancement_jeu) {
        this.lancement_jeu = lancement_jeu;
    }

}
