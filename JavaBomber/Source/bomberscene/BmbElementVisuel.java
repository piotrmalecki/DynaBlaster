package bomberscene ;
import java.awt.* ;
import java.util.* ;

import bombergraphics.* ;
import bomberihm.* ;

/**
 * <b>BmbElement</b> est la classe de base des diff�rents �l�ments du jeu. Elle
 * offre plusieurs m�thodes � sp�cialiser permettant d'afficher, de mettre �
 * jour, et de g�rer les collisions.
 */
 
public class BmbElementVisuel extends BmbElement
{
  public static BmbGraphics surface ;        // image du fond
  public static final int CREE = 0 ;        // indique si un �l�ment est cr��
  public static final int MODIFIE = 1 ;     // indique si un �l�ment est modifi�
  public static final int NON_MODIFIE = 2 ; // indique si un �l�ment est modifi�

  private BmbSprite sprite ; // sprite repr�sentant l'�lement
  private int x ;            // coordonn�es sous x
  private int y ;            // coordonn�es sous y
  private int imageX ;       // colonne de l'image
  private int imageY ;       // ligne de l'image
  private boolean visible ;  // indique si l'�l�ment est visible
  private int delai ;        // d�lai entre chaque animation
  private int chrono ;       // compteur d'animation
	
  /**
   * Constructeur de <b>BmbElementVisuel</b>.
   * 
   * @param parent �l�ment parent
   * @param sprite sprite associ� a l'�l�ment
   * @param x coordonn�e sous x
   * @param y coordonn�e sous y
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
   * @param parent �l�ment parent
   * @param sprite sprite associ� a l'�l�ment
   * @param x coordonn�e sous x
   * @param y coordonn�e sous y
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
   * @param parent �l�ment parent
   * @param sprite sprite associ� a l'�l�ment
   * @param x coordonn�e sous x
   * @param y coordonn�e sous y
   * @param imageX colonne de l'image
   * @param imageY ligne de l'image
   * @param dur�e entre chaque image de l'animation
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
   * @param parent �l�ment parent
   * @param sprite sprite associ� a l'�l�ment
   * @param x coordonn�e sous x
   * @param y coordonn�e sous y
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
   * Rafraichit l'�l�ment. Met � jour les coordonn�es, etc...
   */
  public void rafraichir () throws Exception
  {
    super.rafraichir () ;
    
    // anime l'�l�ment
    if (this.delai > 0)
    {
      // incr�menter le chronom�tre
      this.chrono = this.chrono + BmbPartieServeur.DELAI ;
    
      // si le chrono d�passe le d�lai d'animation
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

    //affichage de l'�l�ment.
    if (this.visible)
    {
      this.sprite.dessiner (this.x, this.y, imageX, imageY) ;
    }
  } /* rafraichir () */

  /**
   * Retourne un tableau contenant les intersections entre les �l�ments
   * 
   * @return tableau d'�l�ments
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
   * Retourne la coordonn�e sous x de l'�l�ment.
   * 
   * @return coordonn�e sous x
   */
  public int getX ()
  {
    return this.x ;
  } /*getX () */

  /**
   * Retourne la coordonn�e sous y de l'�l�ment.
   * 
   * @return coordonn�e sous y
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
   * Retourne le sprite de l'�l�ment visuel.
   * 
   * @return sprite de l'�l�ment visuel
   */
  public BmbSprite getSprite ()
  {
    return this.sprite ;
  } /* getImageX () */

  /**
   * Retourne le rectangle utilis� pour g�rer les collisions.
   * 
   * @return rectangle utilis� pour g�rer les collisions
   */
  public Rectangle getForme ()
  {
    return new Rectangle (this.getX (), this.getY (), this.getSprite ().getLargeurCadre (), this.getSprite ().getHauteurCadre ()) ;
  } /* getForme () */

  /**
   * Indique si l'�l�ment est visible.
   * 
   * @return vrai si l'�l�ment est visible
   */
  public boolean getVisible ()
  {
    return this.visible ;
  } /* getVisible () */

  /**
   * Met � jour la coordonn�e sous x de l'�l�ment.
   * 
   * @param x coordonn�e sous x
   */
  public void setX (int x)
  {
    this.x = x ;
  } /* setX () */

  /**
   * Met � jour la coordonn�e sous y de l'�l�ment.
   * 
   * @param y coordonn�e sous y
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
   * Fixe le d�lai d'animation de l'�l�ment.
   * 
   * @param delai d�lai d'animation
   */
  public void setDelai (int delai)
  {
    this.delai = delai ;
  } /* setDelai () */

  /**
   * Rends visible ou invisible l'�l�ment.
   * 
   * @param visible indique si l'�l�ment doit �tre visible
   */
  public void setVisible (boolean visible)
  {
    this.visible = visible ;
  } /* setVisible () */
} /* BmbElementVisuel */
