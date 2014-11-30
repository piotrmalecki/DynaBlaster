package bombernetwork ;

import java.util.* ;
import java.net.* ;
import java.io.* ;

/**
 * <b>BmbClient</b> est un serveur de donn�es. Il g�re les connexions,
 * transmet et re�oit les messages du serveur.
 * @see java.util.Observable
 * @see java.lang.Runnable
 */
public class BmbClient extends Observable implements Runnable
{
  private int port ;             // port du client
  private String adresse ;        // adresse du client
  private Socket serveur ;        // socket du serveur
  private BufferedReader entree ; // r�ception de requ�te
  private PrintWriter sortie ;    // envoi des r�ponses
  private Thread processus ;      // processus de la connexion
  private boolean connecte ;     // indique si le processus s'ex�cute
  
  /**
   * Constructeur de <b>BmbClient</b>. Par d�faut, tout objet de type
   * <b>BmbClient</b> est dans l'�tat d�marr�.
   * 
   * @param observateur classe devant �tre notifi� de l'arriv� de messages
   * @param port num�ro de port du serveur
   * @param adresse adresse du serveur
   * @throws IOException en cas d'impossibilit� d'initialiser le client
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
    
    // initialisation des buffers d'entr�e / sortie
    this.entree = new BufferedReader (new InputStreamReader (this.serveur.getInputStream ())) ;
    this.sortie = new PrintWriter (new OutputStreamWriter (this.serveur.getOutputStream ()), true) ;
    
    // ajout de l'observateur
    this.addObserver (observateur) ;

    // initialisation du processus
    this.processus = new Thread (this) ;
    this.processus.start () ;
  } /* BmbClient () */

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
    this.connecte = true ;
    while (this.connecte)
    {
      try
      {
        // lit le message
        reponse = this.entree.readLine () ;
        if (reponse != null)
        {
          // transmet le message � l'observateur
          this.setChanged () ;
          this.notifyObservers (new BmbMessage (reponse)) ;
        }
        else
        {
          // si la m�thode stop n'a pas �t� appel�e au pr�alable
          if (this.connecte)
          {
            // ferme la connexion
            this.stop () ;
            this.processus = null ;
            
            // indique � l'observateur la fermeture de la connexion
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
    // arr�te l'execution du processus
    this.connecte = false ;
    
    try
    {
      // ferme les buffers d'entr�e / sortie
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
   * @param message message � envoyer
   */
  public void envoyer (BmbMessage message)
  {
    // envoie le message au client
    this.sortie.println (message.getMessage ()) ;
  } /* envoyer () */

  /**
   * Retourne le num�ro de port du serveur.
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
   * Indique si la connexion est arr�t�.
   */
  public boolean estArrete ()
  {
    return this.processus == null ;
  } /* getPort () */
} /* BmbClient */
