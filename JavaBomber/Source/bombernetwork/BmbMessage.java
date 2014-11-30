package bombernetwork ;

import java.util.* ;

/**
 * <b>BmbMessage</b> encapsule les messages envoyés entre client et serveur. Un
 * message est composé de plusieurs champs séparés par la sentinelle.
 */
public class BmbMessage
{
  /**
   * Indique qu'une connexion vient d'être accepter.
   */
  public static final int MSG_ACCEPTER = 1 ;
  /**
   * Indique que la connexion vient de se fermer.
   */
  public static final int MSG_FERMER = 2 ;

  /**
   * Caractère séparant les différents champs d'un messages.
   */
  public static char SENTINELLE = '#' ; // caractère de séparation
  
  private int action ;       // paramètres du message
  private Vector arguments ; // paramètres du message
  
  /**
   * Choisit le caractère séparant les arguments du messages.
   * 
   * @param sentinelle caractère de séparation des arguments
   */
  public static void setSentinelle (char sentinelle)
  {
    BmbMessage.SENTINELLE = sentinelle ;
  } /* setSentinelle () */

  /**
   * Constructeur de <b>BmbMessage</b>.
   * 
   * @param message chaîne représentant le message
   */
  public BmbMessage (String message)
  {
  	super () ;
  	
    // initialisation du tableau d'arguments
    this.arguments = new Vector () ;
    
    // initialise indice à la première occurence de la sentinelle
    int indiceDebut = 0 ;
    int indiceFin = message.indexOf (BmbMessage.SENTINELLE) ;
    // parcours chacun des arguments
    while (indiceFin != -1)
    {
      // ajoute l'argument à la liste
      this.arguments.addElement (message.substring (indiceDebut, indiceFin)) ;
      // passer à l'élément suivant
      indiceDebut = indiceFin + 1 ;
      indiceFin = message.indexOf (BmbMessage.SENTINELLE, indiceFin + 1) ;
    }
    // ajouter le dernier argument
    this.arguments.addElement (message.substring (indiceDebut, message.length ())) ;
    
    // initialise l'action et la supprime du tableau
    this.action = Integer.parseInt ((String) this.arguments.elementAt (0)) ;
    this.arguments.removeElementAt (0) ;
  } /* BmbMessage () */

  /**
   * Constructeur de <b>BmbMessage</b>.
   * 
   * @param action action du message
   * @param message chaîne représentant le message
   */
  public BmbMessage (int action, String message)
  {
    super () ;
    
    // initialisation du tableau d'arguments
    this.action = action ;
    this.arguments = new Vector () ;
    
    // initialise indice à la première occurence de la sentinelle
    int indiceDebut = 0 ;
    int indiceFin = message.indexOf (BmbMessage.SENTINELLE) ;
    // parcours chacun des arguments
    while (indiceFin != -1)
    {
      // ajoute l'argument à la liste
      this.arguments.addElement (message.substring (indiceDebut, indiceFin)) ;
      // passer à l'élément suivant
      indiceDebut = indiceFin + 1 ;
      indiceFin = message.indexOf (BmbMessage.SENTINELLE, indiceFin + 1) ;
    }
    // ajouter le dernier argument
    this.arguments.addElement (message.substring (indiceDebut, message.length ())) ;
  } /* BmbMessage () */

  /**
   * Retourne le message sous forme de chaîne unique.
   * 
   * @return message sous forme de chaîne
   */
  public String getMessage ()
  {
    String message = Integer.toString (action) ;
    
    for (int i = 0; i < arguments.size (); i ++)
    {
      message = message + BmbMessage.SENTINELLE + (String) arguments.elementAt (i) ;
    }
    
    return (message) ;
  } /* getMessage () */

  /**
   * Retourne le nombre d'arguments composants le message.
   * 
   * @return nombre d'arguments
   */
  public int getNombreArguments ()
  {
    return (arguments.size ()) ;
  } /* getNombreArgument () */

  /**
   * Retourne l'argument à l'indice spécifié.
   * 
   * @param indice indice de l'argument
   * @return argument du message
   * @throws ArrayIndexOutOfBoundsException si l'indice est incorrect
   */
  public String getArgument (int indice)
  {
    return ((String) arguments.elementAt (indice)) ;
  } /* getArgument () */

  /**
   * Retourne l'argument à l'indice indiceMot.
   * 
   * @param indiceMot indice du mot
   * @return action du message
   */
  public int getAction ()
  {
    return this.action ;
  } /* getAction () */
} /* BmbMessage */
