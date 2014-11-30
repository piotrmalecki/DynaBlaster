package bombernetwork ;

import java.util.* ;
import java.net.* ;
import java.io.* ;

/**
 * <b>BmbConnexion</b> établie un lien entre un client et un serveur. C'est par
 * cette classe que transite tous les messages provenant d'un client.
 * @see java.lang.Runnable
 */
public class BmbConnexion extends Observable implements Runnable
{
  private BmbServeur serveur ;    // observateur associé à la connexion
  private Socket client ;         // socket du client
  private BufferedReader entree ; // réception de requête
  private PrintWriter sortie ;    // envoi des réponses
  private Thread processus ;      // processus de la connexion
  private boolean connecte ;     // indique si le processus s'exécute

  /**
   * Constructeur de <b>BmbConnexion</b>.
   * 
   * @param serveur serveur gérant la connexion
   * @param observateur observateur devant être notifié lorsqu'un message est reçu
   * @param client client associé à la connexion
   * @throws IOException si la connexion ne peut être initialisée
   * @see bombernetwork.BmbServeur
   * @see java.net.Socket
   */
  public BmbConnexion (BmbServeur serveur, Observer observateur, Socket client) throws IOException
  {
  	super () ;
  	
    // initialisation du serveur
    this.serveur = serveur ;
    
    // initialisation de l'observateur
    this.addObserver (observateur) ;
    
    // initialisation du socket
    this.client = client ;
    
    // initialisation des buffers d'entrée / sortie
    this.entree = new BufferedReader (new InputStreamReader (this.client.getInputStream ())) ;
    this.sortie = new PrintWriter (new OutputStreamWriter (this.client.getOutputStream ()), true) ;
    
    // initialisation du processus
    this.processus = new Thread (this) ;
    this.processus.start () ;
  } /* BmbConnexion () */

  /**
   * Démarre le thread et gère la connexion. Les messages reçus sont transmis
   * au serveur.
   * 
   * @see java.lang.Runnable
   */
  public void run ()
  {
    String reponse ; // messages envoyés par le client
    
    // attend l'arrivée de messages
    connecte = true ;
    while (connecte)
    {
      try
      {
        // lit le message
        reponse = this.entree.readLine () ;
        if (reponse != null)
        {
          // transmet le message à l'observateur
          this.setChanged () ;
          this.notifyObservers (new BmbMessageServeur (this, reponse)) ;
        }
        else
        {
          // si la méthode stop n'a pas été appelée au préalable
          if (connecte)
          {
            // ferme la connexion
            this.stop () ;
            this.processus = null ;
            
            // indique au socket la suppression de la connexion
            this.serveur.supprimerConnexion (this) ;
            
            // indique à l'observateur la fermeture de la connexion
            this.setChanged () ;
            this.notifyObservers (new BmbMessageServeur (this, BmbMessage.MSG_FERMER, "")) ;
          }
        }
      }
      catch (Exception exception)
      {
      }
    }
  } /* run () */

  /**
   * Envoie un message au client
   * 
   * @param message message à envoyer
   */
  public void envoyer (BmbMessage message)
  {
    // envoie le message au client
    this.sortie.println (message.getMessage ()) ;
  } /* envoyer () */

  /**
   * Ferme la connexion.
   */
  public void stop ()
  {
    // arrête l'execution du processus
    connecte = false ;
    
    try
    {
      // ferme les buffers d'entrée / sortie
      this.client.shutdownInput () ;
      this.client.shutdownOutput () ;
      
      // ferme le socket
      this.client.close () ;
    }
    catch (Exception exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
  } /* stop () */

  /**
   * Retourne le numéro de port de la connexion.
   */
  public int getPort ()
  {
    return this.client.getPort () ;
  } /* getPort () */
} /* BmbConnexion */
