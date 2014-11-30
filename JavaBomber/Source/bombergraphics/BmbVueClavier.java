package bombergraphics ;

/**
 * Gestionnaire de clavier.
 */
public class BmbVueClavier
{
  private boolean [] touches ; // contient les touches pressées et non pressées
  
  /**
   * Constructeur de <b>BmbVueClavier</b>.
   */
  public BmbVueClavier ()
  {
    this.touches = new boolean [BmbClavier.NOMBRETOUCHES] ;
    // initialisation des touches
    for (int i = 0; i < this.touches.length; i ++)
    {
      this.touches [i] = false ;
    }
  } /* BmbVueClavier () */
  
  /**
   * Retourne si la touche est pressée ou non.
   * 
   * @param touche touche pressée
   * @return vrai si la touche est pressée
   */
  public boolean getPresse (int touche)
  {
    return this.touches [touche] ;
  } /* getPresse () */
  
  /**
   * Définit si la touche est pressée ou non.
   * 
   * @param touche touche à définir
   * @param presse indique si la touche est pressée ou non
   */
  public void setPresse (int touche, boolean presse)
  {
    this.touches[touche] = presse ;
  } /* setPresse () */
} /* BmbClavier */
