package bombernetwork ;

import java.util.* ;
import java.net.* ;
import java.io.* ;

/**
 * <b>BmbClient</b> est un serveur de données. Il gère les connexions,
 * transmet et reçoit les messages du serveur.
 * @see java.util.Observable
 * @see java.lang.Runnable
 */
public class BmbClient extends Observable implements Runnable
{
  private int port ;             // port du client
  private String adresse ;        // adresse du client
  private Socket serveur ;        // socket du serveur
  private BufferedReader entree ; // réception de requête
  private PrintWriter sortie ;    // envoi des réponses
  private Thread processus ;      // processus de la connexion
  private boolean connecte ;     // indique si le processus s'exécute
  
  /**
   * Constructeur de <b>BmbClient</b>. Par défaut, tout objet de type
   * <b>BmbClient</b> est dans l'état démarré.
   * 
   * @param observateur classe devant être notifié de l'arrivé de messages
   * @param port numéro de port du serveur
   * @param adresse adresse du serveur
   * @throws IOException en cas d'impossibilité d'initialiser le client
   * @see java.util.Observer
   */
  public BmbClient (Observer observateur, int port, String adresse) throws IOException
  {
  	super () ;
  	
    // initialisations des variables membres
    this.port = port ;
    this.adresse = adresse ;
    
    // initialisation du client
    this.serveur = new Socket (adresse, port) ;
    
    // initialisation des buffers d'entrée / sortie
    this.entree = new BufferedReader (new InputStreamReader (this.serveur.getInputStream ())) ;
    this.sortie = new PrintWriter (new OutputStreamWriter (this.serveur.getOutputStream ()), true) ;
    
    // ajout de l'observateur
    this.addObserver (observateur) ;

    // initialisation du processus
    this.processus = new Thread (this) ;
    this.processus.start () ;
  } /* BmbClient () */

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
    this.connecte = true ;
    while (this.connecte)
    {
      try
      {
        // lit le message
        reponse = this.entree.readLine () ;
        if (reponse != null)
        {
          // transmet le message à l'observateur
          this.setChanged () ;
          this.notifyObservers (new BmbMessage (reponse)) ;
        }
        else
        {
          // si la méthode stop n'a pas été appelée au préalable
          if (this.connecte)
          {
            // ferme la connexion
            this.stop () ;
            this.processus = null ;
            
            // indique à l'observateur la fermeture de la connexion
            this.setChanged () ;
            this.notifyObservers (new BmbMessage (BmbMessage.MSG_FERMER, "")) ;
          }
        }
      }
      catch (Exception exception)
      {
      }
    }
  } /* run () */

  /**
   * Ferme la connexion.
   */
  public void stop ()
  {
    // arrête l'execution du processus
    this.connecte = false ;
    
    try
    {
      // ferme les buffers d'entrée / sortie
      this.serveur.shutdownInput () ;
      this.serveur.shutdownOutput () ;
      
      // ferme le socket
      this.serveur.close () ;
    }
    catch (Exception exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
  } /* stop () */

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
   * Retourne le numéro de port du serveur.
   */
  public int getPort ()
  {
    return this.port ;
  } /* getPort () */

  /**
   * Retourne l'adresse du serveur.
   */
  public String getAdresse ()
  {
    return this.adresse ;
  } /* getPort () */

  /**
   * Indique si la connexion est arrêté.
   */
  public boolean estArrete ()
  {
    return this.processus == null ;
  } /* getPort () */
} /* BmbClient */
