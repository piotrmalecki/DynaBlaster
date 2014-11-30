package bomberscene ;

import bombergraphics.* ;

/**
 * <b>BmbBloc</b> repr�sente un bloc stopppant un joueur.
 */
public class BmbBloc extends BmbElementVisuel
{
  /**
   * Constructeur de <b>BmbBloc</b>.
   * 
   * @param parent �l�ment parent
   * @param sprite sprite associ� a l'�l�ment
   * @param x coordonn�e sous x
   * @param y coordonn�e sous y
   */
  public BmbBloc (BmbElement parent, BmbSprite sprite, int x, int y)
  {
    super (parent, sprite, x, y) ;
  } /* BmbBloc () */
} /* BmbBloc */
