import java.awt.event.* ;
import java.io.* ;
import javax.swing.* ;
import bomberihm.* ;

/**
 * Point d'entr�e et fen�tre principale de l'application <b>JavaBomber</b>.
 * @see javax.swing.JFrame
 * @see bombernetwork.BmbServeur
 * @see bombernetwork.BmbClient
 */
public class JavaBomber extends JFrame
{
  private static final int FENETRE_HEIGHT = 600 ; // hauteur de la fen�tre
  private static final int FENETRE_WIDTH  = 800 ; // largeur de la fen�tre
  private static final String FENETRE_ICONE = "./Image/JavaBomber.png" ; // fichier contenant l'icone
  private static final String FENETRE_NOM = "JavaBomber" ; // nom de la fen�tre
  
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

    // initialisation de la fen�tre
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
    
    // initialisation du gestionnaire de fen�tre
    this.addWindowListener (new GestionnaireFenetre ()) ;
  }

  /**
   * Gestionnaire de fen�tre de <b>JavaBomber</b>.
   */
  private class GestionnaireFenetre extends WindowAdapter
  {
    public void windowClosing (WindowEvent event)
    {
      JavaBomber.this.panneau.gestionEtat (panneau.new BmbEvenement (BmbPanneau.EVT_FERMER)) ;
    } /* windowClosing () */
  } /* GestionnaireFenetre () */
  
  /**
   * Point d'entr�e de l'application <b>JavaBomber</b>.
   * 
   * @param args arguments pass�s en param�tre de l'application
   * @see java.lang.String
   */
  public static void main (String[] args)
  {
    // cr�ation de la fen�tre principale
  	new JavaBomber () ;
  } /* main () */
} /* JavaBomber */
