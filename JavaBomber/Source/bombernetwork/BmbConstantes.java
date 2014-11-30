package bombernetwork ;

/**
 * <b>BmbConstantes</b> contient toutes les constantes identifiant un message
 * spécifique au jeu JavaBomber.
 */
public class BmbConstantes
{
  /**
   * Indique qu'une connexion vient d'être acceptée.
   */
  public static final int MSG_ACCEPTEROK = 3 ;
  /**
   * Indique les connexions associées au serveur.
   */
  public static final int MSG_CONNEXIONS = 4 ;
  /**
   * Indique que la connexion ayant envoyée le message est prête a jouer.
   */
  public static final int MSG_PRET = 5 ;
  /**
   * Indique que la connexion ayant envoyée le message est prête a jouer.
   */
  public static final int MSG_PRETOK = 6 ;
  /**
   * Indique que la partie démarre.
   */
  public static final int MSG_DEMARRER = 7 ;
  /**
   * Indique que la partie s'arrête.
   */
  public static final int MSG_ARRETER = 8 ;
  /**
   * Indique que la partie s'arrête.
   */
  public static final int MSG_NOMJOUEUR = 9 ;
  /**
   * Indique si une touche est pressée.
   */
  public static final int MSG_TOUCHE = 10 ;
  /**
   * Indique qu'un élément est créé.
   */
  public static final int MSG_ELT_CREE = 11 ;
  /**
   * Indique qu'un élément est modifié.
   */
  public static final int MSG_ELT_MODIFIE = 12 ;
  /**
   * Indique qu'un élément estsupprimé.
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
