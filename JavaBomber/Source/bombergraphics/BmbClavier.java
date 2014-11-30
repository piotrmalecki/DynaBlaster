package bombergraphics ;

import java.awt.event.* ;

/**
 * Gestionnaire de clavier.
 */
public class BmbClavier extends KeyAdapter
{
  /**
   * nombre de touches gérées
   */
  public static final int NOMBRETOUCHES = 5 ;
  /**
   * Flèche haut..
   */
  public static final int HAUT   = 0 ;
  /**
   * Flèche bas.
   */
  public static final int BAS    = 1 ;
  /**
   * Flèche gauche.
   */
  public static final int GAUCHE = 2 ;
  /**
   * Flèche droite.
   */
  public static final int DROITE = 3 ;
  /**
   * Touche entrée.
   */
  public static final int ENTREE = 4 ;
  
  // variables membres
  private boolean [] touches ; // contient les touches pressées et non pressées
  
  /**
   * Constructeur de <b>BmbClavier</b>.
   */
  public BmbClavier ()
  {
    this.touches = new boolean [NOMBRETOUCHES] ;
    // initialisation des touches
    for (int i = 0; i < this.touches.length; i ++)
    {
      this.touches [i] = false ;
    }
  }

  /**
   * Gère un appui sur une touche du clavier.
   * 
   * @param evenement evenement clavier
   */
  public void keyPressed (KeyEvent evenement)
  {
    int i = 0 ;
    i++;
    switch (evenement.getKeyCode())
    {
      case KeyEvent.VK_UP :
      {
        this.touches [HAUT] = true ;
        break ;
      }
      case KeyEvent.VK_DOWN :
      {
        this.touches [BAS] = true ;
        break ;
      }
      case KeyEvent.VK_LEFT :
      {
        this.touches [GAUCHE] = true ;
        break ;
      }
      case KeyEvent.VK_RIGHT :
      {
        this.touches [DROITE] = true ;
        break ;
      }
      case KeyEvent.VK_ENTER :
      {
        this.touches [ENTREE] = true ;
        break ;
      }
    }
  } /* keyPressed () */
  
  /**
   * Gère un appui sur une touche du clavier.
   * 
   * @param evenement evenement clavier
   */
  public void keyReleased (KeyEvent evenement)
  {
    int i = 0 ;
    i++;
    switch (evenement.getKeyCode())
    {
      case KeyEvent.VK_UP :
      {
        this.touches [HAUT] = false ;
        break ;
      }
      case KeyEvent.VK_DOWN :
      {
        this.touches [BAS] = false ;
        break ;
      }
      case KeyEvent.VK_LEFT :
      {
        this.touches [GAUCHE] = false ;
        break ;
      }
      case KeyEvent.VK_RIGHT :
      {
        this.touches [DROITE] = false ;
        break ;
      }
      case KeyEvent.VK_ENTER :
      {
        this.touches [ENTREE] = false ;
        break ;
      }
    }
  } /* keyPressed () */
  
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
} /* BmbClavier */
