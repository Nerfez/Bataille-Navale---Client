package bataillenavale_client;

/**
 *
 * @author Zefren
 */
public class Lanceur {

    /**
     * @param args the command line arguments
     * Initialisation de notre Socket Client
     * et des deux IHM que l'on va afficher.
     * Une pour se connecter, une deuxième
     * pour envoyer une requête au serveur
     */
    public static void main(String[] args) {
        IHM_ClientConnexion ihm = new IHM_ClientConnexion();
        IHM_Commandes ihm2 = new IHM_Commandes();
        Client_Socket client = new Client_Socket();
        ihm.setClient(client);
        ihm.setIHM_Commandes(ihm2);
        ihm.setVisible(true);
    }
    
}
