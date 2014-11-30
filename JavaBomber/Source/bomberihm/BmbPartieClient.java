package bomberihm ;

import java.awt.* ;
import java.awt.event.* ;
import java.io.* ;
import javax.swing.* ;
import bombernetwork.* ;
import bombergraphics.* ;
import bomberscene.* ;

/**
 * <b>BmbPartieClient</b> gère et affiche la partie côté client.
 */
public class BmbPartieClient extends JFrame
{
  // constantes diverses
  private static final int FENETRE_HEIGHT = 760 ;                        // hauteur de la fenêtre
  private static final int FENETRE_WIDTH  = 872 ;                        // largeur de la fenêtre
  private static final String FENETRE_ICONE = "./Image/JavaBomber.png" ; // fichier contenant l'icone
  private static final String FENETRE_NOM   = "JavaBomber Client" ;      // nom de la fenêtre

  // variables membres
  private BmbPanneau panneau ;    // serveur de l'application
  private BmbGraphics affichage ; // panneau d'affichage
  private BmbClavier clavier ;    // évenement clavier
  private BmbClient client ;      // connexion cliente
  private BmbSceneClient scene ;  // scene du jeu

  /**
   * Constructor de <b>BmbPartieClient</b>.
   * 
   * @param parent fenêtre parente
   * @param panneau interface permettant de démarrer la partie
   */
  public BmbPartieClient (BmbPanneau panneau, BmbClient client)
  {
    super () ;
    
    // initialisation des variables membres
    this.panneau = panneau ;
    this.client = client ;
    this.affichage = new BmbGraphics () ;
		this.clavier =   new BmbClavier () ;
    
    this.getContentPane ().setLayout (new BorderLayout ()) ;
    this.getContentPane ().add (this.affichage, BorderLayout.CENTER) ;
    
    // initialisation de la fenêtre
    this.setResizable (false) ;
    this.setTitle (FENETRE_NOM) ;
    try
    {
      this.setIconImage (this.getToolkit ().getImage (new File (FENETRE_ICONE).getCanonicalPath ())) ;
    }
    catch (IOException exception)
    {
      BmbPartieClient.this.client.envoyer (new BmbMessage (BmbConstantes.MSG_ARRETER, "")) ;
      BmbPartieClient.this.panneau.gestionEtat (BmbPartieClient.this.panneau.new BmbEvenement (BmbPanneau.EVT_CLIENT_FINPARTIE)) ;
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
    this.setSize (FENETRE_WIDTH, FENETRE_HEIGHT) ;
    
    // initialisation des gestionnaires
    this.addKeyListener (this.clavier) ;
    this.addWindowListener (new GestionnaireFenetre ()) ;
  }

  /**
   * Gère les messages reçus en provenance du serveur.
   * 
   * @param message message envoyé
   */
  public void traiterMessage (BmbMessage message)
  {
    // affiche le message
    if (message instanceof BmbMessage)
    {
      BmbMessage messageRecu = (BmbMessage) message ;
      switch (messageRecu.getAction ())
      {
        case BmbConstantes.MSG_ELT_CREE :
        {
          // crée un nouvel élément
          String identifiant = message.getArgument (0) ;
          BmbSprite sprite = this.affichage.getSprite(message.getArgument(1)) ;
          int x = Integer.parseInt (message.getArgument(2)) ;
          int y = Integer.parseInt (message.getArgument(3)) ;
          int imageX = Integer.parseInt (message.getArgument(4)) ;
          int imageY = Integer.parseInt (message.getArgument(5)) ;
          
          BmbElementVisuel element = new BmbElementVisuel (this.scene, sprite, x, y, imageX, imageY, identifiant) ;
          element.setVisible (message.getArgument(6).equals (Boolean.TRUE.toString ())) ;
          
          // ajoute l'élément à la scène
          this.scene.ajouterElement(element);
          
          break ;
        }
        case BmbConstantes.MSG_ELT_MODIFIE :
        {
          try
          {
            // modifier l'élément
            BmbElementVisuel element = (BmbElementVisuel) this.scene.getElement (message.getArgument (0)) ;
            element.setX (Integer.parseInt (message.getArgument(1))) ;
            element.setY (Integer.parseInt (message.getArgument(2))) ;
            element.setImageX (Integer.parseInt (message.getArgument(3))) ;
            element.setImageY (Integer.parseInt (message.getArgument(4))) ;
            element.setVisible (message.getArgument(5).equals (Boolean.TRUE.toString ())) ;
          }
          catch (Exception exception)
          {
            System.out.println (exception.toString ()) ;
            exception.printStackTrace () ;
          }
          
          break ;
        }
        case BmbConstantes.MSG_ELT_SUPPRIME :
        {
          // supprimer l'élément de la scene
          try
          {
            this.scene.retirerElement (message.getArgument (0)) ;
          }
          catch (Exception exception)
          {
            System.out.println (exception.toString ()) ;
            exception.printStackTrace () ;
          }
          
          break ;
        }
        case BmbConstantes.MSG_RAFRAICHIR :
        {
          try
          {
            // envoie les touches au serveur
            int touches [] = {BmbClavier.DROITE, BmbClavier.GAUCHE, BmbClavier.HAUT, BmbClavier.BAS, BmbClavier.ENTREE} ;
            for (int i = 0; i < touches.length; i ++)
            {
              String arguments = Integer.toString (touches [i])
                               + BmbMessage.SENTINELLE
                               + BmbPartieClient.this.clavier.getPresse(touches [i]);
              this.client.envoyer (new BmbMessage (BmbConstantes.MSG_TOUCHE, arguments)) ;
            }

            // redessine la scène
            BmbPartieClient.this.scene.rafraichir () ;
            BmbPartieClient.this.affichage.repaint () ;
            this.client.envoyer (new BmbMessage (BmbConstantes.MSG_RAFRAICHITOK, "")) ;
          }
          catch (Exception exception)
          {
            BmbPartieClient.this.client.envoyer (new BmbMessage (BmbConstantes.MSG_ARRETER, "")) ;
            BmbPartieClient.this.panneau.gestionEtat (BmbPartieClient.this.panneau.new BmbEvenement (BmbPanneau.EVT_CLIENT_FINPARTIE)) ;
            System.out.println (exception.toString ()) ;
            exception.printStackTrace () ;
          }
          
          break ;
        }
      }
    }
  } /* traiterMessage () */

  /**
   * Démarre une nouvelle partie.
   */
  public void demarrerPartie ()
  {
    this.setVisible (true) ;
    
    // création de la scène
    try
    {
      this.scene = new BmbSceneClient (this.affichage) ;
    }
    catch (Exception exception)
    {
      BmbPartieClient.this.client.envoyer (new BmbMessage (BmbConstantes.MSG_ARRETER, "")) ;
      BmbPartieClient.this.panneau.gestionEtat (BmbPartieClient.this.panneau.new BmbEvenement (BmbPanneau.EVT_CLIENT_FINPARTIE)) ;
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
  } /* demarrerPartie () */

  /**
   * Arrête la partie.
   */
  public void arreterPartie ()
  {
    for (int i = 0; i < this.scene.getNombreElement (); i ++)
    {
      this.scene.retirerElement (this.scene.getElement (i)) ;
    }
    this.setVisible (false) ;
  } /* arreterPartie () */


  /**
   * Gestionnaire de fenêtre de <b>BmbPartieServeur</b>.
   */
  private class GestionnaireFenetre extends WindowAdapter
  {
    public void windowClosing (WindowEvent event)
    {
      BmbPartieClient.this.client.envoyer (new BmbMessage (BmbConstantes.MSG_ARRETER, "")) ;
      BmbPartieClient.this.panneau.gestionEtat (BmbPartieClient.this.panneau.new BmbEvenement (BmbPanneau.EVT_CLIENT_FINPARTIE)) ;
    } /* windowClosing () */
  } /* GestionnaireFenetre () */
} /* BmbPartieServeur */
