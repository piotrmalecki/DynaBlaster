package bombergraphics ;

import java.awt.* ;
import java.awt.image.* ;

/**
 * <b>BmbSprite</b> encapsule une image et permet de dessiner une animation.
 */
public class BmbSprite
{
  private static final String IMAGE_NULLE      = "Erreur : l'image n'est pas chargée" ;
  private static final String CADRE_INCORRECT  = "Erreur : la taille du cadre est incorrecte" ;
  private static final String IMAGE_INCORRECTE = "Erreur : l'image spécifié est hors cadre" ;

  private String identifiant ;  // identifiant du sprite
  private BufferedImage image ; // tampon sur lequel est dessiné la scene avant rendu
  private Graphics2D contexte ; // contexte du tampon de rendu
  private int largeur ;         // largeur du cadre de dessin
  private int hauteur ;         // hauteur du cadre de dessin

  /**
   * Constructeur de <b>BmbSprite</b>.
   * 
   * @param image image contenu
   * @param contexte contexte de dessin
   */
  public BmbSprite (String identifiant, Image sprite, Graphics2D contexte, boolean transparent) throws Exception
  {
    super () ;
    
    if ((sprite.getWidth (null) <= 0) || (sprite.getHeight (null) <= 0))
    {
      throw new Exception (IMAGE_NULLE) ;
    }

    // initialisation des variables membres
    this.identifiant = identifiant ;
    this.contexte    = contexte ;
    this.largeur     = sprite.getWidth (null) ;
    this.hauteur     = sprite.getHeight (null) ;

    // initialise l'image
    if (transparent)
    {
      this.image = new BufferedImage (sprite.getWidth (null), sprite.getHeight (null), BufferedImage.TYPE_INT_ARGB) ;
    }
    else
    {
      this.image = new BufferedImage (sprite.getWidth (null), sprite.getHeight (null), BufferedImage.TYPE_INT_RGB) ;
    }
    Graphics2D imageContexte = this.image.createGraphics () ;
    imageContexte.drawImage (sprite, 0, 0, null) ;
  } /* BmbSprite () */

  /**
   * Constructeur de <b>BmbSprite</b>.
   * 
   * @param image image contenu
   * @param contexte de dessin
   * @param largeur largeur du cadre de dessin
   * @param hauteur hauteur du cadre de dessin
   */
  public BmbSprite (String identifiant, Image sprite, Graphics2D contexte, int largeur, int hauteur, boolean transparent) throws Exception
  {
    super () ;
    
    // vérification des paramètres
    if ((largeur <= 0) || (hauteur <= 0) || ((sprite.getWidth (null) % largeur) != 0) || ((sprite.getHeight (null) % hauteur) != 0))
    {
      throw new Exception (CADRE_INCORRECT) ;
    }
    
    // initialisation des variables membres
    this.identifiant = identifiant ;
    this.contexte    = contexte ;
    this.largeur     = largeur ;
    this.hauteur     = hauteur ;
    
    // initialise l'image
    if (transparent)
    {
      this.image = new BufferedImage (sprite.getWidth (null), sprite.getHeight (null), BufferedImage.TYPE_INT_ARGB) ;
    }
    else
    {
      this.image = new BufferedImage (sprite.getWidth (null), sprite.getHeight (null), BufferedImage.TYPE_INT_RGB) ;
    }
    Graphics2D imageContexte = this.image.createGraphics () ;
    imageContexte.drawImage (sprite, 0, 0, null) ;
  } /* BmbSprite () */

  /**
   * Dessine l'image.
   * 
   * @param x abscisse de l'origine
   * @param y ordonnée de l'origine
   */
  public void dessiner (int x, int y)
  {
    // dessine l'image
    this.contexte.drawImage (this.image,
                              x, y, x + this.largeur, y + this.hauteur,
                              0, 0, this.largeur, this.hauteur, null) ;
  } /* dessiner () */

  /**
   * Dessine une portion d'image.
   * 
   * @param x abscisse de l'origine
   * @param y ordonnée de l'origine
   * @param imageX colonne de l'image
   * @param imageY ligne de l'image
   * @throws Exception si imageX n'est pas compris entre 1 et getNombreColonne ()
   * ou si y n'est pas compris entre 1 et getNombreLigne ()
   */
  public void dessiner (int x, int y, int imageX, int imageY) throws Exception
  {
    if ((imageX <= 0) || (imageX > getNombreColonne ()) || (imageY <= 0) || (imageY > getNombreLigne ()))
    {
      throw new Exception (IMAGE_INCORRECTE) ;
    }

    // dessine l'image
    this.contexte.drawImage (this.image, x, y, x + this.largeur, y + this.hauteur,
                             this.largeur * imageX - this.largeur,
                             this.hauteur * imageY - this.hauteur,
                             this.largeur * imageX,
                             this.hauteur * imageY, null) ;
  } /* dessiner () */

  /**
   * Retourne l'identifiant du sprite.
   * 
   * @return identifiant du sprite
   */
  public String getIdentifiant ()
  {
    return this.identifiant ;
  } /* getIdentifiant () */

  /**
   * Retourne un contexte de dessin.
   * 
   * @return contexte de dessin
   */
  public Graphics2D getContexte ()
  {
    return this.image.createGraphics () ;
  } /* getContexte () */

  /**
   * Modifie le contexte de dessin.
   * 
   * @param contexte contexte de dessin
   */
  public void setContexte (Graphics2D contexte)
  {
    this.contexte = contexte ;
  } /* getContexte () */

  /**
   * Retourne la largeur de l'image.
   * 
   * @return largeur de l'image
   */
  public int getLargeur ()
  {
    return this.image.getWidth () ;
  } /* getLargeur () */

  /**
   * Retourne la hauteur de l'image.
   * 
   * @return hauteur de l'image
   */
  public int getHauteur ()
  {
    return this.image.getHeight () ;
  } /* getHauteur () */

  /**
   * Retourne la largeur du cadre de l'image.
   * 
   * @return largeur du cadre de l'image
   */
  public int getLargeurCadre ()
  {
    return this.largeur ;
  } /* getLargeurCadre () */

  /**
   * Retourne la hauteur du cadre de l'image.
   * 
   * @return hauteur du cadre de l'image
   */
  public int getHauteurCadre ()
  {
    return this.hauteur ;
  } /* getHauteurCadre () */

  /**
   * Retourne le nombre de colonne de l'image.
   * 
   * @return nombre d'image contenu dans la largeur
   */
  public int getNombreColonne ()
  {
    return this.image.getWidth () / this.largeur ;
  } /* getNombreColonne () */

  /**
   * Retourne le nombre de ligne de l'image.
   * 
   * @return nombre d'image contenu dans la hauteur
   */
  public int getNombreLigne ()
  {
    return this.image.getHeight () / this.hauteur ;
  } /* getNombreLigne () */
} /* BmbSprite */
