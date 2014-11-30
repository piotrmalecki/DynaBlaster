package bomberscene ;
import java.awt.* ;
import java.util.* ;

import bombergraphics.* ;
import bomberihm.* ;

/**
 * <b>BmbElement</b> est la classe de base des différents éléments du jeu. Elle
 * offre plusieurs méthodes à spécialiser permettant d'afficher, de mettre à
 * jour, et de gérer les collisions.
 */
 
public class BmbElementVisuel extends BmbElement
{
  public static BmbGraphics surface ;        // image du fond
  public static final int CREE = 0 ;        // indique si un élément est créé
  public static final int MODIFIE = 1 ;     // indique si un élément est modifié
  public static final int NON_MODIFIE = 2 ; // indique si un élément est modifié

  private BmbSprite sprite ; // sprite représentant l'élement
  private int x ;            // coordonnées sous x
  private int y ;            // coordonnées sous y
  private int imageX ;       // colonne de l'image
  private int imageY ;       // ligne de l'image
  private boolean visible ;  // indique si l'élément est visible
  private int delai ;        // délai entre chaque animation
  private int chrono ;       // compteur d'animation
	
  /**
   * Constructeur de <b>BmbElementVisuel</b>.
   * 
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   */
  public BmbElementVisuel (BmbElement parent, BmbSprite sprite, int x, int y)
  {
    super (parent) ;
    
    // initialisation des variables.
    this.sprite  = sprite ;
    this.x       = x ;
    this.y       = y ;
    this.imageX  = 1 ;
    this.imageY  = 1 ;
    this.visible = true ;
    this.delai   = 0 ;
    this.chrono  = 0 ;
    
    this.setCree (this) ;
  } /* BmbElementVisuel () */ 

  /**
   * Constructeur de <b>BmbElementVisuel</b>.
   * 
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   * @param imageX colonne de l'image
   * @param imageY ligne de l'image
   */
  public BmbElementVisuel (BmbElement parent, BmbSprite sprite, int x, int y, int imageX, int imageY)
  {
    super (parent) ;
    
    // initialisation des variables.
    this.sprite  = sprite ;
    this.x       = x ;
    this.y       = y ;
    this.imageX  = imageX ;
    this.imageY  = imageY ;
    this.visible = true ;
    this.delai   = 0 ;
    this.chrono  = 0 ;

    this.setCree (this) ;
  } /* BmbElementVisuel () */

  /**
   * Constructeur de <b>BmbElementVisuel</b>.
   * 
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   * @param imageX colonne de l'image
   * @param imageY ligne de l'image
   * @param durée entre chaque image de l'animation
   */
  public BmbElementVisuel (BmbElement parent, BmbSprite sprite, int x, int y, int imageX, int imageY, int delai)
  {
    super (parent) ;
    
    // initialisation des variables.
    this.sprite  = sprite ;
    this.x       = x ;
    this.y       = y ;
    this.imageX  = imageX ;
    this.imageY  = imageY ;
    this.visible = true ;
    this.delai   = delai ;
    this.chrono  = 0 ;

    this.setCree (this) ;
  } /* BmbElementVisuel () */

/**
   * Constructeur de <b>BmbElementVisuel</b>.
   * 
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   * @param imageX colonne de l'image
   * @param imageY ligne de l'image
   */
  public BmbElementVisuel (BmbElement parent, BmbSprite sprite, int x, int y, int imageX, int imageY, String identifiant)
  {
    super (parent, identifiant) ;
    
    // initialisation des variables.
    this.sprite  = sprite ;
    this.x       = x ;
    this.y       = y ;
    this.imageX  = imageX ;
    this.imageY  = imageY ;
    this.visible = true ;
    this.delai   = 0 ;
    this.chrono  = 0 ;
  } /* BmbElementVisuel () */

  /**
   * Rafraichit l'élément. Met à jour les coordonnées, etc...
   */
  public void rafraichir () throws Exception
  {
    super.rafraichir () ;
    
    // anime l'élément
    if (this.delai > 0)
    {
      // incrémenter le chronomètre
      this.chrono = this.chrono + BmbPartieServeur.DELAI ;
    
      // si le chrono dépasse le délai d'animation
      if (this.chrono >= this.delai)
      {
        // change d'animation
        if (this.getImageX () < this.getSprite ().getNombreColonne ())
        {
          this.setImageX (this.getImageX () + 1) ;
        }
        else
        {
          this.setImageX (1) ;
        }
        this.chrono = 0 ;
      }
    }

    //affichage de l'élément.
    if (this.visible)
    {
      this.sprite.dessiner (this.x, this.y, imageX, imageY) ;
    }
  } /* rafraichir () */

  /**
   * Retourne un tableau contenant les intersections entre les éléments
   * 
   * @return tableau d'éléments
   */
  public Vector intersection (Rectangle forme)
  {
    Vector elements = new Vector () ;
    for (int i = 0; i < this.getParent().getNombreElement() ; i++ )
    {
      if (forme.intersects(((BmbElementVisuel)this.getParent().getElement(i)).getForme()))
      {
        elements.addElement(this.getParent().getElement(i)) ;
      }
    }
    return elements;
  } /* intersection () */ 

  /**
   * Retourne la coordonnée sous x de l'élément.
   * 
   * @return coordonnée sous x
   */
  public int getX ()
  {
    return this.x ;
  } /*getX () */

  /**
   * Retourne la coordonnée sous y de l'élément.
   * 
   * @return coordonnée sous y
   */
  public int getY ()
  {
    return this.y ;
  } /* getY () */

  /**
   * Retourne la colonne de l'image.
   * 
   * @return colonne de l'image
   */
  public int getImageX ()
  {
    return this.imageX ;
  } /* getImageX () */

  /**
   * Retourne la ligne de l'image.
   * 
   * @return ligne de l'image
   */
  public int getImageY ()
  {
    return this.imageY ;
  } /* getImageY () */

  /**
   * Retourne le sprite de l'élément visuel.
   * 
   * @return sprite de l'élément visuel
   */
  public BmbSprite getSprite ()
  {
    return this.sprite ;
  } /* getImageX () */

  /**
   * Retourne le rectangle utilisé pour gèrer les collisions.
   * 
   * @return rectangle utilisé pour gèrer les collisions
   */
  public Rectangle getForme ()
  {
    return new Rectangle (this.getX (), this.getY (), this.getSprite ().getLargeurCadre (), this.getSprite ().getHauteurCadre ()) ;
  } /* getForme () */

  /**
   * Indique si l'élément est visible.
   * 
   * @return vrai si l'élément est visible
   */
  public boolean getVisible ()
  {
    return this.visible ;
  } /* getVisible () */

  /**
   * Met à jour la coordonnée sous x de l'élément.
   * 
   * @param x coordonnée sous x
   */
  public void setX (int x)
  {
    this.x = x ;
  } /* setX () */

  /**
   * Met à jour la coordonnée sous y de l'élément.
   * 
   * @param y coordonnée sous y
   */
  public void setY (int y)
  {
    this.y = y ;
  } /* setY () */

  /**
   * Choisis la colonne de l'image.
   * 
   * @param imageX colonne de l'image
   */
  public void setImageX (int imageX)
  {
    this.imageX = imageX;
  } /* setImageX () */

  /**
   * Choisis la ligne de l'image.
   * 
   * @param imageY ligne de l'image
   */
  public void setImageY (int imageY)
  {
    this.imageY = imageY;
  } /* setImageY () */

  /**
   * Fixe le délai d'animation de l'élément.
   * 
   * @param delai délai d'animation
   */
  public void setDelai (int delai)
  {
    this.delai = delai ;
  } /* setDelai () */

  /**
   * Rends visible ou invisible l'élément.
   * 
   * @param visible indique si l'élément doit être visible
   */
  public void setVisible (boolean visible)
  {
    this.visible = visible ;
  } /* setVisible () */
} /* BmbElementVisuel */
