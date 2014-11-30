package bomberihm;

import bombergraphics.*;
import bombernetwork.* ;

/**
 * Contient les informations concernant un joueur.
 */
public class BmbVueJoueur
{
  private BmbConnexion connexion ;   // connexion du joueur
  private String nom ;               // nom du joueur
  private boolean pret ;             // indique si le joueur est pr�t
  private boolean mort ;             // indique si le joueur est mort
  private boolean rafraichitOk ;     // indique si l'affichage du joueur peut �tre rafraichit
  private BmbVueClavier vueClavier ; // vue du clavier associ� au joueur
  
/**
 * Constructeur de <b>BmbJoueur</b>.
 */
  public BmbVueJoueur (BmbConnexion connexion, String nom, boolean pret)
  {
    super () ;
    
    this.connexion = connexion ;
    this.nom = nom ;
    this.pret = pret ;
    this.mort = false ;
    this.rafraichitOk = true ;
    this.vueClavier = new BmbVueClavier() ;
  }
  
  /**
   * Retourne la connexion associ�e au joueur.
   * 
   * @return connexion du joueur
   */
  public BmbConnexion getConnexion ()
  {
    return this.connexion ;
  } /* getConnexion () */
  

  /**
   * Retourne le nom du joueur.
   * 
   * @return nom du joueur
   */
  public String getNom ()
  {
    return this.nom ;
  } /* getNom () */
  
  /**
   * Retourne l'�tat du joueur (pr�t ou non pr�t).
   * 
   * @return etat du joueur
   */
  public boolean getPret ()
  {
    return this.pret ;
  } /* getPret () */
  
  /**
   * Retourne l'�tat du joueur (mort ou vivant).
   * 
   * @return etat du joueur
   */
  public boolean getMort ()
  {
    return this.mort ;
  } /* getMort () */
  
  /**
   * Indique si le rafraichissement sur le client.
   * 
   * @return client rafraichit
   */
  public boolean getRafraichitOk ()
  {
    return this.rafraichitOk ;
  } /* getRafraichitOk () */
  
  /**
   * Retourne la vue clavier du joueur.
   * 
   * @return vueClavier du joueur
   */
  public BmbVueClavier getVueClavier ()
  {
    return this.vueClavier ;
  } /* getPret () */
  
  /**
   * Modifie le nom du joueur.
   * 
   * @param nom nom du joueur
   */
  public void setNom (String nom)
  {
    this.nom = nom ;
  } /* setNom () */
  
  /**
   * Modifie l'�tat du joueur (pr�t ou non pr�t).
   * 
   * @param etat l'�tat du joueur
   */
  public void setPret (boolean pret)
  {
    this.pret = pret ;
  } /* setNom () */

  /**
   * Modifie l'�tat du joueur (mort ou vivant).
   * 
   * @param etat l'�tat du joueur
   */
  public void setMort (boolean mort)
  {
    this.mort = mort ;
  } /* setMort () */

  /**
   * Enregistre si le rafraichissement sur le client est fini.
   * 
   * @param etat �tat du rafraichissement
   */
  public void setRafraichitOk (boolean rafraichitOk)
  {
    this.rafraichitOk = rafraichitOk ;
  } /* setRafraichitOk () */
} /* BmbJoueur */
