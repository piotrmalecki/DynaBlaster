package bombernetwork ;

/**
 * <b>BmbMessageServeur</b> encapsule les messages envoy�s entre client et serveur. Un
 * message est compos� de plusieurs champs s�par�s par la sentinelle.
 */
public class BmbMessageServeur extends BmbMessage
{
  private BmbConnexion emetteur ; // param�tres du message
  
  /**
   * Constructeur de <b>BmbMessage</b>.
   * 
   * @param emetteur connexion ayant �mis le message
   * @param message cha�ne repr�sentant le message
   */
  public BmbMessageServeur (BmbConnexion emetteur, String message)
  {
    super (message) ;
    
    // initialisation du tableau d'arguments
    this.emetteur = emetteur ;
  } /* BmbMessage () */

  /**
   * Constructeur de <b>BmbMessage</b>.
   * 
   * @param emetteur connexion ayant �mis le message
   * @param action action du message
   * @param message cha�ne repr�sentant le message
   */
  public BmbMessageServeur (BmbConnexion emetteur, int action, String message)
  {
    super (action, message) ;
    
    // initialisation du tableau d'arguments
    this.emetteur = emetteur ;
  } /* BmbMessage () */

  /**
   * Retourne la connexion ayant envoy� le message
   * 
   * @return connexion ayant envoy� le message
   */
  public BmbConnexion getEmetteur ()
  {
    return this.emetteur ;
  } /* getAction () */
} /* BmbMessage */
