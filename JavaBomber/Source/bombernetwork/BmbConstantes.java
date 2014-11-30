package bombernetwork ;

/**
 * <b>BmbConstantes</b> contient toutes les constantes identifiant un message
 * sp�cifique au jeu JavaBomber.
 */
public class BmbConstantes
{
  /**
   * Indique qu'une connexion vient d'�tre accept�e.
   */
  public static final int MSG_ACCEPTEROK = 3 ;
  /**
   * Indique les connexions associ�es au serveur.
   */
  public static final int MSG_CONNEXIONS = 4 ;
  /**
   * Indique que la connexion ayant envoy�e le message est pr�te a jouer.
   */
  public static final int MSG_PRET = 5 ;
  /**
   * Indique que la connexion ayant envoy�e le message est pr�te a jouer.
   */
  public static final int MSG_PRETOK = 6 ;
  /**
   * Indique que la partie d�marre.
   */
  public static final int MSG_DEMARRER = 7 ;
  /**
   * Indique que la partie s'arr�te.
   */
  public static final int MSG_ARRETER = 8 ;
  /**
   * Indique que la partie s'arr�te.
   */
  public static final int MSG_NOMJOUEUR = 9 ;
  /**
   * Indique si une touche est press�e.
   */
  public static final int MSG_TOUCHE = 10 ;
  /**
   * Indique qu'un �l�ment est cr��.
   */
  public static final int MSG_ELT_CREE = 11 ;
  /**
   * Indique qu'un �l�ment est modifi�.
   */
  public static final int MSG_ELT_MODIFIE = 12 ;
  /**
   * Indique qu'un �l�ment estsupprim�.
   */
  public static final int MSG_ELT_SUPPRIME = 13 ;
  /**
   * Indique aux clients si il faut rafraichir.
   */
  public static final int MSG_RAFRAICHIR = 14 ;
  /**
   * Indique au serveur que le client a fini de rafraichir.
   */
  public static final int MSG_RAFRAICHITOK = 15 ;
} /* BmbMessage */
