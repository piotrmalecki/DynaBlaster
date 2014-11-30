package bomberscene ;

import java.util.* ;
import bomberihm.* ;
import bombergraphics.* ;

/**
 * <b>BmbBombe</b> gère le compte à rebours d'une bombe.
 */
public class BmbBombe extends BmbElementVisuel
{
  public static final int DUREE_BOMBE     = 3000 ; // durée de l'explosion
  public static final int DUREE_EXPLOSION = 480 ;  // durée de l'explosion
    
  private int puissance ; // puissance de la bombe en nombre de cases
  private int temps ;     // durée avant l'explosion de la bombe
  private int chrono ;    // chronomètre la durée de vie de la bombe

  /**
   * Constructeur de <b>BmbBombe</b>.
   * 
   * @param puissance puissance associée à la bombe
   * @param temps temps associé à la bombe
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   */
  public BmbBombe (int puissance, BmbElement parent, BmbSprite sprite, int x, int y)
  {
    // crée la bombe en centrant les coordonnées sur la case
    super (parent, sprite, BmbSceneServeur.TAILLE_BORD + Math.round ((float) (x - BmbSceneServeur.TAILLE_BORD) / (float) 64) * 64,
                            BmbSceneServeur.TAILLE_BORD + Math.round ((float) (y - BmbSceneServeur.TAILLE_BORD) / (float) 64) * 64) ;
    // intialisation des variables.
    this.puissance = puissance ;  
    this.temps = DUREE_BOMBE ;       
    this.chrono = 0 ;
    this.setDelai (temps / this.getSprite ().getNombreColonne ()) ;
  } /* BmbBombe () */

  /**
   * Rafraichit l'élément.
	 */
  public void rafraichir () throws Exception
  {
    // incrémenter le chronomètre
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
