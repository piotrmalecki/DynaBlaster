package bombergraphics ;

/**
 * Gestionnaire de clavier.
 */
public class BmbVueClavier
{
  private boolean [] touches ; // contient les touches press�es et non press�es
  
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
   * Retourne si la touche est press�e ou non.
   * 
   * @param touche touche press�e
   * @return vrai si la touche est press�e
   */
  public boolean getPresse (int touche)
  {
    return this.touches [touche] ;
  } /* getPresse () */
  
  /**
   * D�finit si la touche est press�e ou non.
   * 
   * @param touche touche � d�finir
   * @param presse indique si la touche est press�e ou non
   */
  public void setPresse (int touche, boolean presse)
  {
    this.touches[touche] = presse ;
  } /* setPresse () */
} /* BmbClavier */
