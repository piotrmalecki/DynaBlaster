package bomberscene ;

import java.util.* ;
import bomberihm.* ;
import bombergraphics.* ;

/**
 * <b>BmbBombe</b> g�re le compte � rebours d'une bombe.
 */
public class BmbBombe extends BmbElementVisuel
{
  public static final int DUREE_BOMBE     = 3000 ; // dur�e de l'explosion
  public static final int DUREE_EXPLOSION = 480 ;  // dur�e de l'explosion
    
  private int puissance ; // puissance de la bombe en nombre de cases
  private int temps ;     // dur�e avant l'explosion de la bombe
  private int chrono ;    // chronom�tre la dur�e de vie de la bombe

  /**
   * Constructeur de <b>BmbBombe</b>.
   * 
   * @param puissance puissance associ�e � la bombe
   * @param temps temps associ� � la bombe
   * @param parent �l�ment parent
   * @param sprite sprite associ� a l'�l�ment
   * @param x coordonn�e sous x
   * @param y coordonn�e sous y
   */
  public BmbBombe (int puissance, BmbElement parent, BmbSprite sprite, int x, int y)
  {
    // cr�e la bombe en centrant les coordonn�es sur la case
    super (parent, sprite, BmbSceneServeur.TAILLE_BORD + Math.round ((float) (x - BmbSceneServeur.TAILLE_BORD) / (float) 64) * 64,
                            BmbSceneServeur.TAILLE_BORD + Math.round ((float) (y - BmbSceneServeur.TAILLE_BORD) / (float) 64) * 64) ;
    // intialisation des variables.
    this.puissance = puissance ;  
    this.temps = DUREE_BOMBE ;       
    this.chrono = 0 ;
    this.setDelai (temps / this.getSprite ().getNombreColonne ()) ;
  } /* BmbBombe () */

  /**
   * Rafraichit l'�l�ment.
	 */
  public void rafraichir () throws Exception
  {
    // incr�menter le chronom�tre
    this.chrono = this.chrono + BmbPartieServeur.DELAI ;
    
    // teste s'il y a eu une collision avec une flamme
    boolean explosion = false ;
    Vector tabCollision = this.intersection (this.getForme ()) ;
    for (int j = 0 ; j < tabCollision.size(); j++)
    {
      if (tabCollision.elementAt (j) instanceof BmbFlamme)
      {
        explosion = true ;
      }
    }
    
    // s'il est temps que la bombe explose
    if ((this.chrono >= this.temps) || (explosion))
    {
      // ajouter les flammes
      BmbFlamme flamme = new BmbFlamme (this.puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX (), this.getY (), 1, 1) ;
      this.getParent().ajouterElement (flamme) ;
      
      flamme = new BmbFlamme (this.puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX (), this.getY () - this.getSprite ().getHauteurCadre (), 1, BmbFlamme.FLAMME_HAUT) ;
      this.getParent ().ajouterElement (flamme) ;
      
      flamme = new BmbFlamme (this.puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX (), this.getY () + this.getSprite ().getHauteurCadre (), 1, BmbFlamme.FLAMME_BAS) ;
      this.getParent ().ajouterElement (flamme) ;
      
      flamme = new BmbFlamme (this.puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX () - this.getSprite ().getLargeurCadre (), this.getY (), 1, BmbFlamme.FLAMME_GAUCHE) ;
      this.getParent ().ajouterElement (flamme) ;
      
      flamme = new BmbFlamme (this.puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX () + this.getSprite ().getLargeurCadre (), this.getY (), 1, BmbFlamme.FLAMME_DROITE) ;
      this.getParent ().ajouterElement (flamme) ;
      
      // supprimer la bombe de la scene
      this.setSupprime (this) ;
      
      this.getParent ().retirerElement (this) ;
      this.setParent (null) ;
    }
    else
    {
      this.setModifie (this) ;
      super.rafraichir () ;
    }
  } /* rafraichir () */
} /* BmbBombe () */
