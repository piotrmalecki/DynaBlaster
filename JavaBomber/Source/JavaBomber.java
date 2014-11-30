import java.awt.event.* ;
import java.io.* ;
import javax.swing.* ;
import bomberihm.* ;

/**
 * Point d'entrée et fenêtre principale de l'application <b>JavaBomber</b>.
 * @see javax.swing.JFrame
 * @see bombernetwork.BmbServeur
 * @see bombernetwork.BmbClient
 */
public class JavaBomber extends JFrame
{
  private static final int FENETRE_HEIGHT = 600 ; // hauteur de la fenêtre
  private static final int FENETRE_WIDTH  = 800 ; // largeur de la fenêtre
  private static final String FENETRE_ICONE = "./Image/JavaBomber.png" ; // fichier contenant l'icone
  private static final String FENETRE_NOM = "JavaBomber" ; // nom de la fenêtre
  
  private BmbPanneau panneau ; // panneau principal de l'application
  
  /**
   * Constructeur de <b>JavaBomberPanneau</b>.
   */
  public JavaBomber ()
  {
  	super () ;
  	
    // ajout du panneau principal
    panneau = new BmbPanneau () ;
    this.getContentPane ().add (this.panneau) ;

    // initialisation de la fenêtre
    this.setTitle (FENETRE_NOM) ;
    this.setSize (FENETRE_WIDTH, FENETRE_HEIGHT) ;
    try
    {
      this.setIconImage (this.getToolkit ().getImage (new File (FENETRE_ICONE).getCanonicalPath ())) ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      System.exit (1) ;
    }
    this.setVisible (true) ;
    
    // initialisation du gestionnaire de fenêtre
    this.addWindowListener (new GestionnaireFenetre ()) ;
  }

  /**
   * Gestionnaire de fenêtre de <b>JavaBomber</b>.
   */
  private class GestionnaireFenetre extends WindowAdapter
  {
    public void windowClosing (WindowEvent event)
    {
      JavaBomber.this.panneau.gestionEtat (panneau.new BmbEvenement (BmbPanneau.EVT_FERMER)) ;
    } /* windowClosing () */
  } /* GestionnaireFenetre () */
  
  /**
   * Point d'entrée de l'application <b>JavaBomber</b>.
   * 
   * @param args arguments passés en paramètre de l'application
   * @see java.lang.String
   */
  public static void main (String[] args)
  {
    // création de la fenêtre principale
  	new JavaBomber () ;
  } /* main () */
} /* JavaBomber */
