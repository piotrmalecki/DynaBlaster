package bomberscene ;

import bombergraphics.* ;

/**
 * <b>BmbBloc</b> représente un bloc stopppant un joueur.
 */
public class BmbBloc extends BmbElementVisuel
{
  /**
   * Constructeur de <b>BmbBloc</b>.
   * 
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   */
  public BmbBloc (BmbElement parent, BmbSprite sprite, int x, int y)
  {
    super (parent, sprite, x, y) ;
  } /* BmbBloc () */
} /* BmbBloc */
