package bombernetwork ;

import java.util.* ;
import java.net.* ;
import java.io.* ;

/**
 * <b>BmbServeur</b> est un serveur de donn�es. Il g�re les connexions,
 * transmet et re�oit les messages des clients.
 * @see java.lang.Runnable
 * @see java.util.Observable
 * @see java.util.Observer
 * @see bombernetwork.BmbConnexion
 */
public class BmbServeur extends Observable implements Runnable
{
  private int port ;             // port du serveur
  private int maxConnexions ;    // nombre maximum de connexions autoris�es
  private Vector connexions ;    // connexions re�ues
  private ServerSocket serveur ; // socket du serveur
  private Observer observateur ; // observateur
  private Thread processus ;     // processus du serveur
  private boolean execution ;   // indique si le processus s'�xecute
  
  /**
   * Constructeur de <b>BmbServeur</b>. Par d�faut, tout objet de type
   * <b>BmbServeur</b> est dans l'�tat d�marr�.
   * 
   * @param observateur classe devant �tre notifi� de l'arriv� de messages
   * @param port num�ro de port sur lequel doit �couter le serveur
   * @param maxConnexions nombre maximum de connexions devant �tre g�r�es
   * @throws IOException en cas d'impossibilit� d'initialiser le serveur
   * @see java.util.Observer
   */
  public BmbServeur (Observer observateur, int port, int maxConnexions) throws IOException
  {
  	super () ;
  	
    // initialisations des variables membres
    this.port = port ;
    this.maxConnexions = maxConnexions ;
    
    // initialisation du tableau de connexion
    this.connexions = new Vector () ;

    // initialisation de l'observateur
    this.addObserver (observateur) ;
    this.observateur = observateur ;

    // initialisation du serveur
    this.serveur = new ServerSocket (port) ;
    this.serveur.setSoTimeout (1000) ;
    
    // cr�ation du processus
    this.processus = new Thread (this) ;
    this.processus.start () ;
  } /* BmbServeur () */

  /**
   * Ex�cute le serveur et attend les connexions de clients. A chaque connexion
   * d'un nouveau client, un objet de type BmbConnexion est ajout� � la liste
   * des connexions. Cette m�thode ne doit pas �tre appel�e explicitement. Elle
   * est appel�e automatiquement dans le constructeur.
   * 
   * @see java.lang.Runnable
   */
  public void run ()
  {
    // ex�cution du processus en boucle
    this.execution = true ;
    
    // attente de nouvelles connexions
    while (this.execution)
    {
      // attendre la connexion d'un nouveau client
      try
      {
        // attend une connexion
        Socket client = this.serveur.accept () ;
        // si on n'a pas atteint le nombre maximum de connexions
        if (this.connexions.size () < this.maxConnexions)
        {
          try
          {
            // ajoute la connexion � la liste
            BmbConnexion connexion = new BmbConnexion (this, this.observateur, client) ;
            this.connexions.addElement (connexion) ;
            // indique � l'observateur l'ouverture de la connexion
            this.setChanged () ;
            this.notifyObservers (new BmbMessageServeur (connexion, BmbMessage.MSG_ACCEPTER, "")) ;
          }
          catch (Exception exception)
          {
            System.out.println (exception.toString ()) ;
            exception.printStackTrace () ;
          }
        }
        else
        {
          client.close () ;
        }
      }
      catch (IOException exception)
      {
      }
    }
  } /* run () */
  
  /**
   * lib�re le serveur et le port utilis�. Doit �tre imp�rativement appel�
   * avant que l'objet ne soit d�truit pour d�bloquer le port.
   */
  public void stop ()
  {
    // arr�te l'execution du processus
    this.execution = false ;
    
    // ferme les connections
    for (int i = this.connexions.size () - 1; i >= 0; i --)
    {
      // supprime et stoppe la connexion de la liste
      BmbConnexion connexion = ((BmbConnexion) this.connexions.elementAt (i)) ;
      this.connexions.removeElementAt (i) ;
      connexion.stop () ;
    }
    
    // ferme le serveur
    try
    {
      this.serveur.close () ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
  } /* stop () */

  /**
   * Supprime la connexion pass� en param�tre de la liste.
   * 
   * @param connexion connexion � supprimer
   * @see bombernetwork.BmbConnexion
   */
  public void supprimerConnexion (BmbConnexion connexion)
  {
    int i = 0 ; // indice de parcours des connexions
    boolean trouve = false ; // indique si la connexion a �t� trouv�e
    
    // chercher l'indice de la connexion ayant envoyer le message
    while ((i < this.connexions.size ()) && (! trouve))
    {
      // si la connexion est trouv�e
      if (((BmbConnexion) connexions.elementAt (i)) == connexion)
      {
        // supprime l'�l�ment de la liste
        this.connexions.removeElementAt (i) ;
        trouve = true ;
      }
      // passer � l'�l�ment suivant
      i ++ ;
    }
  } /* supprimerConnexion () */

  /**
   * Retourne la connexion d'indice sp�cifi�.
   * 
   * @param indice indice de la connexion
   * @throws ArrayIndexOutOfBoundsException si l'indice est incorrect
   * @see bombernetwork.BmbConnexion
   */
  public BmbConnexion getConnexion (int indice)
  {
    return (BmbConnexion) this.connexions.elementAt (indice) ;
  } /* getConnexion () */

  /**
   * Retourne le nombre de connexions g�r�es par le serveur.
   *
   * @see bombernetwork.BmbConnexion
   */
  public int getNombreConnexions ()
  {
    return this.connexions.size () ;
  } /* getConnexion () */

  /**
   * Retourne le num�ro de port.
   */
  public int getPort ()
  {
    return this.port ;
  } /* getPort () */
} /* BmbServeur */
