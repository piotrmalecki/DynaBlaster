package bombernetwork ;

/**
 * <b>BmbMessageServeur</b> encapsule les messages envoyés entre client et serveur. Un
 * message est composé de plusieurs champs séparés par la sentinelle.
 */
public class BmbMessageServeur extends BmbMessage
{
  private BmbConnexion emetteur ; // paramètres du message
  
  /**
   * Constructeur de <b>BmbMessage</b>.
   * 
   * @param emetteur connexion ayant émis le message
   * @param message chaîne représentant le message
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
   * @param emetteur connexion ayant émis le message
   * @param action action du message
   * @param message chaîne représentant le message
   */
  public BmbMessageServeur (BmbConnexion emetteur, int action, String message)
  {
    super (action, message) ;
    
    // initialisation du tableau d'arguments
    this.emetteur = emetteur ;
  } /* BmbMessage () */

  /**
   * Retourne la connexion ayant envoyé le message
   * 
   * @return connexion ayant envoyé le message
   */
  public BmbConnexion getEmetteur ()
  {
    return this.emetteur ;
  } /* getAction () */
} /* BmbMessage */
