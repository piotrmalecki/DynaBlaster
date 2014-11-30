package bombernetwork ;

import java.util.* ;

/**
 * <b>BmbMessage</b> encapsule les messages envoy�s entre client et serveur. Un
 * message est compos� de plusieurs champs s�par�s par la sentinelle.
 */
public class BmbMessage
{
  /**
   * Indique qu'une connexion vient d'�tre accepter.
   */
  public static final int MSG_ACCEPTER = 1 ;
  /**
   * Indique que la connexion vient de se fermer.
   */
  public static final int MSG_FERMER = 2 ;

  /**
   * Caract�re s�parant les diff�rents champs d'un messages.
   */
  public static char SENTINELLE = '#' ; // caract�re de s�paration
  
  private int action ;       // param�tres du message
  private Vector arguments ; // param�tres du message
  
  /**
   * Choisit le caract�re s�parant les arguments du messages.
   * 
   * @param sentinelle caract�re de s�paration des arguments
   */
  public static void setSentinelle (char sentinelle)
  {
    BmbMessage.SENTINELLE = sentinelle ;
  } /* setSentinelle () */

  /**
   * Constructeur de <b>BmbMessage</b>.
   * 
   * @param message cha�ne repr�sentant le message
   */
  public BmbMessage (String message)
  {
  	super () ;
  	
    // initialisation du tableau d'arguments
    this.arguments = new Vector () ;
    
    // initialise indice � la premi�re occurence de la sentinelle
    int indiceDebut = 0 ;
    int indiceFin = message.indexOf (BmbMessage.SENTINELLE) ;
    // parcours chacun des arguments
    while (indiceFin != -1)
    {
      // ajoute l'argument � la liste
      this.arguments.addElement (message.substring (indiceDebut, indiceFin)) ;
      // passer � l'�l�ment suivant
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
   * @param message cha�ne repr�sentant le message
   */
  public BmbMessage (int action, String message)
  {
    super () ;
    
    // initialisation du tableau d'arguments
    this.action = action ;
    this.arguments = new Vector () ;
    
    // initialise indice � la premi�re occurence de la sentinelle
    int indiceDebut = 0 ;
    int indiceFin = message.indexOf (BmbMessage.SENTINELLE) ;
    // parcours chacun des arguments
    while (indiceFin != -1)
    {
      // ajoute l'argument � la liste
      this.arguments.addElement (message.substring (indiceDebut, indiceFin)) ;
      // passer � l'�l�ment suivant
      indiceDebut = indiceFin + 1 ;
      indiceFin = message.indexOf (BmbMessage.SENTINELLE, indiceFin + 1) ;
    }
    // ajouter le dernier argument
    this.arguments.addElement (message.substring (indiceDebut, message.length ())) ;
  } /* BmbMessage () */

  /**
   * Retourne le message sous forme de cha�ne unique.
   * 
   * @return message sous forme de cha�ne
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
   * Retourne l'argument � l'indice sp�cifi�.
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
   * Retourne l'argument � l'indice indiceMot.
   * 
   * @param indiceMot indice du mot
   * @return action du message
   */
  public int getAction ()
  {
    return this.action ;
  } /* getAction () */
} /* BmbMessage */
