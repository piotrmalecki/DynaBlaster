package bomberscene ;

import bombergraphics.* ;
import bomberihm.* ;

/**
 * <b>BmbBlocDestructible</b> représente un bloc destructible.
 */
 
public class BmbBlocDestructible extends BmbElementVisuel
{
  private boolean detruit = false ;   // Indique si le bloc est détruit
  private int chrono ;    // chronomètre la durée avant l'explosion du bloc
  
  /**
   * Constructeur de <b>BmbBlocDestructible</b>.
   * 
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   */
  public BmbBlocDestructible (BmbElement parent, BmbSprite sprite, int x, int y)
  {
    super (parent, sprite, x, y) ;
    
    this.chrono    = 0 ;
  } /* BmbBlocDestructible () */

  /**
   * Rafraichit l'élément. Met à jour les coordonnées, etc...
   */
  public void rafraichir () throws Exception
  {
    if (this.detruit)
    {
      this.setModifie (this) ;
      
      // incrémenter le chronomètre
      this.chrono = this.chrono + BmbPartieServeur.DELAI ;
      
      // supprime le bloc à la fin de l'explosion
      if (this.chrono >= BmbBombe.DUREE_EXPLOSION)
      {
        this.setSupprime (this) ;

        this.getParent ().retirerElement (this) ;
        this.setParent (null) ;
      }
      else
      {
        super.rafraichir () ;
      }
    }
    else
    {
      super.rafraichir () ;
    }
  } /* rafraichir () */
  
  /**
   * Détruit le bloc
   */
  public void detruire ()
  {
    if (! this.detruit)
    {
      this.setImageX (2) ;
      this.setDelai (BmbBombe.DUREE_EXPLOSION / (this.getSprite ().getNombreColonne () - 1)) ;
      this.detruit = true ;
    }
  } /* detruire () */
} /* BmbBlocDestructible */
