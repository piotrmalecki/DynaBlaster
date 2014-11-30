package bombergraphics ;

import java.awt.* ;
import java.awt.event.* ;
import java.awt.image.* ;
import java.io.* ;
import java.util.* ;
import javax.swing.* ;

/**
 * <b>BmbGraphics</b> est utilisé pour afficher des graphismes 2D.
 * @see javax.swing.JPanel
 */
public class BmbGraphics extends JPanel
{
  private BufferedImage surface ;     // tampon sur lequel est dessiné la scene avant rendu
  private MediaTracker mediaTracker ; // gère le chargement des images
  private Hashtable images ;          // contient tous les sprites chargés

  /**
   * Constructeur de BmbGraphics.
   */
  public BmbGraphics ()
  {
    super () ;
    
    // initialisation des variables membres
    this.images = new Hashtable () ;
    this.mediaTracker = new MediaTracker (this) ;
    
    // initialisation du composant
    this.setDoubleBuffered (true) ;
    this.setVisible (true) ;
    this.addComponentListener (new GestionnaireComposant ()) ;
  } /* BmbGraphics () */

  /**
   * Ajoute un sprite à la liste des sprites.
   * 
   * @param fichier nom du fichier
   * @param sprite sprite à ajouter
   */
  public void ajouter (BmbSprite sprite)
  {
    // ajoute l'image à la liste
    this.images.put (sprite.getIdentifiant (), sprite) ;
  } /* ajouter () */

  /**
   * Supprime un sprite de la liste à partir du nom de fichier.
   * 
   * @param hauteur hauteur du cadre de dessin
   * @throws si l'image n'existe pas ou si le chargement est interrompu
   */
  public void supprimer (String fichier) throws Exception
  {
    this.images.remove (fichier) ;
  } /* supprimer () */

  /**
   * Met à jour le composant.
   * 
   * @param contexte contexte graphique de la surface
   */
  public void update (Graphics contexte)
  {
    this.paint (contexte) ;
  } /* update () */

  /**
   * Dessine le composant. A ne pas appeler directement. Utilisez repaint ().
   */
  public void paint (Graphics contexte)
  {
    if (BmbGraphics.this.surface == null)
    {
      // initialise la surface de rendu
      this.surface = (BufferedImage) this.createImage (this.getWidth (), this.getHeight ()) ;
    }
    
    contexte.drawImage (this.surface, 0, 0, this) ;
  } /* paint () */

  /**
   * Retourne un contexte de dessin.
   * 
   * @return contexte de dessin
   */
  public Graphics2D getContexte ()
  {
    if (this.surface == null)
    {
      // initialise la surface de rendu
      this.surface = (BufferedImage) this.createImage (BmbGraphics.this.getWidth (), BmbGraphics.this.getHeight ()) ;
    }
    
    return this.surface.createGraphics () ;
  }/* getContexte () */

  /**
   * Charge l'image à partir du nom de fichier spécifié en paramètre.
   * 
   * @param fichier nom du fichier image
   * @throws Exception si l'image ne peut être chargé ou n'existe pas
   */
  public Image getImage (String fichier) throws Exception
  {
    // charge l'image
    Image image = this.getToolkit().getImage (new File (fichier).getCanonicalPath ()) ;
    this.mediaTracker.addImage (image, 0) ;
    this.mediaTracker.waitForID (0) ;
    
    return image ;
  } /* getImage () */

  /**
   * Cherche un sprite dans la liste des images.
   * 
   * @param fichier nom du fichier image
   */
  public BmbSprite getSprite (String fichier)
  {
    return (BmbSprite) this.images.get (fichier) ;
  } /* getSprite () */

  /**
   * Gestionnaire de fenêtre de <b>JavaBomber</b>.
   */
  private class GestionnaireComposant extends ComponentAdapter
  {
    /**
     * Gère le redimensionnement du composant.
     * 
     * @param evenement evenement composant
     */
    public void componentResized (ComponentEvent evenement)
    {
      // initialisation du tampon de rendu
      BmbGraphics.this.surface = (BufferedImage) BmbGraphics.this.createImage (BmbGraphics.this.getWidth (), BmbGraphics.this.getHeight ()) ;
      Enumeration images = BmbGraphics.this.images.elements () ;
      while (images.hasMoreElements())
      {
        // change le contexte de dessin du sprite
        ((BmbSprite) images.nextElement ()).setContexte (BmbGraphics.this.getContexte ()) ;
      }
    } /* componentResized () */
  } /* GestionnaireComposant () */
} /* BmbGraphics */
