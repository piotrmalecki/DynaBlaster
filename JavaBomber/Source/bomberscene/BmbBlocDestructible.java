package bomberscene ;

import bombergraphics.* ;
import bomberihm.* ;

/**
 * <b>BmbBlocDestructible</b> repr�sente un bloc destructible.
 */
 
public class BmbBlocDestructible extends BmbElementVisuel
{
  private boolean detruit = false ;   // Indique si le bloc est d�truit
  private int chrono ;    // chronom�tre la dur�e avant l'explosion du bloc
  
  /**
   * Constructeur de <b>BmbBlocDestructible</b>.
   * 
   * @param parent �l�ment parent
   * @param sprite sprite associ� a l'�l�ment
   * @param x coordonn�e sous x
   * @param y coordonn�e sous y
   */
  public BmbBlocDestructible (BmbElement parent, BmbSprite sprite, int x, int y)
  {
    super (parent, sprite, x, y) ;
    
    this.chrono    = 0 ;
  } /* BmbBlocDestructible () */

  /**
   * Rafraichit l'�l�ment. Met � jour les coordonn�es, etc...
   */
  public void rafraichir () throws Exception
  {
    if (this.detruit)
    {
      this.setModifie (this) ;
      
      // incr�menter le chronom�tre
      this.chrono = this.chrono + BmbPartieServeur.DELAI ;
      
      // supprime le bloc � la fin de l'explosion
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
   * D�truit le bloc
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
