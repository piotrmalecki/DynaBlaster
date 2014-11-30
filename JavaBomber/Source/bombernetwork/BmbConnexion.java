package bombernetwork ;

import java.util.* ;
import java.net.* ;
import java.io.* ;

/**
 * <b>BmbConnexion</b> �tablie un lien entre un client et un serveur. C'est par
 * cette classe que transite tous les messages provenant d'un client.
 * @see java.lang.Runnable
 */
public class BmbConnexion extends Observable implements Runnable
{
  private BmbServeur serveur ;    // observateur associ� � la connexion
  private Socket client ;         // socket du client
  private BufferedReader entree ; // r�ception de requ�te
  private PrintWriter sortie ;    // envoi des r�ponses
  private Thread processus ;      // processus de la connexion
  private boolean connecte ;     // indique si le processus s'ex�cute

  /**
   * Constructeur de <b>BmbConnexion</b>.
   * 
   * @param serveur serveur g�rant la connexion
   * @param observateur observateur devant �tre notifi� lorsqu'un message est re�u
   * @param client client associ� � la connexion
   * @throws IOException si la connexion ne peut �tre initialis�e
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
    
    // initialisation des buffers d'entr�e / sortie
    this.entree = new BufferedReader (new InputStreamReader (this.client.getInputStream ())) ;
    this.sortie = new PrintWriter (new OutputStreamWriter (this.client.getOutputStream ()), true) ;
    
    // initialisation du processus
    this.processus = new Thread (this) ;
    this.processus.start () ;
  } /* BmbConnexion () */

  /**
   * D�marre le thread et g�re la connexion. Les messages re�us sont transmis
   * au serveur.
   * 
   * @see java.lang.Runnable
   */
  public void run ()
  {
    String reponse ; // messages envoy�s par le client
    
    // attend l'arriv�e de messages
    connecte = true ;
    while (connecte)
    {
      try
      {
        // lit le message
        reponse = this.entree.readLine () ;
        if (reponse != null)
        {
          // transmet le message � l'observateur
          this.setChanged () ;
          this.notifyObservers (new BmbMessageServeur (this, reponse)) ;
        }
        else
        {
          // si la m�thode stop n'a pas �t� appel�e au pr�alable
          if (connecte)
          {
            // ferme la connexion
            this.stop () ;
            this.processus = null ;
            
            // indique au socket la suppression de la connexion
            this.serveur.supprimerConnexion (this) ;
            
            // indique � l'observateur la fermeture de la connexion
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
   * @param message message � envoyer
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
    // arr�te l'execution du processus
    connecte = false ;
    
    try
    {
      // ferme les buffers d'entr�e / sortie
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
   * Retourne le num�ro de port de la connexion.
   */
  public int getPort ()
  {
    return this.client.getPort () ;
  } /* getPort () */
} /* BmbConnexion */
